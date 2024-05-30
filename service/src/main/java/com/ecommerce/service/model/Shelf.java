package com.ecommerce.service.model;

public class Shelf {

    public Shelf(String productId, String relevancyScore) {
        this.productId = productId;
        this.relevancyScore = relevancyScore;
    }

    private String productId;
    private String relevancyScore;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getRelevancyScore() {
        return relevancyScore;
    }

    public void setRelevancyScore(String relevancyScore) {
        this.relevancyScore = relevancyScore;
    }
}
