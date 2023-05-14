package com.ran.algoritmovisibilidad.presenter.service;

import java.util.List;

import com.ran.algoritmovisibilidad.presenter.dto.StockDto;

public interface StockService {
    List<StockDto> getAll();
    List<StockDto> getStocked();
    List<StockDto> getSoldOut();
    Boolean hasStock(Long sizeId);
    List<StockDto> getBySizeId(Long sizeId);
}