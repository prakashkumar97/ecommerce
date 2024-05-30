package com.ecommerce.dao.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ecommerce.dao.entity.ProductMetadata;

@Transactional
public interface ProductRepository extends JpaRepository<ProductMetadata, String> {

}

