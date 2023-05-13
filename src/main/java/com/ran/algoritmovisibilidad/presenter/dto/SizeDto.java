package com.ran.algoritmovisibilidad.presenter.dto;

public class SizeDto {
    private Long id;
    private ProductDto product;
    private Boolean backSoon;
    private Boolean special;


    public SizeDto() {}
    public SizeDto(Long id, ProductDto product, Boolean backSoon, Boolean special) {
        this.id = id;
        this.product = product;
        this.backSoon = backSoon;
        this.special = special;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    public ProductDto getProduct() {
        return product;
    }
    public void setProduct(ProductDto product) {
        this.product = product;
    }
    
    public Boolean getBackSoon() {
        return backSoon;
    }
    public void setBackSoon(Boolean backSoon) {
        this.backSoon = backSoon;
    }
    
    public Boolean getSpecial() {
        return special;
    }
    public void setSpecial(Boolean special) {
        this.special = special;
    }
}