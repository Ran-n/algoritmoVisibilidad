package com.ran.algoritmovisibilidad.transformer;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ran.algoritmovisibilidad.model.entity.StockEntity;
import com.ran.algoritmovisibilidad.presenter.dto.StockDto;

@Component
public class StockEntityDtoTransformer implements EntityDtoTransformer<StockEntity, StockDto> {
    
    @Autowired
    SizeEntityDtoTransformer sizeTransformer;
    
    @Override
    public StockEntity toEntity(final StockDto dto) {
        if (dto == null) return null;
        
        return new StockEntity(
            sizeTransformer.toEntity(dto.getSize()),
            dto.getQuantity()
        );
    }
    
    @Override
    public StockDto toDto(final StockEntity entity) {
        if (entity == null) return null;

        return new StockDto(
            sizeTransformer.toDto(entity.getSize()),
            entity.getQuantity()
        );
    }
    
    @Override
    public List<StockEntity> toEntity(List<StockDto> dtos) {
        if (dtos == null) return null;
        else if (dtos.isEmpty()) return Collections.emptyList();

        return dtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<StockDto> toDto(List<StockEntity> entities) {
        if (entities == null) return null;
        else if (entities.isEmpty()) return Collections.emptyList();
        
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}