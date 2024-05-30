package com.ecommerce.dao.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.ecommerce.common.Category;
import com.ecommerce.dao.entity.ProductMetadata;
import com.ecommerce.dao.entity.Shelf;
import com.ecommerce.dao.entity.ShopperDetails;

@Component
public class ShopperDetailSpecification {

    public Specification<ShopperDetails> findByProductMetadataSpecification(String shopperId, Category productCategory, String productBrand) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            //Join the tables
            Join<Shelf, ShopperDetails> shopperDetailsJoin = root.join("shelf");
            Join<ProductMetadata, Shelf> shelfProductMetadataJoin = shopperDetailsJoin.join("productMetadata");

            // Add filterations on the tables
            predicates.add(criteriaBuilder.equal(root.get("shopperId"), shopperId));
            predicates.add(criteriaBuilder.equal(shelfProductMetadataJoin.get("category"), productCategory));
            if(productBrand != null)
                predicates.add(criteriaBuilder.equal(shelfProductMetadataJoin.get("brand"), productBrand));

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }

}