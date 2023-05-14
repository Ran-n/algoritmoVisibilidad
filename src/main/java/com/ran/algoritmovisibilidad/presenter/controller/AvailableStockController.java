package com.ran.algoritmovisibilidad.presenter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ran.algoritmovisibilidad.presenter.service.AvailableStockService;
import com.ran.algoritmovisibilidad.transformer.ProductDtoDvoTransformer;

@RestController
public class AvailableStockController {

    private static final Logger logger = LoggerFactory.getLogger(AvailableStockController.class);

    @Autowired
    AvailableStockService availableStockService;
    @Autowired
    ProductDtoDvoTransformer productTransformer;

    @RequestMapping("/availableStock")
    public String availableStock() {
        logger.info("Accessing the available stock");

        final String products = productTransformer.toDvo(availableStockService.getVisible()).toString(); 
        return products.substring(1, products.length()-1);
    }
}