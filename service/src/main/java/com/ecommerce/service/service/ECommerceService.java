package com.ecommerce.service.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ecommerce.common.Category;
import com.ecommerce.common.ECommerceErrorCode;
import com.ecommerce.dao.entity.ProductMetadata;
import com.ecommerce.dao.repository.ProductRepository;
import com.ecommerce.dao.repository.ShopperRepository;
import com.ecommerce.dao.specification.ShopperDetailSpecification;
import com.ecommerce.service.exceptions.ECommerceException;
import com.ecommerce.service.mapper.ECommerceResponseMapper;
import com.ecommerce.service.model.ProductMetaData;
import com.ecommerce.service.model.ShopperDetails;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class ECommerceService {

    @Autowired
    ShopperRepository shopperRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ECommerceResponseMapper eCommerceResponseMapper;

    @Autowired
    ShopperDetailSpecification shopperDetailSpecification;

    @Cacheable(key = "#shopperId", value = "filteredProducts")
    public com.ecommerce.dao.entity.ShopperDetails getCachedShopperDetails(String shopperId) {
        log.info("Filtering Products by shopperId {}", shopperId);
        try {
            return shopperRepository.findByShopperId(shopperId);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            throw new ECommerceException(e.getMessage(), e, ECommerceErrorCode.FAILED_TO_GET_PRODUCTS);
        }

    }

    public List<ProductMetaData> filterProducts(String shopperId, Category category, String brand,
                                                                        Integer limit) {
        com.ecommerce.dao.entity.ShopperDetails cachedShopperDetails = getCachedShopperDetails(shopperId);
        return cachedShopperDetails.getShelf() != null ? cachedShopperDetails.getShelf()
                .stream()
                .filter(shelf -> validateProductMetadata(category, brand, shelf.getProductMetadata()))
                .map(shelf -> eCommerceResponseMapper.mapProductMetaDataResponse(shelf.getProductMetadata()))
                .limit(limit)
                .collect(Collectors.toList()) : Collections.emptyList();

    }


    public ProductMetaData saveProductMetaData(ProductMetadata productMetadata) {
            log.info("Saving productMetadata with productId - {} into the database", productMetadata.getProductId());
            try {
                ProductMetadata savedProductMetadata = productRepository.save(productMetadata);
                return eCommerceResponseMapper.mapProductMetaDataResponse(savedProductMetadata);
            }
            catch (Exception e) {
                log.error(e.getMessage());
//                throw new ECommerceException(e.getMessage(), e, ECommerceErrorCode.FAILED_TO_SAVE_PRODUCT_METADATA);
                throw e;
            }
    }

    public ShopperDetails saveShopperDetails(com.ecommerce.dao.entity.ShopperDetails shopperDetails) {
            log.info("Saving ShopperDetails with shopperId - {} into the database" + shopperDetails.getShopperId());
            try {
                com.ecommerce.dao.entity.ShopperDetails savedShopperDetails = shopperRepository.save(shopperDetails);
                return eCommerceResponseMapper.mapShopperDetailsResponse(savedShopperDetails);
            }
            catch (Exception e) {
                log.error(e.getMessage());
                throw new ECommerceException(e.getMessage(), e, ECommerceErrorCode.FAILED_TO_SAVE_SHOPPER_DETAILS);
            }
    }

    private boolean validateProductMetadata(Category category, String brand, ProductMetadata productMetadata) {

        if(category != null && productMetadata.getCategory() != null && !category.name().equals(productMetadata.getCategory().name())) {
            return false;
        }
        if(brand != null && !brand.equals(productMetadata.getBrand())) {
            return false;
        }
        return true;
    }
}
