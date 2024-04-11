package com.product.inventory.api.exception;

import com.product.inventory.api.model.ProductErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ProductErrorResponse> handleProductException(ProductException ex) {
        ProductErrorResponse errorResponse = new ProductErrorResponse(
                false,
                ex.getMessage(),
                ex.getHttpStatus().toString()
        );
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(errorResponse);
    }

}
