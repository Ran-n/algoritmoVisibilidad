package com.ran.algoritmovisibilidad.transformer;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ran.algoritmovisibilidad.presenter.dto.ProductDto;
import com.ran.algoritmovisibilidad.view.dvo.ProductDvo;

@Component
public class ProductDtoDvoTransformer implements DtoDvoTransformer<ProductDto, ProductDvo> {

    @Override
    public ProductDto toDto(ProductDvo dvo) {
        if (dvo == null) return null;

        return new ProductDto(dvo.getId(), null);
    }

    @Override
    public ProductDvo toDvo(ProductDto dto) {
        if (dto == null) return null;

        return ProductDvo.builder()
                .id(dto.getId())
                .build();
    }

    @Override
    public List<ProductDto> toDto(List<ProductDvo> dvos) {
        if (dvos == null) return null;
        else if (dvos.isEmpty()) return Collections.emptyList();
        
        return dvos.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
    

    @Override
    public List<ProductDvo> toDvo(List<ProductDto> dtos) {
        if (dtos == null) return null;
        else if (dtos.isEmpty()) return Collections.emptyList();

        return dtos.stream()
                .map(this::toDvo)
                .collect(Collectors.toList());
    }
    
}
