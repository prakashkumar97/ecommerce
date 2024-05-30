package com.ecommerce.service.controller;

import java.util.List;

import javax.validation.constraints.Max;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.common.Category;
import com.ecommerce.dao.entity.ProductMetadata;
import com.ecommerce.service.model.ProductMetaData;
import com.ecommerce.service.model.ShopperDetails;
import com.ecommerce.service.service.ECommerceService;

@RestController
public class ECommerceController {

    @Autowired
    ECommerceService eCommerceService;

    @PostMapping("/saveShopperDetails")
    public ShopperDetails saveShopperDetails(@RequestBody com.ecommerce.dao.entity.ShopperDetails shopperDetails) {

        return eCommerceService.saveShopperDetails(shopperDetails);
    }

    @PostMapping("/saveProductMetadata")
    public ProductMetaData saveProductMetadata(@RequestBody ProductMetadata productMetadata) {

        return eCommerceService.saveProductMetaData(productMetadata);
    }

    @GetMapping("/getProductsByShopper")
    public List<ProductMetaData> getProductsByShopper(@RequestParam String shopperId,
                                                                              @RequestParam(required = false) Category category,
                                                                              @RequestParam(required = false) String brand,
                                                                              @RequestParam(required = false, defaultValue = "10")
                                                      @Max(value = 100, message = "Limit can not be more than 100") Integer limit) {
        return eCommerceService.filterProducts(shopperId, category, brand, limit);
    }

}
