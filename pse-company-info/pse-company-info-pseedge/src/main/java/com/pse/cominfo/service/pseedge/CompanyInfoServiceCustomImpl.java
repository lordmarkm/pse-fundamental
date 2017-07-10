package com.pse.cominfo.service.pseedge;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.collect.ImmutableMap;
import com.pse.cominfo.model.CompanyInformation;
import com.pse.cominfo.service.CompanyInfoService;
import com.pse.cominfo.service.custom.CompanyInfoServiceCustom;
import static com.pse.cominfo.util.PseEdgeIds.PSE_EDGE_MAPPING;

public class CompanyInfoServiceCustomImpl implements CompanyInfoServiceCustom {

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
            try {
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

                CompanyInformation companyInfo = new CompanyInformation();
                companyInfo.setCode(code);
                companyInfo.setBookValuePerShare(new BigDecimal(bookValuePerShare));
                companyInfo.setQueryTime(queryTime);
                service.save(companyInfo);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
    }

}
