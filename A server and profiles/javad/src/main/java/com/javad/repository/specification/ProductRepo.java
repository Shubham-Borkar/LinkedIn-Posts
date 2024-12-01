package com.javad.repository.specification;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javad.pojo.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;


@Repository
public class ProductRepo {
@Autowired
private EntityManager em;
	
public List<Product> findBySimpleQuery(String name,float price,int id){
	CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
	CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
	Root<Product> root = criteriaQuery.from(Product.class);
	
	
	Predicate namePredicate = criteriaBuilder.like(root.get("name"), "%"+name+"%");	
	Predicate pricePredicate = criteriaBuilder.ge(root.get("price"), price);
	Predicate idPredicate = criteriaBuilder.ge(root.get("productId"), id);
	Predicate nameOrPrice = criteriaBuilder.or(namePredicate,pricePredicate);
	
	
	criteriaQuery.where(
			//(name or price) and id greater or equals to
			criteriaBuilder.and(nameOrPrice,idPredicate)			
			);
	
	TypedQuery<Product> query= em.createQuery(criteriaQuery);
	
	
//	CriteriaQuery<Product> criteriaQuery2 = criteriaBuilder.createQuery(Product.class);
//	criteriaQuery2.where(nameOrPrice,idPredicate);
//	TypedQuery<Product> query2= em.createQuery(criteriaQuery2);
//	System.err.println(query2.getResultList());

	
	return query.getResultList();
}

}
