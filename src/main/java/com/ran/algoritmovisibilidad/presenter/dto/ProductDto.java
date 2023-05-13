package com.ran.algoritmovisibilidad.presenter.dto;

public class ProductDto {
    private Long id;
    private Integer sequence;


    public ProductDto() {}
    public ProductDto(Long id, Integer sequence) {
        this.id = id;
        this.sequence = sequence;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getSequence() {
        return sequence;
    }
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}