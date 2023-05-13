package com.ran.algoritmovisibilidad.model.entity;

public class StockEntity {
    private SizeEntity size;
    private Integer quantity;

    
    public StockEntity() {}
    public StockEntity(final SizeEntity size, final Integer quantity) {
        this.size = size;
        this.quantity = quantity;
    }
    public StockEntity(final String inputString) {
        String[] values = inputString.split(",");
        this.size = new SizeEntity(Long.parseLong(values[0]));
        this.quantity = Integer.parseInt(values[1]);
    }


    public SizeEntity getSize() {
        return size;
    }
    public void setSize(SizeEntity size) {
        this.size = size;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}