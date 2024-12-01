package com.app.mockito;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.app.pojo.Product;
import com.app.repository.ProductRepository;
import com.app.service.ProductService;
import com.app.service.ProductServiceImpl;


//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class MockingGetAllProduct {
	private static List<Product> productList;
	static{
		productList= Arrays.asList(new Product(1, "Watch",4999.0f, null,null,null),
						new Product(2, "Book",99.0f, null,null,null),
						new Product(3, "T-Shirt",499.0f, null,null,null));		
	}
	
	@Mock
	private ProductService pService2;
	

	//with Annotation
	@Test
	void testGetAllProductWithAnnotation() {	
		when(pService2.getAllProduct())
	    .thenReturn(productList);
	System.err.println("With Annotation");
	System.out.println(pService2.getAllProduct());
	}
	
	
	//without Annotation, we have commented @Autowired and put normal constructor  
	//for injection
	@Test
	void testGetAllProduct() {
		ProductRepository pRepoMock = mock(ProductRepository.class);
		ProductService pService1=new ProductServiceImpl(pRepoMock);
		
		when(pRepoMock.findAll())
		    .thenReturn(productList);
	System.err.println("Without Annotation");
	System.out.println(pService1.getAllProduct());
	}
	


}

