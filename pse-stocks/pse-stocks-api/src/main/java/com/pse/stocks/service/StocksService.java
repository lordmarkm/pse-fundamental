package com.pse.stocks.service;

import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pse.stocks.model.Stocks;
import com.pse.stocks.service.custom.StocksServiceCustom;

public interface StocksService extends StocksServiceCustom, JpaRepository<Stocks, Long> {

    Stocks findByQueryTime(DateTime queryTime);

}
