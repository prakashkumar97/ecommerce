package com.ecommerce.service.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.ecommerce.common.Category;
import com.ecommerce.dao.entity.ProductMetadata;
import com.ecommerce.dao.entity.Shelf;
import com.ecommerce.dao.repository.ProductRepository;
import com.ecommerce.dao.repository.ShopperRepository;

import com.ecommerce.dao.specification.ShopperDetailSpecification;
import com.ecommerce.service.mapper.ECommerceResponseMapper;
import com.ecommerce.service.model.ProductMetaData;
import com.ecommerce.service.model.ShopperDetails;


@Service
//@Slf4j
public class ECommerceService {

    @Autowired
    ShopperRepository shopperRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ECommerceResponseMapper eCommerceResponseMapper;

    @Autowired
    ShopperDetailSpecification shopperDetailSpecification;

    @Cacheable(key = "#shopperId + #category.toString() + #brand", value = "filteredProducts")
    public List<com.ecommerce.dao.entity.ShopperDetails> filterProducts(String shopperId, Category category, String brand) {
        Specification<com.ecommerce.dao.entity.ShopperDetails> shopperDetailsSpecification = shopperDetailSpecification
                .findByProductMetadataSpecification(shopperId, category, brand);
            return shopperRepository.findAll(shopperDetailsSpecification);

    }

    public List<com.ecommerce.dao.entity.ShopperDetails> filterProducts(String shopperId, Category category, String brand,
                                                                        Integer limit) {
        return new ArrayList<>();

    }


    public ProductMetaData saveProductMetaData(ProductMetadata productMetadata) {

        ProductMetadata savedProductMetadata =  productRepository.save(productMetadata);
        return eCommerceResponseMapper.mapProductMetaDataResponse(savedProductMetadata);

    }

    public ShopperDetails saveShopperDetails(com.ecommerce.dao.entity.ShopperDetails shopperDetails) {
        com.ecommerce.dao.entity.ShopperDetails savedShopperDetails =  shopperRepository.save(shopperDetails);
        return eCommerceResponseMapper.mapShopperDetailsResponse(savedShopperDetails);
    }
}
