package com.spring.batch.config;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.concurrent.ThreadPoolExecutor;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.support.DatabaseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.task.ThreadPoolTaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import com.spring.batch.entity.Product;
import com.spring.batch.listner.CustomItemWriteListener;

@EnableBatchProcessing
@Configuration
public class ExportProductJobConfig {
	
	
	
	
	
	@Autowired 
	private JobRepository jobRepository;
	

	
	
	
	@Bean
	public Job dbToFileBatchJob(Step fromProductTableToFile) {
		return new JobBuilder("dbToFileBatchJob",jobRepository)
				.start(fromProductTableToFile)
				.build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Autowired
	private PlatformTransactionManager platformTransactionManager;
	
	
	@Autowired
	private ProductProcessor productProcessor;
	
	@Autowired
	private CustomItemWriteListener customItemWriteListener;
	
	@Bean
	public Step fromProductTableToFile(FlatFileItemWriter<Product> flatFileItemWriter, JdbcPagingItemReader<Product> productJdbcPagingItemReader) {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutorBuilder()
				.corePoolSize(4)
				.maxPoolSize(4)
				.build();
		executor.afterPropertiesSet();
		
		return new StepBuilder("from db to File",jobRepository)
				.<Product,Product>chunk(10, platformTransactionManager)
				//.reader(productJdbcCursorReader())
				.reader(productJdbcPagingItemReader)
				.processor(productProcessor)
				.writer(flatFileItemWriter)
				.listener(customItemWriteListener) 
				//.taskExecutor(executor) //Parallel chunk processing
				.build();
				
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//Option 1
//	@Bean
//	public JdbcCursorItemReader<Product> productJdbcCursorReader(){
//		final String sql="Select id, price, name, processed, datetime from batch_product";
//		return new JdbcCursorItemReaderBuilder<Product>()
//				.name("product reader - JdbcCursor")
//				.dataSource(dataSource)
//				.sql(sql)
//				.fetchSize(10)
//				.rowMapper((rs, rowNum) -> new Product(
//	                    rs.getInt("id"),
//	                    rs.getFloat("price"),
//	                    rs.getString("name"),
//	                    rs.getBoolean("processed"),
//	                    rs.getObject("datetime", LocalDateTime.class) 
//	            ))
//				.build();		
//	}
	
	//Option2	
	@Bean
	public JdbcPagingItemReader<Product> productJdbcPagingItemReader(PagingQueryProvider pagingQueryProvider){
		return new JdbcPagingItemReaderBuilder<Product>()
				.name("product reader - JdbcPagingItem")
				.dataSource(dataSource)
				.queryProvider(pagingQueryProvider)
				.rowMapper((rs, rowNum) -> new Product(
	                    rs.getInt("id"),
	                    rs.getFloat("price"),
	                    rs.getString("name"),
	                    rs.getBoolean("processed"),
	                    rs.getObject("datetime", LocalDateTime.class) 
	            ))
				.pageSize(4)
				.build();
	}
	
	
	
	
	
	@Autowired
	private DataSource dataSource;
	
	
	
	
	
	@Bean
	public SqlPagingQueryProviderFactoryBean quertProvider() {
		
		
		SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
		queryProvider.setSelectClause("Select id, price, name, processed, datetime ");
		queryProvider.setFromClause("from batch_product ");
		queryProvider.setWhereClause("Where 1=1");
		queryProvider.setDataSource(dataSource);
		queryProvider.setDatabaseType(DatabaseType.MYSQL.name());
		queryProvider.setSortKeys(Collections.singletonMap("id", Order.ASCENDING));
		
		return queryProvider;					 
	}
	
	
	
	
	
	
	
	
	
	
	
	@Bean
	@StepScope //to access value using spell
	public FlatFileItemWriter<Product> flatFileItemWriter(@Value("#{jobParameters['output.file.name']}") String outputFile){
		return new FlatFileItemWriterBuilder<Product>()
				.name("Product file writer")
				.resource(new FileSystemResource(outputFile))
				.headerCallback(writer -> writer.append("Header of File"))
				.delimited()
				.delimiter("|")
				.sourceType(Product.class)
				.names("id", "price", "name", "processed", "datetime")
				.shouldDeleteIfEmpty(Boolean.TRUE)
				.append(Boolean.TRUE)
				.build();		
	}
	

}















