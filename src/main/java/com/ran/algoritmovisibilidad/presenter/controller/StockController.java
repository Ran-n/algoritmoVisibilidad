package com.ran.algoritmovisibilidad.presenter.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ran.algoritmovisibilidad.model.entity.ProductEntity;
import com.ran.algoritmovisibilidad.model.entity.SizeEntity;
import com.ran.algoritmovisibilidad.model.entity.StockEntity;
import com.ran.algoritmovisibilidad.utils.CSVUtils;

@RestController
public class StockController {

    @RequestMapping("/stock")
    public ArrayList<Object> stock() throws IOException {
        ArrayList<Object> items = new ArrayList<Object>();
        
        ArrayList<ProductEntity> a = CSVUtils.readCSV(
            new ClassPathResource("data/product.csv").getFile().getAbsolutePath(),
            ProductEntity.class
        );
        ArrayList<SizeEntity> b = CSVUtils.readCSV(
            new ClassPathResource("data/size.csv").getFile().getAbsolutePath(),
            SizeEntity.class
        );
        ArrayList<StockEntity> c = CSVUtils.readCSV(
            new ClassPathResource("data/stock.csv").getFile().getAbsolutePath(),
            StockEntity.class
        );

        items.add(a);
        items.add(b);
        items.add(c);
        
        return items;
    }
}