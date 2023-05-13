package com.ran.algoritmovisibilidad.presenter.dto;

public class StockDto {
    private SizeDto size;
    private Integer quantity;

    
    public StockDto() {}
    public StockDto(SizeDto size, Integer quantity) {
        this.size = size;
        this.quantity = quantity;
    }


    public SizeDto getSize() {
        return size;
    }
    public void setSize(SizeDto size) {
        this.size = size;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
