package com.ran.algoritmovisibilidad.presenter.service;

import java.util.List;

import com.ran.algoritmovisibilidad.presenter.dto.SizeDto;

public interface SizeService {
    List<SizeDto> getAll();
    List<SizeDto> getById(Long id);
    List<SizeDto> getById(List<Long>  ids);
    List<SizeDto> getStocked();
    List<SizeDto> getSoldOut();
    List<SizeDto> getBackSoon();
    List<SizeDto> getNotBackSoon();
    List<SizeDto> getSpecial();
    List<SizeDto> getSpecialStockedOrBackSoon();
    List<SizeDto> getRegular();
    List<SizeDto> getRegularStockedOrBackSoon();
    List<SizeDto> getByProductId(Long productId);
}