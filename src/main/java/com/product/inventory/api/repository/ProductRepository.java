package com.product.inventory.api.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.product.inventory.api.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    // Method to find products by status
    List<Product> findByStatus(String status);
}