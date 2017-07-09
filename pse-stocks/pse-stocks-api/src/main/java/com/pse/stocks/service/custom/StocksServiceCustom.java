package com.pse.stocks.service.custom;

import com.pse.stocks.model.Stocks;

public interface StocksServiceCustom {

    Stocks findAll();
    Stocks findBySymbol(String[] symbol);

}
