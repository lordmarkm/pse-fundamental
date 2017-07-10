package com.pse.stocks.dto.phisix;

import java.math.BigDecimal;
import java.math.BigInteger;

public class PhisixStock {

    private String name;
    private PhisixPrice price;
    private BigDecimal percent_change;
    private BigInteger volume;
    private String symbol;

    public PhisixPrice getPrice() {
        return price;
    }
    public void setPrice(PhisixPrice price) {
        this.price = price;
    }
    public BigDecimal getPercent_change() {
        return percent_change;
    }
    public void setPercent_change(BigDecimal percent_change) {
        this.percent_change = percent_change;
    }
    public BigInteger getVolume() {
        return volume;
    }
    public void setVolume(BigInteger volume) {
        this.volume = volume;
    }
    public String getSymbol() {
        return symbol;
    }
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

}
