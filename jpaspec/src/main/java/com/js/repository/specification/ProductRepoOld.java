package com.js.repository.specification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.js.pojo.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class ProductRepoOld {

	@Autowired
	private EntityManager em;
	
	public List<Product> findBySimpleQuery(String name, float price, int id) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
		Root<Product> root = criteriaQuery.from(Product.class);

		Predicate namePredicate = criteriaBuilder.like(root.get("name"), "%" + name + "%");
		Predicate pricePredicate = criteriaBuilder.ge(root.get("price"), price);
		Predicate nameOrPrice = criteriaBuilder.or(namePredicate, pricePredicate);
		
		Predicate idPredicate = criteriaBuilder.ge(root.get("productId"), id);


		criteriaQuery.where(
				// (name or price) and (id greater or equals to)
				criteriaBuilder.and(nameOrPrice, idPredicate));

		TypedQuery<Product> query = em.createQuery(criteriaQuery);

		return query.getResultList();
	}
	
	
	
	
		
	public List<Product> findByName(String name) {
		
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
		Root<Product> root = criteriaQuery.from(Product.class);

		criteriaQuery.where(criteriaBuilder.like(root.get("name"), "%" + name + "%"));

		TypedQuery<Product> query = em.createQuery(criteriaQuery);

		return query.getResultList();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

}



















//CriteriaQuery<Product> criteriaQuery2 = criteriaBuilder.createQuery(Product.class);
//criteriaQuery2.where(nameOrPrice,idPredicate);
//TypedQuery<Product> query2= em.createQuery(criteriaQuery2);
//System.err.println(query2.getResultList());

