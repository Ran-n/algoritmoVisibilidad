package com.ran.algoritmovisibilidad.transformer;

import java.util.List;

public interface EntityDtoTransformer<E, D> {
    E toEntity(D dto);
    D toDto(E entity);
    List<E> toEntity(List<D> dtos);
    List<D> toDto(List<E> entities);
}