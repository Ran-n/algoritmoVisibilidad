package com.ran.algoritmovisibilidad.model.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ran.algoritmovisibilidad.model.dao.BaseDao;
import com.ran.algoritmovisibilidad.model.dao.StockDao;
import com.ran.algoritmovisibilidad.model.entity.StockEntity;

@Repository("StockDao")
public class StockDaoImpl extends BaseDao implements StockDao {

    private static final Logger logger = LoggerFactory.getLogger(StockDaoImpl.class);
    
    private static final List<StockEntity> stocks = loadData("data/stock.csv", StockEntity.class);
    
    @Override
    public List<StockEntity> getAll() {
        logger.info("Getting all the stocks");
        return stocks;
    }

    @Override
    public List<StockEntity> getStocked() {
        logger.info("Getting all the stocked stocks");
        return stocks.stream()
        .filter(stock -> stock.getQuantity() > 0)
        .collect(Collectors.toList());
    }
            
    @Override
    public List<StockEntity> getSoldOut() {
        logger.info("Getting all the sold out stocks");
        return stocks.stream()
                .filter(stock -> stock.getQuantity() <= 0)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean hasStock(Long sizeId) {
        logger.info("Checking if the size ID {} has stock", sizeId);

        List<StockEntity> stocks = getBySizeId(sizeId);
        return stocks != null && !stocks.isEmpty() && stocks.stream().anyMatch(stock -> stock.getQuantity() > 0);
    }

    @Override
    public List<StockEntity> getBySizeId(Long sizeId) {
        logger.info("Getting all stocks by size ID {}", sizeId);
        return stocks.stream()
            .filter(stock -> stock.getSize().getId().equals(sizeId))
            .collect(Collectors.toList());
    }
}