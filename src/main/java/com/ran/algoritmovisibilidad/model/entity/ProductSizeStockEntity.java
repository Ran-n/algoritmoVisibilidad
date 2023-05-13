package com.ran.algoritmovisibilidad.model.entity;

public class ProductSizeStockEntity {
    ProductEntity product;
    SizeEntity size;
    StockEntity stock;

    
    public ProductSizeStockEntity() {}
    public ProductSizeStockEntity(ProductEntity product, SizeEntity size, StockEntity stock) {
        this.product = product;
        this.size = size;
        this.stock = stock;
    }

    
    public ProductEntity getProduct() {
        return product;
    }
    public void setProduct(ProductEntity product) {
        this.product = product;
    }
    
    public SizeEntity getSize() {
        return size;
    }
    public void setSize(SizeEntity size) {
        this.size = size;
    }
    
    public StockEntity getStock() {
        return stock;
    }
    public void setStock(StockEntity stock) {
        this.stock = stock;
    }
}