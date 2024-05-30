package com.ecommerce.service.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShopperDetails {

    public ShopperDetails(String shopperId) {
        this.shopperId = shopperId;
    }

    private String shopperId;
    private List<Shelf> shelfList;

}
