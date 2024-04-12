package com.product.inventory.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class ProductResponse {
	private boolean success;
	private String message;
	private Object products;

	 public ProductResponse(boolean success, String message, Object products) {
	        this.success = success;
	        this.message = message;
	        this.products = products;
	       
	    }
	 


	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getProducts() {
		return products;
	}

	public void setProducts(Object products) {
		this.products = products;
	}

	
	 
}