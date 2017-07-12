package com.pse.cominfo.service.pseedge;

import static com.pse.cominfo.util.PseEdgeIds.PSE_EDGE_MAPPING;
import static com.pse.core.util.BigDecimalUtil.*;

import java.io.IOException;
import java.util.List;

import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.pse.cominfo.model.CompanyInformation;
import com.pse.cominfo.service.CompanyInfoService;
import com.pse.cominfo.service.custom.CompanyInfoServiceCustom;

public class CompanyInfoServiceCustomImpl implements CompanyInfoServiceCustom {

    private static final Logger LOG = LoggerFactory.getLogger(CompanyInfoServiceCustomImpl.class);

    @Autowired
    private CompanyInfoService service;

    @Value("${pse-edge.url}")
    private String pseEdgeUrl;

    public List<CompanyInformation> findAll() {
        DateTime queryTime = DateTime.now().dayOfMonth().roundFloorCopy();
        List<CompanyInformation> companyInfos = service.findByQueryTime(queryTime);
        if (companyInfos.size() == PSE_EDGE_MAPPING.size()) {
            return companyInfos;
        } else {
            buildIndex();
            return service.findByQueryTime(queryTime);
        }
    }

    @Override
    public void buildIndex() {
        DateTime queryTime = DateTime.now().dayOfMonth().roundFloorCopy();

        PSE_EDGE_MAPPING.forEach((code, pseEdgeId) -> {
            CompanyInformation companyInfo = doPseEdgeQuery(queryTime, code, pseEdgeId);
            service.save(companyInfo);
        });
    }

    @Override
    public CompanyInformation findByCode(String code) {
        DateTime queryTime = DateTime.now().dayOfMonth().roundFloorCopy();
        String pseEdgeId = PSE_EDGE_MAPPING.get(code);
        CompanyInformation companyInfo = service.findByCodeAndQueryTime(code, queryTime);

        if (null == companyInfo) {
            companyInfo = service.save(doPseEdgeQuery(queryTime, code, pseEdgeId));
        }

        return companyInfo;
    }

    private CompanyInformation doPseEdgeQuery(DateTime queryTime, String code, String pseEdgeId) {
        try {
            LOG.info("Firing company information query to PSE Edge. Code={}", code);
            Document doc = Jsoup.connect(pseEdgeUrl + pseEdgeId).get();

            //<tr>
            //  <th>Book Value Per Share</th>
            //  <td>1.44</td> <--current year bv/s
            //  <td>1.12</td> <--prev year bv/s
            //</tr>
            String bookValuePerShare = doc.select("th:contains(Book Value Per Share)")
                .get(1)
                .nextElementSibling()
                .text();

            String earningsPerShare = doc.select("th:contains(Earnings/(Loss) Per Share (Basic))")
                .get(1)
                .nextElementSibling()
                .text();

            CompanyInformation companyInfo = new CompanyInformation();
            companyInfo.setCode(code);
            companyInfo.setBookValuePerShare(tryParse(bookValuePerShare));
            companyInfo.setEarningsPerShare(tryParse(earningsPerShare));
            companyInfo.setBuyingPrice(multiply(companyInfo.getBookValuePerShare(), 1.5d));
            companyInfo.setBuyingPriceAtTarget10(
                    multiply(companyInfo.getBookValuePerShare(),
                            divide(companyInfo.getEarningsPerShare(), 10d)));
            companyInfo.setQueryTime(queryTime);
            return companyInfo;
        } catch (IOException e) {
            LOG.error("Unsuccessful PSE Edge query. Company code={}", code, e);
            return null;
        }
    }

}
