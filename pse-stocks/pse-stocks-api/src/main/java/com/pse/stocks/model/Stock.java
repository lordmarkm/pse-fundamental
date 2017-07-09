package com.pse.stocks.model;

import java.math.BigDecimal;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.pse.core.model.BaseEntity;

@Entity(name = "stock")
public class Stock extends BaseEntity {

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "pct_change")
    private BigDecimal percentChange;

    @Column(name = "volume")
    private BigInteger volume;

    @Column(name = "name")
    private String name;

    @Column(name = "symbol")
    private String symbol;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPercentChange() {
        return percentChange;
    }

    public void setPercentChange(BigDecimal percentChange) {
        this.percentChange = percentChange;
    }

    public BigInteger getVolume() {
        return volume;
    }

    public void setVolume(BigInteger volume) {
        this.volume = volume;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

}
