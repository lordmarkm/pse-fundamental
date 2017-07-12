package com.pse.summary.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.pse.cominfo.service.CompanyInfoService;
import com.pse.cominfo.util.PseEdgeIds;
import com.pse.stocks.model.Stock;
import com.pse.stocks.model.Stocks;
import com.pse.stocks.service.StocksService;
import com.pse.summary.dto.SummaryItem;

@Service
public class SummaryServiceImpl implements SummaryService {

    @Autowired
    private StocksService stocksService;

    @Autowired
    private CompanyInfoService companyInfoService;

    @Override
    public List<SummaryItem> getSummery(String[] codes) {
        Stocks stocks = stocksService.getAll();

        List<SummaryItem> items = Lists.newArrayList();

        if (codes.length == 0) {
            codes = PseEdgeIds.PSE_EDGE_MAPPING.keySet().toArray(new String[]{});
        }

        for (String code : codes) {
            Optional<Stock> stockOpt = stocks.get(code);
            if (stockOpt.isPresent()) {
                SummaryItem item = new SummaryItem();
                item.setStock(stockOpt.get());
                item.setCompanyInfo(companyInfoService.findByCode(code));
                items.add(item);
            }
        }

        return items;
    }

}
