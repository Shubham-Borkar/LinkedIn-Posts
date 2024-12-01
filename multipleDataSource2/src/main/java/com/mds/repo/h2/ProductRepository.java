package com.mds.repo.h2;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mds.entity.h2.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer>, JpaSpecificationExecutor<Product> {


}
