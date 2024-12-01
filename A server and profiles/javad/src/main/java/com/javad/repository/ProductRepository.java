package com.javad.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.javad.pojo.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>, JpaSpecificationExecutor<Product> {


}
