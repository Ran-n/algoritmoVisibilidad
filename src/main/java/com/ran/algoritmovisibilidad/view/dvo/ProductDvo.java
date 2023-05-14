package com.ran.algoritmovisibilidad.view.dvo;

public class ProductDvo {
    private Long id;


    public ProductDvo(Builder builder) {
        this.id = builder.id;
    }


    public Long getId() {
        return id;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Long id;
        
        public ProductDvo build() {
            return new ProductDvo(this);
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }
    }

    @Override
    public String toString() {
        return id.toString();
    }
}