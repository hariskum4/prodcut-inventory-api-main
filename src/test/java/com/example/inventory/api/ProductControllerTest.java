package com.example.inventory.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.product.inventory.api.controller.ProductController;
import com.product.inventory.api.model.Product;
import com.product.inventory.api.model.ProductResponse;
import com.product.inventory.api.service.ProductService;

class ProductControllerTest {
	 private static final Logger logger = LoggerFactory.getLogger(ProductControllerTest.class);
    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateProduct() {
    	logger.info("Starting testCreateProduct...");
    	 Product product = new Product();
         product.setId("1");
         product.setName("Test Product");
         product.setDescription("Description");
         product.setPrice(10.0);
         product.setQuantity(5);
         product.setStatus("available");
        when(productService.createProduct(product)).thenReturn(product);

        ResponseEntity<ProductResponse> response = productController.createProduct(product);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody().isSuccess());
        assertEquals("Product created", response.getBody().getMessage());
        assertEquals(product, response.getBody().getProducts());
        logger.info("testCreateProduct completed.");
    }

    @Test
    void testAddProducts() {
    	logger.info("Starting testAddProducts...");
        // Create a list of products
        List<Product> products = new ArrayList<>();
        
        Product product1 = new Product();
        product1.setId("1");
        product1.setName("Test Product 1");
        product1.setDescription("Description");
        product1.setPrice(10.0);
        product1.setQuantity(5);
        product1.setStatus("available");
        
        Product product2 = new Product();
        product2.setId("2");
        product2.setName("Test Product 2");
        product2.setDescription("Description");
        product2.setPrice(20.0);
        product2.setQuantity(10);
        product2.setStatus("available");

        products.add(product1);
        products.add(product2);

        // Mock the ProductService's addProducts method
        when(productService.addProducts(products)).thenReturn(products);

        // Call the controller method being tested
        ResponseEntity<ProductResponse> response = productController.addProducts(products);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody().isSuccess());
        assertEquals("Products created", response.getBody().getMessage());
        assertEquals(products, response.getBody().getProducts());
        logger.info("testAddProducts completed.");
    }


    @Test
    void testGetProductById() {
    	logger.info("Starting testGetProductById...");
        String productId = "1";
        
        Product product = new Product();
        product.setId(productId);
        product.setName("Test Product");
        product.setDescription("Description");
        product.setPrice(10.0);
        product.setQuantity(5);
        product.setStatus("available");

        when(productService.getProductById(productId)).thenReturn(product);

        ResponseEntity<ProductResponse> response = productController.getProductById(productId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody().isSuccess());
        assertEquals("Product found", response.getBody().getMessage());
        assertEquals(product, response.getBody().getProducts());
        logger.info("testGetProductById completed.");
    }

    @Test
    void testDeleteProduct() {
    	 logger.info("Starting testDeleteProduct...");
        String productId = "1";

        // Mock the ProductService's deleteProduct method
        when(productService.deleteProduct(productId)).thenReturn(null);

        // Call the controller method being tested
        ResponseEntity<ProductResponse> response = productController.deleteProduct(productId);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody().isSuccess());
        assertEquals("Product deleted", response.getBody().getMessage());
        assertEquals(null, response.getBody().getProducts());
        logger.info("testDeleteProduct completed.");
    }

    @Test
    void testGetAllProducts() {
    	logger.info("Starting testGetAllProducts...");
        // Create a list of products
        List<Product> products = new ArrayList<>();
        
        Product product1 = new Product();
        product1.setId("1");
        product1.setName("Test Product 1");
        product1.setDescription("Description");
        product1.setPrice(10.0);
        product1.setQuantity(5);
        product1.setStatus("available");
        
        Product product2 = new Product();
        product2.setId("2");
        product2.setName("Test Product 2");
        product2.setDescription("Description");
        product2.setPrice(20.0);
        product2.setQuantity(10);
        product2.setStatus("available");

        products.add(product1);
        products.add(product2);

        // Mock the ProductService's getAllProducts method
        when(productService.getAllProducts(1, 10)).thenReturn(products);

        // Call the controller method being tested
        ResponseEntity<ProductResponse> response = productController.getAllProducts(1, 10);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody().isSuccess());
        assertEquals("Products retrieved", response.getBody().getMessage());
        assertEquals(products, response.getBody().getProducts());
        logger.info("testGetAllProducts completed.");
    }

    @Test
    void testGetProductsByStatus() {
    	logger.info("Starting testGetProductsByStatus...");
        String status = "available";

        // Create a list of products
        List<Product> products = new ArrayList<>();
        
        Product product1 = new Product();
        product1.setId("1");
        product1.setName("Test Product 1");
        product1.setDescription("Description");
        product1.setPrice(10.0);
        product1.setQuantity(5);
        product1.setStatus("available");
        
        Product product2 = new Product();
        product2.setId("2");
        product2.setName("Test Product 2");
        product2.setDescription("Description");
        product2.setPrice(20.0);
        product2.setQuantity(10);
        product2.setStatus("available");

        products.add(product1);
        products.add(product2);

        // Mock the ProductService's getProductsByStatus method
        when(productService.getProductsByStatus(status)).thenReturn(products);

        // Call the controller method being tested
        ResponseEntity<ProductResponse> response = productController.getProductsByStatus(status);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.getBody().isSuccess());
        assertEquals("Products retrieved", response.getBody().getMessage());
        assertEquals(products, response.getBody().getProducts());
        logger.info("testGetProductsByStatus completed.");
    }
}