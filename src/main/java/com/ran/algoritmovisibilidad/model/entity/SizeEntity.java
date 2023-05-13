package com.ran.algoritmovisibilidad.model.entity;

public class SizeEntity {
    private Long id;
    private ProductEntity product;
    private Boolean backSoon;
    private Boolean special;

    
    public SizeEntity() {}
    public SizeEntity(final Long id) {
        this.id = id;
    }
    public SizeEntity(final Long id, final ProductEntity product, final Boolean backSoon, final Boolean special) {
        this.id = id;
        this.product = product;
        this.backSoon = backSoon;
        this.special = special;
    }
    public SizeEntity(final String inputString) {
        final String[] values = inputString.split(",");
        this.id = Long.parseLong(values[0]);
        this.product = new ProductEntity(Long.parseLong(values[1]));
        this.backSoon = Boolean.parseBoolean(values[2]);
        this.special = Boolean.parseBoolean(values[3]);
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public ProductEntity getProduct() {
        return product;
    }
    public void setProduct(ProductEntity product) {
        this.product = product;
    }
    
    public Boolean isBackSoon() {
        return backSoon;
    }
    public void setBackSoon(Boolean backSoon) {
        this.backSoon = backSoon;
    }
    
    public Boolean isSpecial() {
        return special;
    }
    public void setSpecial(Boolean special) {
        this.special = special;
    }
}