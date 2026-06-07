package com.example.demo.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@EnableJpaRepositories(
        basePackages = "com.example.demo.repository",
        entityManagerFactoryRef = "entityManagerFactory1",
        transactionManagerRef = "transactionManager1"
)
@Configuration
public class PrimaryDatasource {

    @Primary
    @Bean(name = "datasource1")
    @ConfigurationProperties(prefix = "spring.datasource1")
    public DataSource getDataSource() {
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        return dataSourceBuilder.build();
    }

    @Primary
    @Bean(name = "entityManagerFactory1")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            @Qualifier("datasource1") DataSource dataSource,
            EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        return builder
                .dataSource(dataSource)
                .packages("com.example.demo.entity")
                .persistenceUnit("primary")
                .properties(properties)
                .build();
    }

    @Primary
    @Bean(name = "transactionManager1")
    public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory1") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}