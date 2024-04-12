package com.product.inventory.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.inventory.api.exception.ProductException;
import com.product.inventory.api.model.Product;
import com.product.inventory.api.model.ProductErrorResponse;
import com.product.inventory.api.model.ProductResponse;
import com.product.inventory.api.service.ProductService;
import com.product.inventory.api.utils.ProductConstants;
import com.product.inventory.api.utils.ProductUtils;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/products")
@Tag(name = ProductConstants.PRODUCT_CONTROLLER, description = ProductConstants.PRODUCT_CONTROLLER_DESCRIPTION)
public class ProductController {
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	private final ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@Operation(summary = ProductConstants.CREATE_PRODUCT_OPERATION_SUMMARY, description = ProductConstants.CREATE_PRODUCT_OPERATION_DESCRIPTION)
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = ProductConstants.CREATE_PRODUCT_RESPONSE_CODE_200_DESCRIPTION, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponse.class))),
	    @ApiResponse(responseCode = "400", description = ProductConstants.CREATE_PRODUCT_RESPONSE_CODE_400_DESCRIPTION, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductErrorResponse.class)))
	})
	@PostMapping("/addProduct")
	@RateLimiter(name = ProductConstants.CREATE_PRODUCT_LIMIT, fallbackMethod = ProductConstants.FALLBACK_INVENTORY_METHOD)
	public ResponseEntity<?> createProduct(@RequestBody Product product) {
	    try {
	        Product createdProduct = productService.createProduct(product);
	        LOGGER.info("Product created: {}", createdProduct);
	        return ResponseEntity.ok(new ProductResponse(true, ProductConstants.PRODUCT_CREATED_MESSAGE, createdProduct));
	    } catch (ProductException e) {
	        LOGGER.error("Error creating product: {}", e.getMessage());
	        return ProductUtils.handleProductException(e);
	    }
	}

	@Operation(summary = ProductConstants.ADD_MULTIPLE_PRODUCTS_OPERATION_SUMMARY, description = ProductConstants.ADD_MULTIPLE_PRODUCTS_OPERATION_DESCRIPTION)
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = ProductConstants.ADD_MULTIPLE_PRODUCTS_RESPONSE_CODE_200_DESCRIPTION, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponse.class, type = "array"))),
	    @ApiResponse(responseCode = "400", description = ProductConstants.ADD_MULTIPLE_PRODUCTS_RESPONSE_CODE_400_DESCRIPTION, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductErrorResponse.class)))
	})
	@PostMapping("/addProducts")
	@RateLimiter(name = ProductConstants.CREATE_PRODUCTS_LIMIT, fallbackMethod = ProductConstants.FALLBACK_INVENTORY_METHOD)
	public ResponseEntity<?> addProducts(@RequestBody List<Product> products) {
	    LOGGER.info("Received request to add products: {}", products);
	    try {
	        List<Product> createdProducts = productService.addProducts(products);
	        LOGGER.info("Products created: {}", createdProducts);
	        return ResponseEntity.ok(new ProductResponse(true, ProductConstants.PRODUCTS_CREATED_MESSAGE, createdProducts));
	    } catch (ProductException e) {
	        LOGGER.error("Error adding products: {}", e.getMessage());
	        return ProductUtils.handleProductException(e);
	    }
	}


	@Operation(summary = ProductConstants.GET_PRODUCT_BY_ID_OPERATION_SUMMARY, description = ProductConstants.GET_PRODUCT_BY_ID_OPERATION_DESCRIPTION)
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = ProductConstants.GET_PRODUCT_BY_ID_RESPONSE_CODE_200_DESCRIPTION, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponse.class))),
	    @ApiResponse(responseCode = "404", description = ProductConstants.GET_PRODUCT_BY_ID_RESPONSE_CODE_404_DESCRIPTION, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductErrorResponse.class)))
	})
	@GetMapping("/getProductById/{id}")
	@RateLimiter(name = ProductConstants.GET_PRODUCT_BY_ID_LIMIT, fallbackMethod = ProductConstants.FALLBACK_INVENTORY_METHOD)
	public ResponseEntity<?> getProductById(
	        @Parameter(description = ProductConstants.PRODUCT_ID_PARAMETER_DESCRIPTION) @PathVariable String id) {
	    LOGGER.info("Received request to retrieve product by ID: {}", id);
	    try {
	        Product product = productService.getProductById(id);
	        LOGGER.info("Product retrieved: {}", product);
	        return ResponseEntity.ok(new ProductResponse(true, ProductConstants.PRODUCT_FOUND_MESSAGE, product));
	    } catch (ProductException e) {
	        LOGGER.error("Error retrieving product: {}", e.getMessage());
	        return ProductUtils.handleProductException(e);
	    }
	}


	@Operation(summary = ProductConstants.UPDATE_PRODUCT_OPERATION_SUMMARY, description = ProductConstants.UPDATE_PRODUCT_OPERATION_DESCRIPTION)
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = ProductConstants.UPDATE_PRODUCT_RESPONSE_CODE_200_DESCRIPTION, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponse.class))),
	    @ApiResponse(responseCode = "400", description = ProductConstants.UPDATE_PRODUCT_RESPONSE_CODE_400_DESCRIPTION, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductErrorResponse.class))),
	    @ApiResponse(responseCode = "404", description = ProductConstants.UPDATE_PRODUCT_RESPONSE_CODE_404_DESCRIPTION, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductErrorResponse.class)))
	})
	@PutMapping("/updateProductByID/{id}")
	@RateLimiter(name = ProductConstants.UPDATE_PRODUCT_BY_ID_LIMIT, fallbackMethod = ProductConstants.FALLBACK_INVENTORY_METHOD)
	public ResponseEntity<?> updateProduct(
	        @Parameter(description = ProductConstants.UPDATE_PRODUCT_ID_PARAMETER_DESCRIPTION) @PathVariable String id,
	        @RequestBody Product product) {
	    LOGGER.info("Received request to update product with ID {}: {}", id, product);
	    try {
	        Product updatedProduct = productService.updateProduct(id, product);
	        LOGGER.info("Product updated: {}", updatedProduct);
	        return ResponseEntity.ok(new ProductResponse(true, ProductConstants.PRODUCT_UPDATED_MESSAGE, updatedProduct));
	    } catch (ProductException e) {
	        LOGGER.error("Error updating product: {}", e.getMessage());
	        return ProductUtils.handleProductException(e);
	    }
	}

	@Operation(summary = ProductConstants.DELETE_PRODUCT_OPERATION_SUMMARY, description = ProductConstants.DELETE_PRODUCT_OPERATION_DESCRIPTION)
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = ProductConstants.DELETE_PRODUCT_API_RESPONSE_200_DESCRIPTION, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductResponse.class))),
	    @ApiResponse(responseCode = "404", description = ProductConstants.DELETE_PRODUCT_API_RESPONSE_404_DESCRIPTION, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductErrorResponse.class)))
	})
	@DeleteMapping("/deleteProductByID/{id}")
	@RateLimiter(name = ProductConstants.DELETE_PRODUCT_BY_ID_LIMIT, fallbackMethod = ProductConstants.FALLBACK_INVENTORY_METHOD)
	public ResponseEntity<?> deleteProduct(
	        @Parameter(description = ProductConstants.DELETE_PRODUCT_API_PARAMETER_DESCRIPTION) @PathVariable String id) {
	    LOGGER.info("Received request to delete product with ID: {}", id);
	    try {
	        productService.deleteProduct(id);
	        LOGGER.info("Product deleted with ID: {}", id);
	        return ResponseEntity.ok(new ProductResponse(true, ProductConstants.PRODUCT_DELETED_MESSAGE, null));
	    } catch (ProductException e) {
	        LOGGER.error("Error deleting product: {}", e.getMessage());
	        return ProductUtils.handleProductException(e);
	    }
	}

	@Operation(summary = ProductConstants.GET_ALL_PRODUCTS_OPERATION_SUMMARY, description = ProductConstants.GET_ALL_PRODUCTS_OPERATION_DESCRIPTION)
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = ProductConstants.GET_ALL_PRODUCTS_RESPONSE_CODE_200_DESCRIPTION, content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class, type = "array"))),
	    @ApiResponse(responseCode = "400", description = ProductConstants.GET_ALL_PRODUCTS_RESPONSE_CODE_400_DESCRIPTION, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductErrorResponse.class)))
	})
	@GetMapping
	public ResponseEntity<?> getAllProducts(
	        @Parameter(description = ProductConstants.PAGE_NUMBER_PARAMETER_DESCRIPTION) @RequestParam(defaultValue = "1") int page,
	        @Parameter(description = ProductConstants.PAGE_SIZE_PARAMETER_DESCRIPTION) @RequestParam(defaultValue = "10") int pageSize) {
	    LOGGER.info("Received request to retrieve all products with page number {} and page size {}", page, pageSize);
	    try {
	        List<Product> products = productService.getAllProducts(page, pageSize);
	        LOGGER.info("Products retrieved: {}", products);
	        return ResponseEntity.ok(new ProductResponse(true, ProductConstants.PRODUCTS_RETRIEVED_MESSAGE, products));
	    } catch (ProductException e) {
	        LOGGER.error("Error retrieving products: {}", e.getMessage());
	        return ProductUtils.handleProductException(e);
	    }
	}


	@Operation(summary = ProductConstants.GET_PRODUCTS_BY_STATUS_OPERATION_SUMMARY, description = ProductConstants.GET_PRODUCTS_BY_STATUS_OPERATION_DESCRIPTION)
	@ApiResponses(value = {
	    @ApiResponse(responseCode = "200", description = ProductConstants.GET_PRODUCTS_BY_STATUS_RESPONSE_CODE_200_DESCRIPTION, content = @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class, type = "array"))),
	    @ApiResponse(responseCode = "400", description = ProductConstants.GET_PRODUCTS_BY_STATUS_RESPONSE_CODE_400_DESCRIPTION, content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductErrorResponse.class)))
	})
	@GetMapping("/status")
	public ResponseEntity<?> getProductsByStatus(
	        @Parameter(description = ProductConstants.STATUS_PARAMETER_DESCRIPTION) @RequestParam String status) {
	    LOGGER.info("Received request to retrieve products by status: {}", status);
	    try {
	        List<Product> products = productService.getProductsByStatus(status);
	        LOGGER.info("Products retrieved by status {}: {}", status, products);
	        return ResponseEntity.ok(new ProductResponse(true, ProductConstants.PRODUCTS_RETRIEVED_MESSAGE, products));
	    } catch (ProductException e) {
	        LOGGER.error("Error retrieving products by status {}: {}", status, e.getMessage());
	        return ProductUtils.handleProductException(e);
	    }
	}


	public ResponseEntity<ProductResponse> productInventoryFallbackMethod(Exception e) {
		LOGGER.error("An error occurred: {}", e.getMessage());
		ProductResponse errorResponse = new ProductResponse(false, ProductConstants.FALLBACK_ERROR_MESSAGE,
				HttpStatus.TOO_MANY_REQUESTS.toString());
		return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(errorResponse);

	}
	
}
