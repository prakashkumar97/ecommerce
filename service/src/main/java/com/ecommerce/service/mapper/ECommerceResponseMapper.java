package com.ecommerce.service.mapper;

import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ecommerce.dao.entity.ProductMetadata;
import com.ecommerce.service.model.ProductMetaData;
import com.ecommerce.service.model.Shelf;
import com.ecommerce.service.model.ShopperDetails;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ECommerceResponseMapper {

    public ProductMetaData mapProductMetaDataResponse(ProductMetadata productMetadata) {
        log.info("Mapping ProductMetadata entity to model response.");
        return new ProductMetaData(productMetadata.getProductId(), productMetadata.getCategory(), productMetadata.getBrand());

    }

    public ShopperDetails mapShopperDetailsResponse(com.ecommerce.dao.entity.ShopperDetails shopperDetails) {

        log.info("Mapping ShopperDetails entity to model response.");
        ShopperDetails shopperDetailsResponse = new ShopperDetails(shopperDetails.getShopperId());
        if(Objects.nonNull(shopperDetails)) {

            shopperDetailsResponse
                    .setShelfList(shopperDetails.getShelf()
                                    .stream()
                                    .map(shelf -> new Shelf(shelf.getProductId(), shelf.getRelevancyScore()))
                                    .collect(Collectors.toList()));

        }
        return shopperDetailsResponse;

    }

}
