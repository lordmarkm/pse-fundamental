package com.pse.stocks.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pse.stocks.model.Stocks;
import com.pse.stocks.service.StocksService;
import static org.springframework.http.HttpStatus.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/stocks")
public class StocksResource {

    @Autowired
    private StocksService service;

    @RequestMapping(method = GET)
    public ResponseEntity<Stocks> findAll() {
        return new ResponseEntity<Stocks>(service.getAll(), OK);
    }

}
