package com.app.service.mockito;

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

@ExtendWith(MockitoExtension.class)
class MockingTest {
	private static List<Product> productList;
	static {
		productList = Arrays.asList(new Product(1, "Watch", 4999.0f, null), 
									new Product(2, "Book", 99.0f, null),
									new Product(3, "T-Shirt", 499.0f, null));
	}
	
	@Mock
	private ProductService pService2;


	
	@Test
	void testGetAllProductWithAnnotation() {
		when(pService2.getAllProduct()).thenReturn(productList);
		System.err.println("With Annotation");
		System.out.println(pService2.getAllProduct());
	}

	
	
	// using constructor for Dependency Injection
	@Test
	void testGetAllProduct() {
		ProductRepository pRepoMock = mock(ProductRepository.class);
		ProductService pService1 = new ProductServiceImpl(pRepoMock);

		when(pRepoMock.findAll()).thenReturn(productList);
		System.out.println(pService1.getAllProduct());
	}

	


}












//@Mock
//private ProductRepository productRepository;
//
//@InjectMocks
//private ProductServiceImpl productService; // Dependencies (e.g., UserRepository) are injected here
//
//@Test
//void testFindUserById() {
//    MockitoAnnotations.openMocks(this);
//    Optional<Product> product = Optional.of(new Product(1, "Watch", 999.9f, null));
//    // Define mock behavior
//    when(productRepository.findById(1)).thenReturn(product);
//    
//    // Use the UserService, which has its dependencies injected
//    Product productReturned = productService.findUserById(1);
//    
//    // Assert the behavior
//    assertEquals("Watch", productReturned.getName());
//}






//// with Annotation
//@Test
//void testForPrivateMethodAW() {
//	when(pServicePrivate.privateMethod())
//	     .thenReturn("No Issue Calling private Methode");
//	System.err.println("AW Private Methode");
//	System.out.println(pServicePrivate.privateMethod());
//}
//@Test
//void testForPrivateMethodCI() {
//	ProductRepository pRepoMock = mock(ProductRepository.class);
//	ProductServiceImpl pService1 = new ProductServiceImpl(pRepoMock);
//
//	System.err.println("CI Private Methode");
//	System.out.println(pService1.privateMethod());
//}

