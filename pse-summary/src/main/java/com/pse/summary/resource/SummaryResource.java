package com.pse.summary.resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pse.summary.dto.SummaryItem;
import com.pse.summary.service.SummaryService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/summary")
public class SummaryResource {

    @Autowired
    private SummaryService service;

    @RequestMapping(method = GET)
    public ResponseEntity<List<SummaryItem>> getSummary() {
        return  new ResponseEntity<>(service.getSummery(new String[] {}), OK);
    }

    @RequestMapping(value = "/{codes}", method = GET)
    public ResponseEntity<List<SummaryItem>> getSummary(@PathVariable String codes) {
        return  new ResponseEntity<>(service.getSummery(codes.split(",")), OK);
    }

}
