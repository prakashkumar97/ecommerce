package com.ecommerce.service.model;

import com.ecommerce.common.Category;

public class ProductMetaData {

    public ProductMetaData(String productId, Category category, String brand) {
        this.productId = productId;
        this.category = category;
        this.brand = brand;
    }

    private String productId;
    private Category category;
    private String brand;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
