package com.ran.algoritmovisibilidad.transformer;

import java.util.List;

public interface DtoDvoTransformer<T, V> {
    T toDto(V dvo);
    V toDvo(T dto);
    List<T> toDto(List<V> dvos);
    List<V> toDvo(List<T> dtos);
}