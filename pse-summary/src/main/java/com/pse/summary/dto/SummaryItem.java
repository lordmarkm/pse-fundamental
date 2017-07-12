package com.pse.summary.dto;

import java.math.BigDecimal;

import com.pse.cominfo.model.CompanyInformation;
import com.pse.core.util.BigDecimalUtil;
import com.pse.stocks.model.Stock;

public class SummaryItem {

    private Stock stock;
    private CompanyInformation companyInfo;

    public BigDecimal getPriceOverBookValue() {
        return BigDecimalUtil.divide(stock.getPrice(), companyInfo.getBookValuePerShare());
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public CompanyInformation getCompanyInfo() {
        return companyInfo;
    }

    public void setCompanyInfo(CompanyInformation companyInfo) {
        this.companyInfo = companyInfo;
    }

}
