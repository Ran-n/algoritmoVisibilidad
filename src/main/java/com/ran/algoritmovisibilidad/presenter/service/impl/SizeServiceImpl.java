package com.ran.algoritmovisibilidad.presenter.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ran.algoritmovisibilidad.model.dao.SizeDao;
import com.ran.algoritmovisibilidad.presenter.dto.SizeDto;
import com.ran.algoritmovisibilidad.presenter.service.SizeService;
import com.ran.algoritmovisibilidad.presenter.service.StockService;
import com.ran.algoritmovisibilidad.transformer.SizeEntityDtoTransformer;

@Service("SizeService")
public class SizeServiceImpl implements SizeService {

    private static final Logger logger = LoggerFactory.getLogger(SizeServiceImpl.class);

    @Autowired
    SizeEntityDtoTransformer sizeTransformer;
    @Autowired
    SizeDao sizeDao;
    @Autowired
    StockService stockService;
    
    @Override
    public List<SizeDto> getAll() {
        logger.info("Getting all the sizes");
        return sizeTransformer.toDto(sizeDao.getAll());
    }
    
    @Override
    public List<SizeDto> getById(Long id) {
        logger.info("Searching for size ID {}", id);
        return sizeTransformer.toDto(sizeDao.getById(id));
    }
    
    @Override
    public List<SizeDto> getById(List<Long> ids) {
        logger.info("Searching for sizes IDs {}", ids);
        return sizeTransformer.toDto(sizeDao.getById(ids));
    }
    
    @Override
    public List<SizeDto> getStocked() {
        logger.info("Getting stocked sizes");
        return stockService.getStocked().stream()
        .map(stock -> stock.getSize().getId())
        .distinct()
                .flatMap(ids -> getById(ids).stream())
                .collect(Collectors.toList());
    }
    
    @Override
    public List<SizeDto> getSoldOut() {
        logger.info("Getting sold out sizes");
        return stockService.getSoldOut().stream()
                .map(stock -> stock.getSize().getId())
                .distinct()
                .flatMap(ids -> getById(ids).stream())
                .collect(Collectors.toList());
    }

    @Override
    public List<SizeDto> getBackSoon() {
        logger.info("Getting sizes with \"backSoon\" set to true");
        return sizeTransformer.toDto(sizeDao.getBackSoon());
    }

    @Override
    public List<SizeDto> getNotBackSoon() {
        logger.info("Getting sizes with \"backSoon\" set to false");
        return sizeTransformer.toDto(sizeDao.getNotBackSoon());
    }

    @Override
    public List<SizeDto> getSpecial() {
        logger.info("Getting sizes with \"special\" set to true");
        return sizeTransformer.toDto(sizeDao.getSpecial());
    }

    @Override
    public List<SizeDto> getSpecialStockedOrBackSoon() {
        logger.info("Getting sizes with \"special\" set to true that have stock or are back soon");
        return sizeTransformer.toDto(sizeDao.getSpecialStockedOrBackSoon());
    }
    
    @Override
    public List<SizeDto> getRegular() {
        logger.info("Getting sizes with \"special\" set to false");
        return sizeTransformer.toDto(sizeDao.getRegular());
    }

    @Override
    public List<SizeDto> getRegularStockedOrBackSoon() {
        logger.info("Getting sizes with \"regular\" set to true that have stock or are back soon");
        return sizeTransformer.toDto(sizeDao.getRegularStockedOrBackSoon());
    }

    @Override
    public List<SizeDto> getByProductId(Long productId) {
        logger.info("Getting all the sizes with product ID {}", productId);
        return sizeTransformer.toDto(sizeDao.getByProductId(productId));
    }
}