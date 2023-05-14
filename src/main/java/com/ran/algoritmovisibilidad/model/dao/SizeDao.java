package com.ran.algoritmovisibilidad.model.dao;

import java.util.List;

import com.ran.algoritmovisibilidad.model.entity.SizeEntity;

public interface SizeDao {
    List<SizeEntity> getAll();
    List<SizeEntity> getById(Long id);
    List<SizeEntity> getById(List<Long> ids);
    List<SizeEntity> getBackSoon();
    List<SizeEntity> getNotBackSoon();
    List<SizeEntity> getSpecial();
    List<SizeEntity> getSpecialStockedOrBackSoon();
    List<SizeEntity> getRegular();
    List<SizeEntity> getRegularStockedOrBackSoon();
    List<SizeEntity> getByProductId(Long productId);
}