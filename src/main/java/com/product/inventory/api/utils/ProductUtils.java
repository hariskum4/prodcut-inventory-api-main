package com.product.inventory.api.utils;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.product.inventory.api.exception.ProductException;
import com.product.inventory.api.model.Product;
import com.product.inventory.api.model.ProductResponse;

public class ProductUtils {

	public static void validateProductData(Product product) {
		if (product.getName() == null || product.getName().trim().isEmpty()) {
			throw new ProductException(HttpStatus.BAD_REQUEST, "Product name cannot be blank");
		}

		if (product.getDescription() == null || product.getDescription().trim().isEmpty()) {
			throw new ProductException(HttpStatus.BAD_REQUEST, "Product description cannot be blank");
		}

		if (product.getPrice() <= 0) {
			throw new ProductException(HttpStatus.BAD_REQUEST, "Product price must be positive");
		}

		if (product.getQuantity() < 0) {
			throw new ProductException(HttpStatus.BAD_REQUEST, "Product quantity cannot be negative");
		}

		if (product.getStatus() == null || product.getStatus().trim().isEmpty()) {
			throw new ProductException(HttpStatus.BAD_REQUEST, "Product status cannot be blank");
		}
	}

	// Helper method to validate status
	public static boolean isValidStatus(String status) {
		// Define valid status values
		String[] validStatuses = { "available", "not-available" };

		// Check if the provided status is in the valid status array
		for (String validStatus : validStatuses) {
			if (status.equalsIgnoreCase(validStatus)) {
				return true; // Status is valid
			}
		}

		return false; // Status is invalid
	}

	public static void validateProductFields(Product product) {
		if (Objects.equals(product.getPrice(), null) || product.getPrice() <= 0) {
			throw new ProductException(HttpStatus.BAD_REQUEST, "Price cannot be null, empty, or non-positive");
		}

		if (Objects.equals(product.getQuantity(), null) || product.getQuantity() < 0) {
			throw new ProductException(HttpStatus.BAD_REQUEST, "Quantity cannot be null, empty, or negative");
		}

		if (Objects.equals(product.getStatus(), null) || product.getStatus().isEmpty()) {
			throw new ProductException(HttpStatus.BAD_REQUEST, "Status cannot be null or empty");
		}
	}
	
	 public static ResponseEntity<ProductResponse> handleProductException(ProductException e) {
	        ProductResponse errorResponse = new ProductResponse(false, e.getMessage(), e.getHttpStatus().toString());
	        return ResponseEntity.status(e.getHttpStatus()).body(errorResponse);
	    }
}