package com.ecommerce.dao.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ecommerce.dao.entity.ShopperDetails;

@Transactional
public interface ShopperRepository extends JpaRepository<ShopperDetails, String>, JpaSpecificationExecutor<ShopperDetails> {
    ShopperDetails findByShopperId(String shopperId);

}

