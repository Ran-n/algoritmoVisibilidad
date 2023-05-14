package com.ran.algoritmovisibilidad.presenter.service;

import java.util.List;

import com.ran.algoritmovisibilidad.presenter.dto.ProductDto;

public interface ProductService {
    List<ProductDto> getAll();
    List<ProductDto> getById(Long id);
    List<ProductDto> getById(List<Long> ids);
    List<ProductDto> getStocked();
    List<ProductDto> getSoldOut();
    List<ProductDto> getBackSoon();
    List<ProductDto> getNotBackSoon();
    List<ProductDto> getSpecial();
    List<ProductDto> getSpecialStockedOrBackSoon();
    List<ProductDto> getRegular();
    List<ProductDto> getRegularStockedOrBackSoon();
    List<ProductDto> getStrictlyRegular();
    Boolean hasStock(Long id);
    Boolean isBackSoon(Long id);
}