package com.example.inventory.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.product.inventory.api.controller.ProductController;
import com.product.inventory.api.exception.ProductException;
import com.product.inventory.api.model.Product;
import com.product.inventory.api.model.ProductResponse;
import com.product.inventory.api.service.ProductService;

class ProductControllerTest {

    private ProductController productController;
    private ProductService productServiceMock;

    @BeforeEach
    void setUp() {
        productServiceMock = mock(ProductService.class);
        productController = new ProductController(productServiceMock);
    }

    @Test
    void testCreateProduct() {
        Product mockProduct = new Product();
        mockProduct.setId("1");
        mockProduct.setName("Test Product");
        mockProduct.setPrice(10.0);
        mockProduct.setQuantity(100);
        mockProduct.setStatus("available");

        when(productServiceMock.createProduct(mockProduct)).thenReturn(mockProduct);

        ResponseEntity<?> responseEntity = productController.createProduct(mockProduct);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(true, ((ProductResponse) responseEntity.getBody()).isSuccess());
        assertEquals("Product created", ((ProductResponse) responseEntity.getBody()).getMessage());
    }

    @Test
    void testAddProducts() {
        List<Product> mockProducts = new ArrayList<>();
        Product product1 = new Product();
        product1.setId("1");
        product1.setName("Test Product 1");
        product1.setPrice(10.0);
        product1.setQuantity(100);
        product1.setStatus("available");

        Product product2 = new Product();
        product2.setId("2");
        product2.setName("Test Product 2");
        product2.setPrice(20.0);
        product2.setQuantity(200);
        product2.setStatus("available");

        mockProducts.add(product1);
        mockProducts.add(product2);

        when(productServiceMock.addProducts(mockProducts)).thenReturn(mockProducts);

        ResponseEntity<?> responseEntity = productController.addProducts(mockProducts);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(true, ((ProductResponse) responseEntity.getBody()).isSuccess());
        assertEquals("Products created", ((ProductResponse) responseEntity.getBody()).getMessage());
    }

    @Test
    void testGetProductById() {
        Product mockProduct = new Product();
        mockProduct.setId("1");
        mockProduct.setName("Test Product");
        mockProduct.setPrice(10.0);
        mockProduct.setQuantity(100);
        mockProduct.setStatus("available");

        when(productServiceMock.getProductById("1")).thenReturn(mockProduct);

        ResponseEntity<?> responseEntity = productController.getProductById("1");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(true, ((ProductResponse) responseEntity.getBody()).isSuccess());
        assertEquals("Product found", ((ProductResponse) responseEntity.getBody()).getMessage());
        assertEquals(mockProduct, ((ProductResponse) responseEntity.getBody()).getProducts());
    }

    @Test
    void testUpdateProduct() {
        Product mockProduct = new Product();
        mockProduct.setId("1");
        mockProduct.setName("Test Product");
        mockProduct.setPrice(10.0);
        mockProduct.setQuantity(100);
        mockProduct.setStatus("available");

        when(productServiceMock.updateProduct("1", mockProduct)).thenReturn(mockProduct);

        ResponseEntity<?> responseEntity = productController.updateProduct("1", mockProduct);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(true, ((ProductResponse) responseEntity.getBody()).isSuccess());
        assertEquals("Product updated", ((ProductResponse) responseEntity.getBody()).getMessage());
    }

    @Test
    void testDeleteProduct() {
        when(productServiceMock.deleteProduct("1")).thenReturn(new Product());

        ResponseEntity<?> responseEntity = productController.deleteProduct("1");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(true, ((ProductResponse) responseEntity.getBody()).isSuccess());
        assertEquals("Product deleted", ((ProductResponse) responseEntity.getBody()).getMessage());
    }

    @Test
    void testGetAllProducts() {
        List<Product> mockProducts = new ArrayList<>();
        when(productServiceMock.getAllProducts(1, 10)).thenReturn(mockProducts);

        ResponseEntity<?> responseEntity = productController.getAllProducts(1, 10);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(true, ((ProductResponse) responseEntity.getBody()).isSuccess());
        assertEquals("Products retrieved", ((ProductResponse) responseEntity.getBody()).getMessage());
        assertEquals(mockProducts, ((ProductResponse) responseEntity.getBody()).getProducts());
    }

    @Test
    void testGetProductsByStatus() {
        List<Product> mockProducts = new ArrayList<>();
        when(productServiceMock.getProductsByStatus("available")).thenReturn(mockProducts);

        ResponseEntity<?> responseEntity = productController.getProductsByStatus("available");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(true, ((ProductResponse) responseEntity.getBody()).isSuccess());
        assertEquals("Products retrieved", ((ProductResponse) responseEntity.getBody()).getMessage());
        assertEquals(mockProducts, ((ProductResponse) responseEntity.getBody()).getProducts());
    }

    @Test
    void testFallbackMethod() {
        ResponseEntity<ProductResponse> responseEntity = productController.productInventoryFallbackMethod(new ProductException(HttpStatus.TOO_MANY_REQUESTS, "Too Many Requests"));

        assertEquals(HttpStatus.TOO_MANY_REQUESTS, responseEntity.getStatusCode());
        assertEquals(false, responseEntity.getBody().isSuccess());
        assertEquals("Inventory service does not permit furthur request, please try after some time", responseEntity.getBody().getMessage());
    }
}