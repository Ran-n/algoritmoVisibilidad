package com.ran.algoritmovisibilidad.model.entity;

public class ProductEntity {
    private Long id;
    private Integer sequence;


    public ProductEntity() {}
    public ProductEntity(final Long id) {
        this.id = id;
    }
    public ProductEntity(final Long id, final Integer sequence) {
        this.id = id;
        this.sequence = sequence;
    }
    public ProductEntity(final String inputString) {
        final String[] values = inputString.split(",");
        this.id = Long.parseLong(values[0]);
        this.sequence = Integer.parseInt(values[1]);
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