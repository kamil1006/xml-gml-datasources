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
        entityManagerFactoryRef = "db4EntityManagerFactory",
        transactionManagerRef = "db4TransactionManager",
        basePackages = {"com.example.xmlgmldatasources.db4.repository"})
public class DataSourceDb4Config {

    //---------------------------------------------------------------------------------
   // @Primary
    @Bean(name = "db4DataSourceProperties")
    @ConfigurationProperties("spring.datasource-db4")
    public DataSourceProperties db4DataSourceProperties() {
        return new DataSourceProperties();
    }
    //---------------------------------------------------------------------------------
   // @Primary
    @Bean(name = "db4DataSource")
    @ConfigurationProperties("spring.datasource-db4.configuration")
    public DataSource db4DataSource(@Qualifier("db4DataSourceProperties") DataSourceProperties db4DataSourceProperties) {
        return db4DataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }
    //---------------------------------------------------------------------------------
    //@Primary
    @Bean(name = "db4EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean db4EntityManagerFactory(
            EntityManagerFactoryBuilder db4EntityManagerFactoryBuilder, @Qualifier("db4DataSource") DataSource db4DataSource) {

        Map<String, String> db4JpaProperties = new HashMap<>();

       // db4JpaProperties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");  h2
      //  db4JpaProperties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect"); mysql
     //   db4JpaProperties.put("hibernate.dialect", "net.ucanaccess.hibernate.dialect.UCanAccessDialect");
       // db4JpaProperties.put("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
        db4JpaProperties.put("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
     //   db4JpaProperties.put("hibernate.hbm2ddl.auto", "create-drop");

        return db4EntityManagerFactoryBuilder
                .dataSource(db4DataSource)
                .packages("com.example.xmlgmldatasources.db4.entity")
                .persistenceUnit("db4DataSource")
                .properties(db4JpaProperties)
                .build();
    }
    //---------------------------------------------------------------------------------
   // @Primary
    @Bean(name = "db4TransactionManager")
    public PlatformTransactionManager db4TransactionManager(
            @Qualifier("db4EntityManagerFactory") EntityManagerFactory db4EntityManagerFactory) {

        return new JpaTransactionManager(db4EntityManagerFactory);
    }



}
