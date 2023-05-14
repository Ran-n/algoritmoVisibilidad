package com.ran.algoritmovisibilidad.model.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ran.algoritmovisibilidad.model.dao.BaseDao;
import com.ran.algoritmovisibilidad.model.dao.ProductDao;
import com.ran.algoritmovisibilidad.model.entity.ProductEntity;

@Repository("ProductDao")
public class ProductDaoImpl extends BaseDao implements ProductDao {

    private static final Logger logger = LoggerFactory.getLogger(ProductDaoImpl.class);
    
    private static final List<ProductEntity> products = loadData("data/product.csv", ProductEntity.class);
    
    @Override
    public List<ProductEntity> getAll() {
        logger.info("Getting all the products");
        return products;
    }

    @Override
    public List<ProductEntity> getById(Long id) {
        logger.info("Getting products with ID {}", id);
        List<ProductEntity> foundProducts = products.stream()
                                    .filter(product -> product.getId().equals(id))
                                    .collect(Collectors.toList());
        if (foundProducts.isEmpty()) {
            logger.warn("The products with ID {} was not found", id);
        }

        return foundProducts;
    }

    @Override
    public List<ProductEntity> getById(List<Long> ids) {
        logger.info("Getting products with IDs {}", ids);

        List<ProductEntity> foundProducts = products.stream()
                                        .flatMap(product -> getById(product.getId()).stream())
                                        .collect(Collectors.toList());
    
        if (foundProducts.isEmpty()) {
            logger.warn("No products found with the specified IDs: {}", ids);
        }
    
        return foundProducts;
    }
}