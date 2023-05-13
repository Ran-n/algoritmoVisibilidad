package com.ran.algoritmovisibilidad.transformer;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ran.algoritmovisibilidad.model.entity.ProductEntity;
import com.ran.algoritmovisibilidad.presenter.dto.ProductDto;

@Component
public class ProductEntityDtoTransformer implements EntityDtoTransformer<ProductEntity, ProductDto> {
    
    @Override
    public ProductEntity toEntity(final ProductDto dto) {
        if (dto == null) return null;
        
        return new ProductEntity(
            dto.getId(),
            dto.getSequence()
            );
    }

    @Override
    public ProductDto toDto(final ProductEntity entity) {
        if (entity == null) return null;

        return new ProductDto(
            entity.getId(),
            entity.getSequence()
        );
    }

    @Override
    public List<ProductEntity> toEntity(List<ProductDto> dtos) {
        if (dtos == null) return null;
        else if (dtos.isEmpty()) return Collections.emptyList();

        return dtos
                .stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto> toDto(List<ProductEntity> entities) {
        if (entities == null) return null;
        else if (entities.isEmpty()) return Collections.emptyList();

        return entities
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}