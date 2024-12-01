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
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;



@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.mds.repo.mysql", 
					   entityManagerFactoryRef = "mysqlEntityManagerFactory", 
					   transactionManagerRef = "mysqlTransactionManager")
public class MysqlConfig {

	@Bean
	@ConfigurationProperties(prefix = "spring.mysql.datasource")
	public DataSourceProperties mysqlDataSourceProperty() {
		return new DataSourceProperties();
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.mysql.datasource.configuration")
	public DataSource mysqlDataSource() {
		return mysqlDataSourceProperty().initializeDataSourceBuilder().type(HikariDataSource.class).build();
	}

	@Bean(name = "mysqlEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean mysqlentityManagerFactory(EntityManagerFactoryBuilder builder) {
		HashMap<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.hbm2ddl.auto", "create");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		return builder
				.dataSource(mysqlDataSource())
			    .properties(properties)
				.packages("com.mds.entity.mysql")
				.persistenceUnit("mysql")
				.build();
	}

	@Bean(name = "mysqlTransactionManager")
	public PlatformTransactionManager mysqltransactionManager(final @Qualifier("mysqlEntityManagerFactory") 
							LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean) {
		return new JpaTransactionManager(localContainerEntityManagerFactoryBean.getObject());
	}

}














//properties.put("hibernate.globally_quoted_identifiers", true);	
//properties.put("hibernate.show_sql", true);  // Use Hibernate property here
//properties.put("hibernate.format_sql", true);  // Format SQL for readability
//properties.put("hibernate.use_sql_comments", true);  // Use SQL comments
