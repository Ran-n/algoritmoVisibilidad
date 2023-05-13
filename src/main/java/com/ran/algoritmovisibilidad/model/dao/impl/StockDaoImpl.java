package com.ran.algoritmovisibilidad.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ran.algoritmovisibilidad.model.dao.BaseDao;
import com.ran.algoritmovisibilidad.model.dao.StockDao;
import com.ran.algoritmovisibilidad.model.entity.StockEntity;

@Repository("StockDao")
public class StockDaoImpl extends BaseDao implements StockDao {
    
    private static final List<StockEntity> stocks = loadData("data/stock.csv", StockEntity.class);
    
    @Override
    public List<StockEntity> getAll() {
        return stocks;
    }
}