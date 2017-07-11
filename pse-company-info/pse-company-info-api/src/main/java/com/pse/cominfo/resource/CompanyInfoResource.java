package com.pse.cominfo.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.http.HttpStatus.OK;

import com.pse.cominfo.model.CompanyInformation;
import com.pse.cominfo.service.CompanyInfoService;

@RestController
@RequestMapping("/company-info")
public class CompanyInfoResource {

    @Autowired
    private CompanyInfoService service;

    @RequestMapping(value = "/{code}", method = GET)
    public ResponseEntity<CompanyInformation> getCompanyInfo(@PathVariable String code) {
        return new ResponseEntity<>(service.findByCode(code), OK);
    }

}
