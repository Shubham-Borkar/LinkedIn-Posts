package com.app.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.app.dto.ApiResponse;
import com.app.dto.ProductDto;
import com.app.pojo.Product;
import com.app.pojo.Review;
import com.app.repository.ProductRepository;
import com.app.repository.ReviewRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackOn = Exception.class)
public class ProductServiceImpl implements ProductService {
 //   commented for mocking,constructor injection needed
//	@Autowired
	private ProductRepository productRepo;
	public ProductServiceImpl(ProductRepository productRepo) {
		this.productRepo=productRepo;
    }
	
	@Autowired
	private ReviewRepository reviewRepo;
	
	@Autowired
	private ReviewService rservice;

	@Autowired
	private ModelMapper mapper;

	public List<Product> getAllProduct() {
		System.err.println("Service");

		return productRepo.findAll();
	}
	
	@Override
	public Product findProductById(int id) {

		return productRepo.findById(id).orElse(null);
	}

	@Override
	public Product editProductById(int id, Product product) {
		Optional<Product> optionalProduct = productRepo.findById(id);
		if (optionalProduct.isPresent()) {
			Product productRevived = optionalProduct.get();
			productRevived.setName(product.getName());
			productRevived.setPrice(product.getPrice());
			productRepo.save(productRevived);
			return productRevived;

		} else {
			return null;
		}

	}

	@Override
	public ApiResponse deleteById(int id) {

		if (productRepo.findById(id).isPresent()) {
			productRepo.deleteById(id);
			return new ApiResponse("Product with id " + id + " Deleted");
		} else {
			return new ApiResponse("Error while deleting product");
		}
	}

	@Override
	public List<Product> paginatedProduct(int pageindex, int elements) {
		PageRequest pagingCriteria = PageRequest.of(pageindex, elements);
		Page<Product> findAllbyPaging = productRepo.findAll(pagingCriteria);
		return findAllbyPaging.getContent();

	}

	@Override
	public List<Product> paginatedSortedProduct(int pageindex, int elements) {
		PageRequest pagingCriteria = PageRequest.of(pageindex, elements, Sort.by("price").descending());
		Page<Product> findAllbyPaging = productRepo.findAll(pagingCriteria);
		return findAllbyPaging.getContent();
	}

	@Override
	public Product addProduct(ProductDto productDto) {
		log.info("********* ProductDto Received ->{} ", productDto.toString());
		Product validProduct = mapper.map(productDto, Product.class);
		log.info("********* Valid Project Object,will be saved ->{} ", validProduct.toString());
		return validProduct;
//		Product savedProduct = productRepo.save(validProduct);
//		return savedProduct;
	}

	@Override
	public void saveProduct(Product product) throws  Exception{
	
		
		Review review = product.getReviews().get(0);
		product.setReviews(null);
		
		reviewRepo.save(review);
	//	Product product2 = productRepo.save(product);
	//	rservice.savereviewlist(product2);
		
	//	throw new Exception();
		
	}

	@Override
	public void saveProduct(List<Product> product) throws Exception {
		Product save = productRepo.save(product.get(0));
		System.err.println(save);
		int a=Integer.valueOf(product.get(0).getName());
		
		Product save2 = productRepo.save(product.get(1));
		System.err.println(save2);	
		
		reviewRepo.save(new Review(2, a, null, save2));
		
		if(1==a) {
			throw new Exception();
			}
	}

	
	 // Hibernate Fetch Behaviour
	  /*
		@Override
		public Product findProductById(int id) {
			Product product = productRepo.findById(id).orElse(null);
			product.getReviews().size();
			System.out.println("waiting session to close");
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(" session to closed");
			return product;
		}
	   */

}
