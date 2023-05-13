package com.ran.algoritmovisibilidad.model.entity;

public class ItemEntity {
    private Long productId;
    private Long sizeId;
    private Integer sequence;
    private Integer quantity;
    private Boolean backSoon;
    private Boolean special;


    public Long getProductId() {
        return productId;
    }
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    
    public Long getSizeId() {
        return sizeId;
    }
    public void setSizeId(Long sizeId) {
        this.sizeId = sizeId;
    }
    
    public Integer getSequence() {
        return sequence;
    }
    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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