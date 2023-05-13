package com.ran.algoritmovisibilidad.presenter.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ran.algoritmovisibilidad.model.dao.ProductDao;
import com.ran.algoritmovisibilidad.model.dao.SizeDao;
import com.ran.algoritmovisibilidad.model.dao.StockDao;
import com.ran.algoritmovisibilidad.model.entity.ProductEntity;
import com.ran.algoritmovisibilidad.model.entity.SizeEntity;
import com.ran.algoritmovisibilidad.model.entity.StockEntity;
import com.ran.algoritmovisibilidad.transformer.ProductEntityDtoTransformer;
import com.ran.algoritmovisibilidad.transformer.SizeEntityDtoTransformer;
import com.ran.algoritmovisibilidad.transformer.StockEntityDtoTransformer;

@RestController
public class StockController {

    private static final Logger logger = LoggerFactory.getLogger(StockController.class);

    @Autowired
    ProductEntityDtoTransformer productTransformer;
    @Autowired
    SizeEntityDtoTransformer sizeTransformer;
    @Autowired
    StockEntityDtoTransformer stockTransformer;
    @Autowired
    ProductDao productDao;
    @Autowired
    SizeDao sizeDao;
    @Autowired
    StockDao stockDao;

    @RequestMapping("/stock")
    public ArrayList<Object> stock() throws IOException {
        logger.info("Accessing the stock");

        ArrayList<Object> items = new ArrayList<Object>();
        
        List<ProductEntity> a = productDao.getAll(); 
        List<SizeEntity> b = sizeDao.getAll();
        List<StockEntity> c = stockDao.getAll();

        items.add(productTransformer.toDto(a));
        items.add(sizeTransformer.toDto(b));
        items.add(stockTransformer.toDto(c));
        
        return items;
    }
}