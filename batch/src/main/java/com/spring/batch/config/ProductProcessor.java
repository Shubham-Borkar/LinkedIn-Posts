package com.spring.batch.config;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.spring.batch.entity.Product;

@Component
public class ProductProcessor implements ItemProcessor<Product, Product>{

	@Override
	public Product process(Product item) throws Exception {
		System.out.println("thread ->"+Thread.currentThread().getName()+
				", processing the item id -> "+ item.getId());
		
		//validation, filtering, data transformation
		if((item.isProcessed()==true) || item.getId()%2==0) {
			return null;
		}
		return item;
	}

}






