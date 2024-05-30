package com.ecommerce.dao;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@ComponentScan(basePackages = {"com.ecommerce.dao"})
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "EntityManagerFactory",
        transactionManagerRef = "TransactionManager")
public class ECommerceDbConfig {

    @Bean(name = "EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean EntityManagerFactory(
            EntityManagerFactoryBuilder builder, DataSource dataSource) {
        return builder.dataSource(dataSource).packages("com.ecommerce.dao")
                        .build();
    }


    @Bean(name = "TransactionManager")
    public PlatformTransactionManager contentTransactionManager(
            @Qualifier("EntityManagerFactory") EntityManagerFactory EntityManagerFactory) {
        return new JpaTransactionManager(EntityManagerFactory);
    }

}