package com.ecommerce.service.mapper;

import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.ecommerce.dao.entity.ProductMetadata;
import com.ecommerce.service.model.ProductMetaData;
import com.ecommerce.service.model.Shelf;
import com.ecommerce.service.model.ShopperDetails;

@Component
public class ECommerceResponseMapper {

    public ProductMetaData mapProductMetaDataResponse(ProductMetadata productMetadata) {

        return new ProductMetaData(productMetadata.getProductId(), productMetadata.getCategory(),
                                   productMetadata.getBrand());

    }

    public ShopperDetails mapShopperDetailsResponse(com.ecommerce.dao.entity.ShopperDetails shopperDetails) {

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
