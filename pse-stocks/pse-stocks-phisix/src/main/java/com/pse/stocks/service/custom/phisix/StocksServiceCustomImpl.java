package com.pse.stocks.service.custom.phisix;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Value;

import com.pse.stocks.model.Stocks;
import com.pse.stocks.service.custom.StocksServiceCustom;

public class StocksServiceCustomImpl implements StocksServiceCustom {

    private static final String ALL_STOCKS_SUFFIX = ".json";

    @Value("${phisix.url}")
    private String phisixUrl;

    private Client client;

    @PostConstruct
    public void init() {
        ClientBuilder.newBuilder();
        client = ClientBuilder.newClient();
    }

    @Override
    public Stocks getAll() {
        Invocation.Builder builder = client.target(phisixUrl + ALL_STOCKS_SUFFIX).request();
        Response response = builder.get();
        return builder.get(Stocks.class);
    }

    @Override
    public Stocks getBySymbol(String[] symbol) {
        // TODO Auto-generated method stub
        return null;
    }

}
