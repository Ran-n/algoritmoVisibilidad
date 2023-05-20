package com.ran.algoritmovisibilidad.presenter.service.impl;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
        Set<ProductDto> strictlyRegularProducts = new HashSet<>(productService.getStrictlyRegular());
        
        if (strictlyRegularProducts.size() > 0) {
                // check if any product has stock or is backSoon (only one is needed to be visible)
                // coded like: remove a product if its both out of stock and not back soon
                strictlyRegularProducts.removeIf(product ->
                        !productService.hasStock(product.getId())
                        && !productService.isBackSoon(product.getId())
                );
        }
    
        // SECOND SCENARIO
        // get the groups of special and regular products that are stocked or backSoon
        Set<ProductDto> special = new HashSet<>(productService.getSpecialStockedOrBackSoon());
        Set<Long> regularIds = productService.getRegularStockedOrBackSoon().stream()
                .map(ProductDto::getId)
                .collect(Collectors.toSet());
        Set<ProductDto> intersection = new HashSet<>();
        if (special.size() > 0 && regularIds.size() > 0) {
                // calculate the intersection using group theory
                intersection = special.stream()
                        .filter(specialProduct -> regularIds.contains(specialProduct.getId()))
                        .collect(Collectors.toSet());
        }
    
        // combine both scenarios
        // coded like: combine strictlyRegularProducts and intersection without duplicates
        strictlyRegularProducts.addAll(intersection);
    
        // Sort and return result
        List<ProductDto> visibleProducts = strictlyRegularProducts.stream()
                .sorted(Comparator.comparing(ProductDto::getSequence))
                .collect(Collectors.toList());
    
        return visibleProducts;
    }
}