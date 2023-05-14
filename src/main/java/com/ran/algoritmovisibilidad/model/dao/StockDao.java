package com.ran.algoritmovisibilidad.model.dao;

import java.util.List;

import com.ran.algoritmovisibilidad.model.entity.StockEntity;

public interface StockDao {
    List<StockEntity> getAll();
    List<StockEntity> getStocked();
    List<StockEntity> getSoldOut();
    Boolean hasStock(Long sizeId);
    List<StockEntity> getBySizeId(Long sizeId);
}