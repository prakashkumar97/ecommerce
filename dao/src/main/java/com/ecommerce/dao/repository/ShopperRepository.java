package com.ecommerce.dao.repository;

import java.util.List;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ecommerce.dao.entity.ProductMetadata;
import com.ecommerce.dao.entity.Shelf;
import com.ecommerce.dao.entity.ShopperDetails;

@Transactional
public interface ShopperRepository extends JpaRepository<ShopperDetails, String>, JpaSpecificationExecutor<ShopperDetails> {
    ShopperDetails findByShopperId(String shopperId);

}

