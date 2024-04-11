package com.product.inventory.api.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.product.inventory.api.exception.ProductException;
import com.product.inventory.api.model.Product;
import com.product.inventory.api.repository.ProductRepository;
import com.product.inventory.api.service.ProductService;
import com.product.inventory.api.utils.ProductUtils;

@Service
public class ProductServiceImpl implements ProductService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

	private final ProductRepository productRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public Product createProduct(Product product) {
		// Check if the product with the same ID already exists
		LOGGER.info("Creating a new product: {}", product);
		Optional<Product> existingProduct = productRepository.findById(product.getId());
		if (existingProduct.isPresent()) {
			throw new ProductException(HttpStatus.CONFLICT, "Product with ID '" + product.getId() + "' already exists");
		}

		// Validate product data
		ProductUtils.validateProductData(product);
		LOGGER.info("Product created successfully: {}", product);
		return productRepository.save(product);
	}

	@Override
	public List<Product> addProducts(List<Product> products) {
		LOGGER.info("Inserting Multiple product:{}", products);
		List<String> existingIds = productRepository
				.findAllById(products.stream().map(Product::getId).collect(Collectors.toList())).stream()
				.map(Product::getId).collect(Collectors.toList());

		List<Product> productsToSave = products.stream().filter(product -> !existingIds.contains(product.getId()))
				.peek(ProductUtils::validateProductData).collect(Collectors.toList());

		List<String> conflictingIds = products.stream().map(Product::getId).filter(existingIds::contains)
				.collect(Collectors.toList());

		if (!conflictingIds.isEmpty()) {
			throw new ProductException(HttpStatus.CONFLICT, "Products with IDs " + conflictingIds + " already exist");
		}

		return productRepository.saveAll(productsToSave);
	}

	public Product getProductById(String id) {
		LOGGER.info("Retrieving product with ID: {}", id);

		// Retrieve the product with the given ID
		Optional<Product> optionalProduct = productRepository.findById(id);

		// Check if the product exists
		if (optionalProduct.isPresent()) {
			return optionalProduct.get(); // Return the product if found
		} else {
			LOGGER.error("Product with ID '{}' not found", id);
			throw new ProductException(HttpStatus.NOT_FOUND, "Product with ID '" + id + "' not found");
		}
	}

	@Override
	public Product updateProduct(String id, Product updatedProduct) {
		LOGGER.info("Updating product with ID: {}", id);

		// Check if the product with the given ID exists
		Product existingProduct = getProductById(id);

		// If the product does not exist, throw an error
		if (existingProduct == null) {
			LOGGER.error("Product with ID '{}' is not available for update", id);
			throw new ProductException(HttpStatus.NOT_FOUND, "The product you are trying to update is not available");
		}

		// Validate the fields of the updated product
		ProductUtils.validateProductFields(updatedProduct);

		// Update the existing product with the provided details
		existingProduct.setPrice(updatedProduct.getPrice());
		existingProduct.setQuantity(updatedProduct.getQuantity());
		existingProduct.setStatus(updatedProduct.getStatus());

		// Save and return the updated product
		LOGGER.info("Product updated successfully: {}", existingProduct);
		return productRepository.save(existingProduct);
	}

	@Override
	public Product deleteProduct(String id) {
		LOGGER.info("Deleting product with ID: {}", id);
		Optional<Product> optionalProduct = productRepository.findById(id);
		if (optionalProduct.isPresent()) {
			Product deletedProduct = optionalProduct.get();
			productRepository.deleteById(id);
			LOGGER.info("Product with ID '{}' deleted successfully", id);
			return deletedProduct;
		} else {
			LOGGER.error("Product with ID '{}' does not exist", id);
			throw new ProductException(HttpStatus.NOT_FOUND, "Product with ID '" + id + "' not found");
		}
	}

	@Override
	public List<Product> getAllProducts(int page, int pageSize) {
		LOGGER.info("Retrieving all products - Page: {}, PageSize: {}", page, pageSize);

		// Check if page or pageSize is invalid
		if (page <= 0 || pageSize <= 0 || pageSize > 100) {
			LOGGER.error("Invalid page or pageSize provided");
			throw new ProductException(HttpStatus.BAD_REQUEST, "Invalid page or pageSize provided");
		}

		Page<Product> productPage = productRepository.findAll(PageRequest.of(page - 1, pageSize));
		List<Product> products = productPage.getContent();
		LOGGER.info("Retrieved {} products", products.size());
		return products;
	}

	@Override
	public List<Product> getProductsByStatus(String status) {
		LOGGER.info("Retrieving products with status: {}", status);

		// Validate the status
		if (!ProductUtils.isValidStatus(status)) {
			LOGGER.error("Invalid status provided: {}", status);
			throw new ProductException(HttpStatus.BAD_REQUEST, "Invalid status provided: " + status);
		}
		List<Product> products = productRepository.findByStatus(status);
		LOGGER.info("Retrieved {} products with status '{}'", products.size(), status);
		return products;
	}

}
