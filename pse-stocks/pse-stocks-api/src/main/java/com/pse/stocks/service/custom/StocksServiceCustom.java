package com.pse.stocks.service.custom;

import com.pse.stocks.model.Stocks;

public interface StocksServiceCustom {

    Stocks getAll();
    Stocks getBySymbol(String[] symbol);

}
