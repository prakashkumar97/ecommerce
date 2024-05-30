package com.ecommerce.dao.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.dao.entity.ProductMetadata;

@Transactional
public interface ProductRepository extends JpaRepository<ProductMetadata, String> {

}

