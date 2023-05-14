package com.ran.algoritmovisibilidad.presenter.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ran.algoritmovisibilidad.model.dao.ProductDao;
import com.ran.algoritmovisibilidad.presenter.dto.ProductDto;
import com.ran.algoritmovisibilidad.presenter.dto.SizeDto;
import com.ran.algoritmovisibilidad.presenter.service.ProductService;
import com.ran.algoritmovisibilidad.presenter.service.SizeService;
import com.ran.algoritmovisibilidad.presenter.service.StockService;
import com.ran.algoritmovisibilidad.transformer.ProductEntityDtoTransformer;

@Service("ProductService")
public class ProductServiceImpl implements ProductService {
    
    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    StockService stockService;

    @Autowired
    ProductEntityDtoTransformer productTransformer;
    @Autowired
    ProductDao productDao;
    @Autowired
    SizeService sizeService;
    
    @Override
    public List<ProductDto> getAll() {
        logger.info("Getting all the products");
        return productTransformer.toDto(productDao.getAll());
    }

    @Override
    public List<ProductDto> getById(Long id) {
        logger.info("Searching for product ID {}", id);
        return productTransformer.toDto(productDao.getById(id));
    }

    @Override
    public List<ProductDto> getById(List<Long> ids) {
        logger.info("Searching for product IDs {}", ids);
        return productTransformer.toDto(productDao.getById(ids));
    }

    @Override
    public List<ProductDto> getStocked() {
        logger.info("Getting all the stocked products");
        return sizeService.getStocked().stream()
                .map(size -> size.getProduct().getId())
                .distinct()
                .flatMap(ids -> getById(ids).stream())
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getSoldOut() {
        logger.info("Getting all the sold out products");
        return sizeService.getSoldOut().stream()
                .map(size -> size.getProduct().getId())
                .distinct()
                .flatMap(ids -> getById(ids).stream())
                .collect(Collectors.toList());
    }
    

    @Override
    public List<ProductDto> getBackSoon() {
        logger.info("Getting products with \"backSoon\" set to true");
        return sizeService.getBackSoon().stream()
            .map(size -> size.getProduct().getId())
            .distinct()
            .flatMap(ids -> getById(ids).stream())
            .collect(Collectors.toList());
    }
    

    @Override
    public List<ProductDto> getNotBackSoon() {
        logger.info("Getting products with \"backSoon\" set to false");
    
        return sizeService.getNotBackSoon().stream()
                .map(size -> size.getProduct().getId())
                .distinct()
                .flatMap(ids -> getById(ids).stream())
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getSpecial() {
        logger.info("Getting products with \"special\" set to true");
    
        return sizeService.getSpecial().stream()
                .map(size -> size.getProduct().getId())
                .distinct()
                .flatMap(ids -> getById(ids).stream())
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getSpecialStockedOrBackSoon() {
        logger.info("Getting products with \"special\" set to true that have stock or are back soon");
        return sizeService.getSpecialStockedOrBackSoon().stream()
                .map(size -> size.getProduct().getId())
                .distinct()
                .flatMap(ids -> getById(ids).stream())
                .collect(Collectors.toList());
    }
    
    @Override
    public List<ProductDto> getRegular() {
        logger.info("Getting products with \"special\" set to false");
    
        return sizeService.getRegular().stream()
                .map(size -> size.getProduct().getId())
                .distinct()
                .flatMap(ids -> getById(ids).stream())
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> getRegularStockedOrBackSoon() {
        logger.info("Getting products with \"regular\" set to true that have stock or are back soon");
        return sizeService.getRegularStockedOrBackSoon().stream()
                .map(size -> size.getProduct().getId())
                .distinct()
                .flatMap(ids -> getById(ids).stream())
                .collect(Collectors.toList());
    }

    // Products that are never marked as having a special size
    @Override
    public List<ProductDto> getStrictlyRegular() {
        logger.info("Getting products that are strictly regular");
    
        List<ProductDto> specials = getSpecial();
        List<ProductDto> regulars = getRegular();
    
        List<ProductDto> strictlyRegular = regulars.stream()
                .filter(regular -> specials.stream().noneMatch(special -> special.getId().equals(regular.getId())))
                .collect(Collectors.toList());
    
        return strictlyRegular;
    }

    @Override
    public Boolean hasStock(Long id) {
        logger.info("Checking if product ID {} has stock in any size", id);
        return sizeService.getByProductId(id).stream()
                .anyMatch(size -> stockService.hasStock(size.getId()));
    }

    @Override
    public Boolean isBackSoon(Long id) {
        logger.info("Checking if product ID {} is \"backSoon\" for any size", id);
        return sizeService.getByProductId(id).stream()
                .anyMatch(SizeDto::isBackSoon);
    }
}