package com.product.inventory.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.product.inventory.api.model.Product;

@Service
public interface ProductService {
    /**
     * Creates a new product.
     *
     * @param product The product to create.
     * @return The created product.
     */
    Product createProduct(Product product);

    /**
     * Adds multiple products.
     *
     * @param products The list of products to add.
     * @return The list of added products.
     */
    List<Product> addProducts(List<Product> products);

    /**
     * Retrieves a product by its ID.
     *
     * @param id The ID of the product to retrieve.
     * @return The product with the specified ID, or null if not found.
     */
    Product getProductById(String id);

    /**
     * Updates an existing product.
     *
     * @param id             The ID of the product to update.
     * @param updatedProduct The updated product information.
     * @return The updated product.
     */
    Product updateProduct(String id, Product updatedProduct);

    /**
     * Deletes a product by its ID.
     *
     * @param id The ID of the product to delete.
     */
    Product deleteProduct(String id);

    /**
     * Retrieves a list of products with pagination support.
     *
     * @param page     The page number (starting from 1).
     * @param pageSize The number of products per page.
     * @return The list of products for the specified page.
     */
    List<Product> getAllProducts(int page, int pageSize);

    /**
     * Retrieves a list of products by status.
     *
     * @param status The status of the products to retrieve.
     * @return The list of products with the specified status.
     */
    List<Product> getProductsByStatus(String status);
}