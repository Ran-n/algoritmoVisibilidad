package com.ran.algoritmovisibilidad.presenter.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ran.algoritmovisibilidad.model.dao.ProductDao;
import com.ran.algoritmovisibilidad.presenter.dto.ProductDto;
import com.ran.algoritmovisibilidad.presenter.service.ProductService;
import com.ran.algoritmovisibilidad.transformer.ProductEntityDtoTransformer;

@Service("ProductService")
public class ProductServiceImpl implements ProductService {
    
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    ProductEntityDtoTransformer productTransformer;
    @Autowired
    ProductDao productDao;
    
    @Override
    public List<ProductDto> getAll() {
        logger.info("Getting all the products");
        return productTransformer.toDto(productDao.getAll());
    }
}
