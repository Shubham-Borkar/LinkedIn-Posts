package com.mds.datasource.config;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = "com.mds.repo.h2", 
    entityManagerFactoryRef = "h2EntityManagerFactory",
    transactionManagerRef = "h2TransactionManager"
)
public class H2Config {
	 
    @Bean
    @ConfigurationProperties(prefix = "spring.h2.datasource")
    public DataSourceProperties h2DataSourceProperty() {
        return new DataSourceProperties();
    }
      
    @Bean
    @ConfigurationProperties(prefix = "spring.h2.datasource.configuration")
    public DataSource h2DataSource() {
        return h2DataSourceProperty()
        		.initializeDataSourceBuilder()
        		.type(HikariDataSource.class)
        		.build();
    }
    
    
    
    
    
    @Bean(name = "h2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean h2entityManagerFactory(
            EntityManagerFactoryBuilder builder) {
    	HashMap<String, Object> properties = new HashMap<String, Object>();
    	properties.put("hibernate.hbm2ddl.auto", "update");
    	properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        return builder
                .dataSource(h2DataSource())
                .properties(properties)
                .packages("com.mds.entity.h2")
                .persistenceUnit("h2")
                .build();
    }

    @Bean(name = "h2TransactionManager")
    public PlatformTransactionManager h2transactionManager(final @Qualifier("h2EntityManagerFactory") 
    						LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
        return new JpaTransactionManager(localContainerEntityManagerFactoryBean.getObject());
    }
    
    @Bean
    public EntityManagerFactoryBuilder h2entityManagerFactoryBuilder() {
       return new EntityManagerFactoryBuilder(new HibernateJpaVendorAdapter(), new HashMap<>(), null);
    }
}















//properties.put("hibernate.show_sql", true);  // Use Hibernate property here
//properties.put("hibernate.format_sql", true);  // Format SQL for readability
//properties.put("hibernate.use_sql_comments", true);  // Use SQL comments
