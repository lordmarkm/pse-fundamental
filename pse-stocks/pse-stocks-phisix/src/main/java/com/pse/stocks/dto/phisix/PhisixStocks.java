package com.pse.stocks.dto.phisix;

import java.util.List;

import org.joda.time.DateTime;

public class PhisixStocks {

    private List<PhisixStock> stock;
    private String as_of;

    public List<PhisixStock> getStock() {
        return stock;
    }
    public void setStock(List<PhisixStock> stock) {
        this.stock = stock;
    }
    public String getAs_of() {
        return as_of;
    }
    public void setAs_of(String as_of) {
        this.as_of = as_of;
    }

}
