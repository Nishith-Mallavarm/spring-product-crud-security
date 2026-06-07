package com.example.demo.config;

import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@EnableJpaRepositories(
        basePackages = "com.example.demo.repository2",
        entityManagerFactoryRef = "entityManagerFactory2",
        transactionManagerRef = "transactionManager2"
)
@Configuration
public class SecondaryDatasource {

    @Bean(name = "datasource2")
    @ConfigurationProperties(prefix = "spring.datasource2")
    public DataSource getDataSource() {
        DataSourceBuilder<?>  dataSourceBuilder = DataSourceBuilder.create();
        return dataSourceBuilder.build();
    }

    @Bean(name = "entityManagerFactory2")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(@Qualifier("datasource2") DataSource dataSource, EntityManagerFactoryBuilder builder) {
        Map<String,Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");

        return builder
                .dataSource(dataSource)
                .packages("com.example.demo.entity2")
                .properties(properties)
                .persistenceUnit("secondary")
                .build();
    }

    @Bean(name="transactionManager2")
    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory2") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
