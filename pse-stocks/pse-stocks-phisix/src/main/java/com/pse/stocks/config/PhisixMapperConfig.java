package com.pse.stocks.config;

import static org.dozer.loader.api.FieldsMappingOptions.*;

import javax.annotation.PostConstruct;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pse.stocks.dto.phisix.PhisixStock;
import com.pse.stocks.dto.phisix.PhisixStocks;
import com.pse.stocks.model.Stock;
import com.pse.stocks.model.Stocks;

@Configuration
public class PhisixMapperConfig {

    public static final String PHISIX_MAPPER = "phisixMapper";

    @Bean(name = PHISIX_MAPPER)
    public DozerBeanMapper mapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        return mapper;
    }

    @PostConstruct
    public void init() {
        mapper().addMapping(new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(PhisixStocks.class, Stocks.class)
                    .fields("as_of", "asOf", customConverter(StringToDateTimeConverter.class))
                    .fields("stock", "stocks");

                mapping(PhisixStock.class, Stock.class)
                    .fields("percent_change", "percentChange")
                    .fields("price.amount", "price");
            }
        });
    }

}
