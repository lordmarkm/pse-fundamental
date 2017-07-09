package com.pse.config;

import java.text.SimpleDateFormat;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;

/**
 *
 *
 * @author Mark Baldwin B. Martinez on 5 Jan 2017
 *
 */
@Configuration
public class DateTimeSerializationConfig {

    public static final String DATETIME_FORMAT = "YYYY-MM-dd hh:mm a";
    public static final String DATE_FORMAT = "YYYY-MM-dd";

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void configureDateTimeSerialization() {
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new SimpleDateFormat(DATETIME_FORMAT));
        objectMapper.registerModule(new JodaModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

}
