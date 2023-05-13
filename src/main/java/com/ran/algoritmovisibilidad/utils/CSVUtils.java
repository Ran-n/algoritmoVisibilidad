package com.ran.algoritmovisibilidad.utils;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class CSVUtils {

    private static final Logger logger = LoggerFactory.getLogger(CSVUtils.class);

    public static <T> ArrayList<T> readCSV(final String filePath, final Class<T> clazz) {
        ArrayList<T> mappedCSV = new ArrayList<>();

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
        } catch (CsvValidationException e) {
            // Handle file reading exception
            logger.error("Validating the csv file {}.\nError type {}", filePath, e);
            e.printStackTrace();
        } catch (IOException e) {
            logger.error("Reading the csv file {}.\nError type {}", filePath, e);
            e.printStackTrace();
        }
        return mappedCSV;
    }

    private static <T> T createObjectFromLine(final String line, final Class<T> clazz) {
        try {
            Constructor<T> constructor = clazz.getConstructor(String.class);
            return constructor.newInstance(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
