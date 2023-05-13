package com.ran.algoritmovisibilidad.presenter.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ran.algoritmovisibilidad.presenter.service.ProductService;
import com.ran.algoritmovisibilidad.presenter.service.SizeService;
import com.ran.algoritmovisibilidad.presenter.service.StockService;

@RestController
public class StockController {

    private static final Logger logger = LoggerFactory.getLogger(StockController.class);

    @Autowired
    ProductService productService;
    @Autowired
    SizeService sizeService;
    @Autowired
    StockService stockService;

    @RequestMapping("/stock")
    public ArrayList<Object> stock() throws IOException {
        logger.info("Accessing the stock");

        ArrayList<Object> items = new ArrayList<Object>();
        
        items.add(productService.getAll());
        items.add(sizeService.getAll());
        items.add(stockService.getAll());
        
        return items;
    }
}