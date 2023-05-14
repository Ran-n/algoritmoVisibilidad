package com.ran.algoritmovisibilidad.presenter.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ran.algoritmovisibilidad.presenter.dto.ProductDto;
import com.ran.algoritmovisibilidad.presenter.service.AvailableStockService;
import com.ran.algoritmovisibilidad.presenter.service.ProductService;
import com.ran.algoritmovisibilidad.presenter.service.SizeService;
import com.ran.algoritmovisibilidad.presenter.service.StockService;


@Service("AvailableStockService")
public class AvailableStockServiceImpl implements AvailableStockService {

    private static final Logger logger = LoggerFactory.getLogger(AvailableStockServiceImpl.class);

    @Autowired
    ProductService productService;
    @Autowired
    SizeService sizeService;
    @Autowired
    StockService stockService;

    @Override
    public List<ProductDto> getVisible() {
        logger.info("Getting visible products");
    
        // FIRST SCENARIO
        // get the products that are strictly regular
        List<ProductDto> strictlyRegularProducts = productService.getStrictlyRegular();
    
        // check if any product has stock or is backSoon (only one is needed to be visible)
        // coded like: remove a product if its both out of stock and not back soon
        strictlyRegularProducts.removeIf(product ->
                !productService.hasStock(product.getId())
                && !productService.isBackSoon(product.getId())
        );

        // SECOND SCENARIO
        // get the groups of special and regular products that are stocked or backSoon
        List<ProductDto> special = productService.getSpecialStockedOrBackSoon();
        List<ProductDto> regular = productService.getRegularStockedOrBackSoon();
    
        // calculate the intersection using group theory
        List<ProductDto> intersection = special.stream()
                .filter(specialProduct -> regular.stream()
                        .anyMatch(regularProduct -> regularProduct.getId().equals(specialProduct.getId())))
                .collect(Collectors.toList());

        // if a product is in the inserction list, then it complies with the second scenario
        // because it has a size regular and special with either stock or backSoon

        // combine both scenarios
        // coded like: combine strictlyRegularProducts and intersection without duplicates
        List<ProductDto> visibleProducts = new ArrayList<>(strictlyRegularProducts);
        visibleProducts.addAll(intersection);
        
        visibleProducts = visibleProducts.stream()
                .collect(Collectors.toMap(ProductDto::getId, product -> product, (p1, p2) -> p1))
                .values()
                .stream()
                .sorted(Comparator.comparing(ProductDto::getSequence))
                .collect(Collectors.toList());
    
        return visibleProducts;
    }
}