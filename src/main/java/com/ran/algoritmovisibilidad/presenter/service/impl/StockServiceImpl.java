package com.ran.algoritmovisibilidad.presenter.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ran.algoritmovisibilidad.model.dao.StockDao;
import com.ran.algoritmovisibilidad.presenter.dto.StockDto;
import com.ran.algoritmovisibilidad.presenter.service.StockService;
import com.ran.algoritmovisibilidad.transformer.StockEntityDtoTransformer;

@Service("StockService")
public class StockServiceImpl implements StockService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    StockEntityDtoTransformer stockTransformer;
    @Autowired
    StockDao stockDao;

    @Override
    public List<StockDto> getAll() {
        logger.info("Getting all the stock information");
        return stockTransformer.toDto(stockDao.getAll());
    }

    @Override
    public List<StockDto> getStocked() {
        logger.info("Getting all the stocked stocks");
        return stockTransformer.toDto(stockDao.getStocked());
    }

    @Override
    public List<StockDto> getSoldOut() {
        logger.info("Getting all the sold out stocks");
        return stockTransformer.toDto(stockDao.getSoldOut());
    }

    @Override
    public Boolean hasStock(Long sizeId) {
        logger.info("Checking if the size ID {} has stock", sizeId);
        return stockDao.hasStock(sizeId);
    }

    @Override
    public List<StockDto> getBySizeId(Long sizeId) {
        logger.info("Getting all stocks by size ID {}", sizeId);
        return stockTransformer.toDto(stockDao.getBySizeId(sizeId));
    }
}