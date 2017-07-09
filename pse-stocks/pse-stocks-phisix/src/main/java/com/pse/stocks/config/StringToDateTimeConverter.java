package com.pse.stocks.config;

import org.dozer.CustomConverter;
import org.joda.time.DateTime;

public class StringToDateTimeConverter implements CustomConverter {

    @Override
    public Object convert(Object existingDestinationFieldValue,
            Object sourceFieldValue,
            Class<?> destinationClass,
            Class<?> sourceClass) {
        return new DateTime((String) sourceFieldValue);
    }

}
