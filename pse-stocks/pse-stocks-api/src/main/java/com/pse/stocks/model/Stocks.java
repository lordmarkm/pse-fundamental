package com.pse.stocks.model;

import java.util.List;
import java.util.Optional;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.pse.core.model.BaseEntity;

@Entity(name = "stocks")
public class Stocks extends BaseEntity {

    @Column(name = "query_time", updatable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime queryTime;

    @Column(name = "as_of", updatable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime asOf;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "stocks_id")
    private List<Stock> stocks;

    public Optional<Stock> get(String code) {
         return stocks.stream().filter(s -> s.getSymbol().equals(code)).findFirst();
    }

    public DateTime getAsOf() {
        return asOf;
    }

    public void setAsOf(DateTime asOf) {
        this.asOf = asOf;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public DateTime getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(DateTime queryTime) {
        this.queryTime = queryTime;
    }

}
