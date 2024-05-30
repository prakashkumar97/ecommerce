package com.ecommerce.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import com.ecommerce.common.Category;


@Entity(name = "product_metadata")
@Table(name = "product_metadata", indexes = {
        @Index(name = "product_id_index", columnList = "product_id", unique = true),
        @Index(name = "category_brand_index", columnList = "category, brand")
})
public class ProductMetadata implements Serializable {

    @Id
    @Column(name = "product_id")
    private String productId;

    @Enumerated(EnumType.STRING)
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
