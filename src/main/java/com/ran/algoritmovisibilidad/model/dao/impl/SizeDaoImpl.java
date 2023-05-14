package com.ran.algoritmovisibilidad.model.dao.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ran.algoritmovisibilidad.model.dao.BaseDao;
import com.ran.algoritmovisibilidad.model.dao.SizeDao;
import com.ran.algoritmovisibilidad.model.dao.StockDao;
import com.ran.algoritmovisibilidad.model.entity.SizeEntity;

@Repository("SizeDao")
public class SizeDaoImpl extends BaseDao implements SizeDao {

    private static final Logger logger = LoggerFactory.getLogger(SizeDaoImpl.class);
    
    private static final List<SizeEntity> sizes = loadData("data/size.csv", SizeEntity.class);

    @Autowired
    private StockDao stockDao;
    
    @Override
    public List<SizeEntity> getAll() {
        logger.info("Getting all the sizes");
        return sizes;
    }

    @Override
    public List<SizeEntity> getById(Long id) {
        logger.info("Searching for the size with ID {}", id);

        List<SizeEntity> foundSizes = sizes.stream()
                                    .filter(size -> size.getId().equals(id))
                                    .collect(Collectors.toList());
        if (foundSizes.isEmpty()) {
            logger.warn("The size with ID {} was not found", id);
        }

        return foundSizes;
    }

    @Override
    public List<SizeEntity> getById(List<Long> ids) {
        logger.info("Searching for sizes with IDs: {}", ids);
    
        List<SizeEntity> foundSizes = sizes.stream()
                                        .flatMap(size -> getById(size.getId()).stream())
                                        .collect(Collectors.toList());
    
        if (foundSizes.isEmpty()) {
            logger.warn("No sizes found with the specified IDs: {}", ids);
        }
    
        return foundSizes;
    }

    @Override
    public List<SizeEntity> getBackSoon() {
        logger.info("Getting sizes with backSoon set to true");
    
        List<SizeEntity> backSoonSizes = sizes.stream()
                .filter(SizeEntity::isBackSoon)
                .collect(Collectors.toList());
    
        if (backSoonSizes.isEmpty()) {
            logger.warn("No sizes found with backSoon set to true");
        }
    
        return backSoonSizes;
    }

    @Override
    public List<SizeEntity> getNotBackSoon() {
        logger.info("Getting sizes with \"backSoon\" set to false");
    
        List<SizeEntity> notBackSoonSizes = sizes.stream()
                .filter(size -> !size.isBackSoon())
                .collect(Collectors.toList());
    
        if (notBackSoonSizes.isEmpty()) {
            logger.warn("No sizes found with \"backSoon\" set to false");
        }
    
        return notBackSoonSizes;
    }

    @Override
    public List<SizeEntity> getSpecial() {
        logger.info("Getting sizes with \"special\" set to true");
    
        List<SizeEntity> specialSizes = sizes.stream()
                .filter(SizeEntity::isSpecial)
                .collect(Collectors.toList());
    
        if (specialSizes.isEmpty()) {
            logger.warn("No sizes found with \"special\" set to true");
        }
    
        return specialSizes;
    }

    @Override
    public List<SizeEntity> getSpecialStockedOrBackSoon() {
        logger.info("Getting sizes with \"special\" set to true that have stock or are back soon");
    
        List<SizeEntity> specialSizesStockedOrBackSoon = sizes.stream()
                .filter(size ->
                        size.isSpecial()
                        && (stockDao.hasStock(size.getId()) || size.isBackSoon())
                )
                .collect(Collectors.toList());
    
        if (specialSizesStockedOrBackSoon.isEmpty()) {
            logger.warn("No sizes found with \"special\" set to true that have stock or are back soon");
        }
    
        return specialSizesStockedOrBackSoon;
    }
    
    @Override
    public List<SizeEntity> getRegular() {
        logger.info("Getting sizes with \"special\" set to false");
    
        List<SizeEntity> regularSizes = sizes.stream()
                .filter(size -> !size.isSpecial())
                .collect(Collectors.toList());
    
        if (regularSizes.isEmpty()) {
            logger.warn("No sizes found with \"special\" set to false");
        }
    
        return regularSizes;
    }

    @Override
    public List<SizeEntity> getRegularStockedOrBackSoon() {
        logger.info("Getting sizes with \"regular\" set to true that have stock or are back soon");
    
        List<SizeEntity> regularSizesStockedOrBackSoon = sizes.stream()
                .filter(size ->
                        !size.isSpecial()
                        && (stockDao.hasStock(size.getId()) || size.isBackSoon())
                )
                .collect(Collectors.toList());
    
        if (regularSizesStockedOrBackSoon.isEmpty()) {
            logger.warn("No sizes found with \"regular\" set to true that have stock or are back soon");
        }
    
        return regularSizesStockedOrBackSoon;
    }

    @Override
    public List<SizeEntity> getByProductId(Long productId) {
        logger.info("Getting all the sizes with product ID {}", productId);
        
        List<SizeEntity> sizesByProductId = sizes.stream()
                .filter(size -> size.getProduct().getId().equals(productId))
                .collect(Collectors.toList());
    
        return sizesByProductId;
    }
}