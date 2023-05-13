package com.ran.algoritmovisibilidad.model.dao;

import java.util.List;

import com.ran.algoritmovisibilidad.model.entity.ProductEntity;

public interface ProductDao {
    List<ProductEntity> getAll();
}