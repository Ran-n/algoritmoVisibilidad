package com.ran.algoritmovisibilidad.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ran.algoritmovisibilidad.model.dao.BaseDao;
import com.ran.algoritmovisibilidad.model.dao.ProductDao;
import com.ran.algoritmovisibilidad.model.entity.ProductEntity;

@Repository("ProductDao")
public class ProductDaoImpl extends BaseDao implements ProductDao {
    
    private static final List<ProductEntity> products = loadData("data/product.csv", ProductEntity.class);
    
    @Override
    public List<ProductEntity> getAll() {
        return products;
    }
}