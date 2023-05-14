package com.ran.algoritmovisibilidad.model.dao;

import java.util.List;

import com.ran.algoritmovisibilidad.model.entity.ProductEntity;

public interface ProductDao {
    List<ProductEntity> getAll();
    List<ProductEntity> getById(Long id);
    List<ProductEntity> getById(List<Long> ids);
}