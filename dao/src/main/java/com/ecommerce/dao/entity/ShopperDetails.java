package com.ecommerce.dao.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "shopper_details")
@Table(name = "shopper_details", indexes = {
        @Index(name = "shopper_id_index", columnList = "shopper_id", unique = true)
})
public class ShopperDetails implements Serializable {

    @Id
    @Column(name = "shopper_id")
    private String shopperId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "shelf_shopper_id")
    private List<Shelf> shelf;

    public String getShopperId() {
        return shopperId;
    }

    public void setShopperId(String shopperId) {
        this.shopperId = shopperId;
    }

    public List<Shelf> getShelf() {
        return shelf;
    }

    public void setShelf(List<Shelf> shelf) {
        this.shelf = shelf;
    }
}
