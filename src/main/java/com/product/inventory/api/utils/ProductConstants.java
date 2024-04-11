package com.product.inventory.api.utils;

public class ProductConstants {

	    public static final String PRODUCTS_CREATED_MESSAGE = "Products created";
	    public static final String PRODUCT_CREATED_MESSAGE = "Product created";
	    
	    public static final String PRODUCT_FOUND_MESSAGE = "Product found";
	    public static final String PRODUCT_UPDATED_MESSAGE = "Product updated";
	    public static final String PRODUCT_DELETED_MESSAGE = "Product deleted";
	    public static final String INVALID_PAGE_OR_PAGE_SIZE_MESSAGE = "Invalid page or pageSize provided";
	    public static final String PRODUCTS_RETRIEVED_MESSAGE = "Products retrieved";
	    public static final String CREATE_PRODUCT_LIMIT = "createProductLimit";
	    public static final String CREATE_PRODUCTS_LIMIT = "createProductsLimit";
	    public static final String GET_PRODUCT_BY_ID_LIMIT = "getProductByIdLimit";
	    public static final String UPDATE_PRODUCT_BY_ID_LIMIT = "updateProductByIdLimit";
	    public static final String DELETE_PRODUCT_BY_ID_LIMIT = "deleteProductByIdLimit";
	    public static final String FALLBACK_INVENTORY_METHOD= "productInventoryFallbackMethod";
	    public static final String FALLBACK_ERROR_MESSAGE= "Inventory service does not permit furthur request, please try after some time";
	    public static final String PRODUCT_CONTROLLER= "Product Controller";
	    public static final String PRODUCT_CONTROLLER_DESCRIPTION= "Operations for managing products in the inventory";
	    public static final String CREATE_PRODUCT_OPERATION_SUMMARY = "Add a new product";
	    public static final String CREATE_PRODUCT_OPERATION_DESCRIPTION = "Creates a new product in the inventory";
	    public static final String CREATE_PRODUCT_RESPONSE_CODE_200_DESCRIPTION = "Successfully created a new product";
	    public static final String CREATE_PRODUCT_RESPONSE_CODE_400_DESCRIPTION = "Invalid create product data";
	    public static final String ADD_MULTIPLE_PRODUCTS_OPERATION_SUMMARY = "Add multiple products";
	    public static final String ADD_MULTIPLE_PRODUCTS_OPERATION_DESCRIPTION = "Creates multiple new products in the inventory";
	    public static final String ADD_MULTIPLE_PRODUCTS_RESPONSE_CODE_200_DESCRIPTION = "Successfully created multiple products";
	    public static final String ADD_MULTIPLE_PRODUCTS_RESPONSE_CODE_400_DESCRIPTION = "Invalid multiple products data";
	    public static final String GET_PRODUCT_BY_ID_OPERATION_SUMMARY = "Get a product by ID";
	    public static final String GET_PRODUCT_BY_ID_OPERATION_DESCRIPTION = "Retrieves a product by its unique identifier";
	    public static final String PRODUCT_ID_PARAMETER_DESCRIPTION = "The unique identifier of the product";
	    public static final String GET_PRODUCT_BY_ID_RESPONSE_CODE_200_DESCRIPTION = "Successfully retrieved the product";
	    public static final String GET_PRODUCT_BY_ID_RESPONSE_CODE_404_DESCRIPTION = "Product with Id not found";
	    public static final String UPDATE_PRODUCT_OPERATION_SUMMARY = "Update a product";
	    public static final String UPDATE_PRODUCT_OPERATION_DESCRIPTION = "Updates an existing product in the inventory";
	    public static final String UPDATE_PRODUCT_ID_PARAMETER_DESCRIPTION = "The unique identifier of the update product";
	    public static final String UPDATE_PRODUCT_RESPONSE_CODE_200_DESCRIPTION = "Successfully updated the product";
	    public static final String UPDATE_PRODUCT_RESPONSE_CODE_400_DESCRIPTION = "Invalid Update product data";
	    public static final String UPDATE_PRODUCT_RESPONSE_CODE_404_DESCRIPTION = "Update Product not found";
	    public static final String DELETE_PRODUCT_OPERATION_SUMMARY = "Delete a product";
	    public static final String DELETE_PRODUCT_OPERATION_DESCRIPTION = "Deletes a product from the inventory";
	    public static final String DELETE_PRODUCT_API_RESPONSE_200_DESCRIPTION = "Successfully deleted the product";
	    public static final String DELETE_PRODUCT_API_RESPONSE_404_DESCRIPTION = "Product for deletion not found";
	    public static final String DELETE_PRODUCT_API_PARAMETER_DESCRIPTION = "The unique identifier of the delete product";
	    public static final String GET_ALL_PRODUCTS_OPERATION_SUMMARY = "Get all products";
	    public static final String GET_ALL_PRODUCTS_OPERATION_DESCRIPTION = "Retrieves a paginated list of all products in the inventory";
	    public static final String GET_ALL_PRODUCTS_RESPONSE_CODE_200_DESCRIPTION = "Successfully retrieved the list of products";
	    public static final String PAGE_NUMBER_PARAMETER_DESCRIPTION = "Page number";
	    public static final String PAGE_SIZE_PARAMETER_DESCRIPTION = "Page size";
	    public static final String GET_PRODUCTS_BY_STATUS_OPERATION_SUMMARY = "Get products by status";
	    public static final String GET_PRODUCTS_BY_STATUS_OPERATION_DESCRIPTION = "Retrieves a list of products with a specific status";
	    public static final String GET_PRODUCTS_BY_STATUS_RESPONSE_CODE_200_DESCRIPTION = "Successfully retrieved the list of products";
	    public static final String STATUS_PARAMETER_DESCRIPTION = "The status of the products";
}
