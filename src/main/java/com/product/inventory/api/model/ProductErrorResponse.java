package com.product.inventory.api.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonFormat(with = JsonFormat.Feature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
public class ProductErrorResponse {
	 private boolean success;
	  private String message;
	  private Object products; 
	  private String error; 
	  // private Product product; 

		public ProductErrorResponse(boolean success, String message, String error) {
			super();
			this.success = success;
			this.message = message;
			this.error = error;
 
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

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}

		@Override
		public String toString() {
			return "ProductErrorResponse [success=" + success + ", message=" + message + ", products=" + products
					+ ", error=" + error + "]";
		}
 
 

}
