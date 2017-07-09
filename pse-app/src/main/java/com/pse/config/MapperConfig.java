package com.pse.config;

import org.dozer.DozerBeanMapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pse.core.dto.BaseInfo;
import com.pse.core.model.BaseEntity;

import javax.annotation.PostConstruct;

import static org.dozer.loader.api.FieldsMappingOptions.copyByReference;

@Configuration
public class MapperConfig {

    @Bean
    public DozerBeanMapper mapper() {
        DozerBeanMapper mapper = new DozerBeanMapper();
        return mapper;
    }

    @PostConstruct
    public void init() {
        mapper().addMapping(new BeanMappingBuilder() {
            @Override
            protected void configure() {
                mapping(BaseEntity.class, BaseInfo.class)
                    .fields("createdDate", "createdDate", copyByReference())
                    .fields("updatedDate", "updatedDate", copyByReference());
            }
        });
    }
}
