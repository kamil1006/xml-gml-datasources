package com.example.xmlgmldatasources.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "thirdEntityManagerFactory",
        transactionManagerRef = "thirdTransactionManager",
        basePackages = {"com.example.xmlgmldatasources.db3.repository"})
public class DataSourceThirdConfig {

    //---------------------------------------------------------------------------------
   // @Primary
    @Bean(name = "thirdDataSourceProperties")
    @ConfigurationProperties("spring.datasource-third")
    public DataSourceProperties thirdDataSourceProperties() {
        return new DataSourceProperties();
    }
    //---------------------------------------------------------------------------------
   // @Primary
    @Bean(name = "thirdDataSource")
    @ConfigurationProperties("spring.datasource-third.configuration")
    public DataSource thirdDataSource(@Qualifier("thirdDataSourceProperties") DataSourceProperties thirdDataSourceProperties) {
        return thirdDataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
    //---------------------------------------------------------------------------------
    //@Primary
    @Bean(name = "thirdEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean thirdEntityManagerFactory(
            EntityManagerFactoryBuilder thirdEntityManagerFactoryBuilder, @Qualifier("thirdDataSource") DataSource thirdDataSource) {

        Map<String, String> thirdJpaProperties = new HashMap<>();

       // thirdJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");  h2
        thirdJpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        thirdJpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");

        return thirdEntityManagerFactoryBuilder
                .dataSource(thirdDataSource)
                .packages("com.example.xmlgmldatasources.db3.entity")
                .persistenceUnit("thirdDataSource")
                .properties(thirdJpaProperties)
                .build();
    }
    //---------------------------------------------------------------------------------
   // @Primary
    @Bean(name = "thirdTransactionManager")
    public PlatformTransactionManager thirdTransactionManager(
            @Qualifier("thirdEntityManagerFactory") EntityManagerFactory thirdEntityManagerFactory) {

        return new JpaTransactionManager(thirdEntityManagerFactory);
    }



}
