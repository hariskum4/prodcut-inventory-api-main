# product-inventory-api

Product Inventory API
This repository contains the source code for a Product Inventory API. The API is designed to manage products, including creating, updating, deleting, and retrieving products from an inventory.

Features
Create Product: Allows users to add a new product to the inventory by providing details such as name, description, price, quantity, and status.
Update Product: Enables users to update the details of an existing product, including modifying its price, quantity, and status.
Delete Product: Allows users to delete a product from the inventory based on its unique identifier.
Retrieve Product: Provides endpoints to retrieve product information based on various criteria, such as product ID, name, status, or category.
Exception Handling: Includes robust exception handling to provide meaningful error messages to clients in case of validation errors, data conflicts, or unexpected issues.
Technologies Used
Spring Boot: Framework for building Java-based applications.
Spring Data MongoDB: Provides support for MongoDB database integration.
Swagger: Used for API documentation and testing.
Resilience4j: Library for implementing resilience patterns in Java applications.
Lombok: Library to reduce boilerplate code in Java.
Docker: Containerization platform used for packaging the application and its dependencies into containers.
Kubernetes (K8s): Container orchestration platform used for deploying, scaling, and managing containerized applications.
Installation
To run the Product Inventory API locally, follow these steps:

Clone this repository to your local machine.
Ensure that you have Java Development Kit (JDK) installed on your system.
Install MongoDB and configure the connection details in the application properties.
Build the project using Maven: mvn clean install.
Run the application: mvn spring-boot:run.
Docker and Kubernetes
The Product Inventory API can also be deployed using Docker and Kubernetes:

Docker
Build the Docker image:
Copy code
docker build -t product-inventory-api .
Run the Docker container:
arduino
Copy code
docker run -p 8080:8080 product-inventory-api
Kubernetes
Apply the Kubernetes manifests:
Copy code
kubectl apply -f kubernetes/
Monitor the deployment and services:
arduino
Copy code
kubectl get pods
kubectl get services
API Documentation
Once the application is running, you can access the Swagger UI for API documentation and testing by navigating to http://localhost:8080/swagger-ui.html in your web browser.

Endpoints
Create Product: POST /products/addProduct
Update Product: PUT /products/updateProductByID/{id}
Delete Product: DELETE /products/deleteProductByID/{id}
Retrieve Product by ID: GET /products/getProductById/{id}
Retrieve Products by Status: GET /products/status?status={status}
Retrieve All Products: GET /products?page={page}&pageSize={pageSize}
How to Execute
Use a tool like Postman or Swagger UI to send HTTP requests to the respective endpoints.
Provide the required parameters in the request body or query parameters as per the API documentation.
Review the response to ensure that the operation was successful or to identify any errors.
Contributing
Contributions to this project are welcome. You can contribute by submitting bug reports, feature requests, or pull requests.
