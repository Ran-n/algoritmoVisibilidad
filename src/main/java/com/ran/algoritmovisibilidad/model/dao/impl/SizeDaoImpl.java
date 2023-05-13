package com.ran.algoritmovisibilidad.model.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ran.algoritmovisibilidad.model.dao.BaseDao;
import com.ran.algoritmovisibilidad.model.dao.SizeDao;
import com.ran.algoritmovisibilidad.model.entity.SizeEntity;

@Repository("SizeDao")
public class SizeDaoImpl extends BaseDao implements SizeDao {
    
    private static final List<SizeEntity> sizes = loadData("data/size.csv", SizeEntity.class);
    
    @Override
    public List<SizeEntity> getAll() {
        return sizes;
    }
}