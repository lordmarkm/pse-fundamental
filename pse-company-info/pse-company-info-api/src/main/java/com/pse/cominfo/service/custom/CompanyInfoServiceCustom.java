package com.pse.cominfo.service.custom;

import com.pse.cominfo.model.CompanyInformation;

public interface CompanyInfoServiceCustom {

    void buildIndex();
    CompanyInformation findByCode(String code);

}
