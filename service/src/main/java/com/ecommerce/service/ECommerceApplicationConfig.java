package com.ecommerce.service;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.ecommerce.dao.ECommerceDbConfig;

@Configuration
@ComponentScan(basePackages = {"com.ecommerce.service"})
@Import(ECommerceDbConfig.class)
@EnableCaching
public class ECommerceApplicationConfig {

}
