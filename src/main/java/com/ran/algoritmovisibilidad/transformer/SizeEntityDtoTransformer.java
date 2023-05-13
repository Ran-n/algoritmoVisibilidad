package com.ran.algoritmovisibilidad.transformer;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ran.algoritmovisibilidad.model.entity.SizeEntity;
import com.ran.algoritmovisibilidad.presenter.dto.SizeDto;

@Component
public class SizeEntityDtoTransformer implements EntityDtoTransformer<SizeEntity, SizeDto> {

    @Autowired
    ProductEntityDtoTransformer productTransformer;
    
    @Override
    public SizeEntity toEntity(final SizeDto dto) {
        if (dto == null) return null;
        
        return new SizeEntity(
            dto.getId(),
            productTransformer.toEntity(dto.getProduct()),
            dto.getBackSoon(),
            dto.getSpecial()
        );
    }

    @Override
    public SizeDto toDto(final SizeEntity entity) {
        if (entity == null) return null;

        return new SizeDto(
            entity.getId(),
            productTransformer.toDto(entity.getProduct()),
            entity.getBackSoon(),
            entity.getSpecial()
        );
    }
    
    @Override
    public List<SizeEntity> toEntity(List<SizeDto> dtos) {
        if (dtos == null) return null;
        else if (dtos.isEmpty()) return Collections.emptyList();

        return dtos
                .stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<SizeDto> toDto(List<SizeEntity> entities) {
        if (entities == null) return null;
        else if (entities.isEmpty()) return Collections.emptyList();

        return entities
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    
}