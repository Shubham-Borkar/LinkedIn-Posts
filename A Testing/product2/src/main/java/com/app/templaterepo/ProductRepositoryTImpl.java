package com.app.templaterepo;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.app.pojo.Product;

import jakarta.persistence.EntityManager;

@Repository
public class ProductRepositoryTImpl implements ProductRepositoryT {
	@Autowired
	private EntityManager mgr;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJT;
	
	@Override
	public Product getProductByIdCR(int productId) {
		String query ="select p from Product p where p.productId=:pid";
		Product singleResult = mgr.createQuery(query,Product.class)
								  .setParameter("pid", productId)
								  .getSingleResult();
		return singleResult;
	}
	@Override
	public Boolean saveProductNQ(Product product) {
		String query ="insert into sbsb_product (name,price) values(:name,:price)";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("name", product.getName());
		paramMap.addValue("price", product.getPrice());
		
		Object object = namedParameterJT.execute(query, paramMap, p->p.executeUpdate());
		System.out.println(object.toString());
		return true;
	}
	
	@Override
	public Product getProductByIdNQ(int productId) {
		String query ="select * from sbsb_product where product_id=:pid";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("pid", productId);
		
		ResultSetExtractor<Product> rse = extracted();
		
		Product product = namedParameterJT.query(query, paramMap, rse);
		return product;
	}
	
	private ResultSetExtractor<Product> extracted() {
		ResultSetExtractor<Product> rse = rs->{
			Product toReturn=new Product();
			while(rs.next()) {
				toReturn.setProductId(rs.getInt("product_id"));
				toReturn.setName(rs.getString("name"));	
				toReturn.setPrice(rs.getFloat("price"));
			}
			return toReturn;
		};
		return rse;
	}
	
	@Override
	public List<Product> getAllProductNQ() {
		String query ="select * from sbsb_product";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		List<Product> toReturn=new ArrayList<>();
	
		ResultSetExtractor<List<Product>> rse = rs->{
			
			while(rs.next()) {
				toReturn.add(new Product(rs.getInt("product_id"),
						                 rs.getString("name"),
						                 rs.getFloat("price"),
						                 null,null,null));
			}
			return toReturn;
		};
		
		namedParameterJT.query(query, paramMap, rse);
		return toReturn;
	}
	
	@Override
	public int deleteProductNQ(int id) {
		String query ="delete from sbsb_product where product_id=:id";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("id", id);
		
		Integer count = namedParameterJT.execute(query, paramMap,p->p.executeUpdate());
		return count;
	}
	@Override
	public int updateProductNQ(Product product,int id) {
		String query ="update sbsb_product set name=:name, price=:price where product_id=:id";
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("name",product.getName());
		paramMap.addValue("price", product.getPrice());
		paramMap.addValue("id", id);
		
		Integer count = namedParameterJT.execute(query, paramMap,p->p.executeUpdate());
		return count;
	}
	

}
