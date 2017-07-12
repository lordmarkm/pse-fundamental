package com.pse.cominfo.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import com.pse.core.model.BaseEntity;

@Entity(name = "company_info")
public class CompanyInformation extends BaseEntity {

    @Column(name = "query_time", updatable = false)
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime queryTime;

    @Column(name = "company_code")
    private String code;

    @Column(name = "bv_per_share")
    private BigDecimal bookValuePerShare;

    @Column(name = "eps")
    private BigDecimal earningsPerShare;

    @Column(name = "buying_price")
    private BigDecimal buyingPrice;

    @Column(name = "buying_price_at_target_10")
    private BigDecimal buyingPriceAtTarget10;

    public BigDecimal getBookValuePerShare() {
        return bookValuePerShare;
    }

    public void setBookValuePerShare(BigDecimal bookValuePerShare) {
        this.bookValuePerShare = bookValuePerShare;
    }

    public BigDecimal getEarningsPerShare() {
        return earningsPerShare;
    }

    public void setEarningsPerShare(BigDecimal earningsPerShare) {
        this.earningsPerShare = earningsPerShare;
    }

    public BigDecimal getBuyingPrice() {
        return buyingPrice;
    }

    public void setBuyingPrice(BigDecimal buyingPrice) {
        this.buyingPrice = buyingPrice;
    }

    public BigDecimal getBuyingPriceAtTarget10() {
        return buyingPriceAtTarget10;
    }

    public void setBuyingPriceAtTarget10(BigDecimal buyingPriceAtTarget10) {
        this.buyingPriceAtTarget10 = buyingPriceAtTarget10;
    }

    public DateTime getQueryTime() {
        return queryTime;
    }

    public void setQueryTime(DateTime queryTime) {
        this.queryTime = queryTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
