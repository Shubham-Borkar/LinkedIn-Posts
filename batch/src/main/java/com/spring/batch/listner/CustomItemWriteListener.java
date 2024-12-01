package com.spring.batch.listner;

import java.util.List;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.item.Chunk;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.spring.batch.entity.Product;

@Component
public class CustomItemWriteListener implements ItemWriteListener<Product> {
  
	@Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	
	@Override
	public void afterWrite(Chunk<? extends Product> items) {
		
		List<Integer> itemProcessed = items.getItems().stream().map(Product::getId).toList();
		if(itemProcessed.size()>0) {
			
			final String updateSql = "Update batch_product set processed = 1, datetime = now() where id IN (:ids)";
			MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource("ids", itemProcessed);
			int updateCount = namedParameterJdbcTemplate.update(updateSql, mapSqlParameterSource);
			System.out.println("Total Update count -> "+updateCount +" , ids -> "+ itemProcessed);
			
		}else {			
			System.out.println("No items to Update as of now");			
		}

	}
    
    
    

}
