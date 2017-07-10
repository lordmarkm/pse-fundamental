package com.pse.stocks.service.phisix;

import javax.annotation.PostConstruct;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;

import org.dozer.Mapper;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import com.pse.stocks.config.PhisixMapperConfig;
import com.pse.stocks.dto.phisix.PhisixStocks;
import com.pse.stocks.model.Stocks;
import com.pse.stocks.service.StocksService;
import com.pse.stocks.service.custom.StocksServiceCustom;

public class StocksServiceCustomImpl implements StocksServiceCustom {

    private static final String ALL_STOCKS_SUFFIX = ".json";

    @Autowired
    private StocksService service;

    @Autowired
    @Qualifier(PhisixMapperConfig.PHISIX_MAPPER)
    private Mapper mapper;

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
        DateTime queryTime = DateTime.now().minuteOfHour().roundFloorCopy();
        Stocks stocks = service.findByQueryTime(queryTime);

        if (null == stocks) {
            Invocation.Builder builder = client.target(phisixUrl + ALL_STOCKS_SUFFIX).request();
            Response response = builder.get();
            PhisixStocks psStocks = builder.get(PhisixStocks.class);

            stocks = mapper.map(psStocks, Stocks.class);
            stocks.setQueryTime(queryTime);
            stocks = service.save(stocks);
        }

        return stocks;
    }

    @Override
    public Stocks getBySymbol(String[] symbol) {
        // TODO Auto-generated method stub
        return null;
    }

}
