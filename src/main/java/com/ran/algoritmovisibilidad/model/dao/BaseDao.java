package com.ran.algoritmovisibilidad.model.dao;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import com.ran.algoritmovisibilidad.utils.CsvUtils;

public abstract class BaseDao {

    private static final Logger logger = LoggerFactory.getLogger(BaseDao.class);

    protected static <T> List<T> loadData(String filePath, final Class<T> clazz) {
        try {
            filePath = new ClassPathResource(filePath).getFile().getAbsolutePath();
            return CsvUtils.readCSV(filePath, clazz);
        } catch (final IOException e) {
            logger.error("During loading of csv file {}: {}", filePath, e.getMessage());
            e.printStackTrace();
        }
        return Collections.emptyList();
    }
}