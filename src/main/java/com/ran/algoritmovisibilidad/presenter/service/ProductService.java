package com.ran.algoritmovisibilidad.presenter.service;

import java.util.List;

import com.ran.algoritmovisibilidad.presenter.dto.ProductDto;

public interface ProductService {
    List<ProductDto> getAll();
}