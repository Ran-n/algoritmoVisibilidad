package com.ran.algoritmovisibilidad.presenter.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ran.algoritmovisibilidad.model.dao.SizeDao;
import com.ran.algoritmovisibilidad.presenter.dto.SizeDto;
import com.ran.algoritmovisibilidad.presenter.service.SizeService;
import com.ran.algoritmovisibilidad.transformer.SizeEntityDtoTransformer;

@Service("SizeService")
public class SizeServiceImpl implements SizeService {

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    SizeEntityDtoTransformer sizeTransformer;
    @Autowired
    SizeDao sizeDao;
    
    @Override
    public List<SizeDto> getAll() {
        logger.info("Getting all the sizes");
        return sizeTransformer.toDto(sizeDao.getAll());
    }
}