package com.ran.algoritmovisibilidad.utils;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class CsvUtils {

    private static final Logger logger = LoggerFactory.getLogger(CsvUtils.class);

    public static <T> List<T> readCSV(final String filePath, final Class<T> clazz) {
        logger.info("Reading the csv file {}", filePath);
        List<T> mappedCSV = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                final String actual_line = String.join(",", line);
                // ignore commented lines
                if (!actual_line.startsWith("#")) {
                    T obj = createObjectFromLine(actual_line, clazz);
                    if (obj != null) {
                        mappedCSV.add(obj);
                    }
                }
            }
        } catch (CsvValidationException | IOException e) {
            logger.error("While processing the csv file {}: {}", filePath, e.getMessage());
            e.printStackTrace();
        }
        return mappedCSV;
    }

    private static <T> T createObjectFromLine(final String line, final Class<T> clazz) {
        try {
            Constructor<T> constructor = clazz.getConstructor(String.class);
            return constructor.newInstance(line);
        } catch (Exception e) {
            logger.error("While creating the object {} from the line {}: {}", clazz.getName(), line, e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
