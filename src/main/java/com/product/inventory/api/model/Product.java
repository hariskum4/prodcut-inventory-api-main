package com.product.inventory.api.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "ProductInventory")
public class Product {

	@Id
	@Indexed(unique = true)
	private String id;

	@NotBlank(message = "Name cannot be blank")
	private String name;

	private String description;

	@PositiveOrZero(message = "Price must be a positive value or zero")
	private double price;

	@PositiveOrZero(message = "Quantity must be a positive value or zero")
	private int quantity;

	@NotBlank(message = "Status cannot be blank")
	private String status; // e.g., 'available,' 'out of stock'
	// Getters and setters

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	// toString method

	@Override
	public String toString() {
		return "Product{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", description='" + description + '\''
				+ ", price=" + price + ", quantity=" + quantity + ", status='" + status + '\'' + '}';
	}
}