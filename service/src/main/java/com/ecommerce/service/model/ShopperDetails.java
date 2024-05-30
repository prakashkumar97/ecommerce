package com.ecommerce.service.model;

import java.util.List;

public class ShopperDetails {

    public ShopperDetails(String shopperId) {
        this.shopperId = shopperId;
    }
    private String shopperId;
    private List<Shelf> shelfList;

    public String getShopperId() {
        return shopperId;
    }

    public void setShopperId(String shopperId) {
        this.shopperId = shopperId;
    }

    public List<Shelf> getShelfList() {
        return shelfList;
    }

    public void setShelfList(List<Shelf> shelfList) {
        this.shelfList = shelfList;
    }
}
