package com.app;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.app.config.ConfigClass;
import com.app.pojo.AutowiringBeanOne;
import com.app.pojo.AutowiringBeanTwo;
import com.app.pojo.CInjected;
import com.app.repository.ProductRepository;

@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	/**
	 * @return instance of model mapper
	 */
	@Bean
	public ModelMapper mapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}

	@Autowired
	private ConfigClass configurations;

	@Autowired
	private ProductRepository pRepo;
	/**
	 * @param ctx
	 * @return print all beans when Spring Boot Application run
	 */
	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
//		List<Product> nativeQuery = pRepo.nativeQuery();
//		List<Product> nativeQueryy = pRepo.nativeQueryyy();
		return args -> {
			
		
			// Configuration Value using Configuration Class
//			System.out.println('\n' + "Configuration using @ConfigurationProperties" + configurations.toString());
//			nativeQuery.forEach(n-> System.err.println(n.toString()));
//			nativeQueryy.forEach(n-> System.err.println(n.toString()));
		};

	}
	@Bean
	public CInjected getCInjected () {
		CInjected cInjected = new CInjected();
		System.out.println("Creating cin with value->"+cInjected.toString());
		return cInjected;
	}
	
	@Bean
	public  AutowiringBeanOne getAutowiringBeanOne(CInjected cin) {
		System.out.println("Creating bean one with value->"+cin.toString());
		return new AutowiringBeanOne(cin);	
	}
	@Bean
	public  AutowiringBeanTwo getAutowiringBeanTwo(CInjected cin) {
		System.out.println("Creating bean two with value->"+cin.toString());
		return new AutowiringBeanTwo(cin);	
	}

}
