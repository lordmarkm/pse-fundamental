package com.pse.cominfo.service;

import java.util.List;

import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

import com.pse.cominfo.model.CompanyInformation;
import com.pse.cominfo.service.custom.CompanyInfoServiceCustom;

public interface CompanyInfoService extends CompanyInfoServiceCustom, JpaRepository<CompanyInformation, Long> {

    CompanyInformation findByCodeAndQueryTime(String code, DateTime queryTime);
    List<CompanyInformation> findByQueryTime(DateTime queryTime);

}
