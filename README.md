# Product Inventory API

This repository contains the source code for a Product Inventory API. The API is designed to manage products, including creating, updating, deleting, and retrieving products from an inventory.

## Features

- **Create Product:** Allows users to add a new product to the inventory by providing details such as name, description, price, quantity, and status.
- **Update Product:** Enables users to update the details of an existing product, including modifying its price, quantity, and status.
- **Delete Product:** Allows users to delete a product from the inventory based on its unique identifier.
- **Retrieve Product:** Provides endpoints to retrieve product information based on various criteria, such as product ID, name, status, or category.
- **Exception Handling:** Includes robust exception handling to provide meaningful error messages to clients in case of validation errors, data conflicts, or unexpected issues.

## Technologies Used

- **Spring Boot:** Framework for building Java-based applications.
- **Spring Data MongoDB:** Provides support for MongoDB database integration.
- **Swagger:** Used for API documentation and testing.
- **Resilience4j:** Library for implementing resilience patterns in Java applications.
- **Lombok:** Library to reduce boilerplate code in Java.
- **Docker:** Containerization platform used for packaging the application and its dependencies into containers.
- **Kubernetes (K8s):** Container orchestration platform used for deploying, scaling, and managing containerized applications.


## Docker Deployment
To run the Product Inventory API locally with Docker , follow these steps:

1. Clone this repository to your local machine.
2. Ensure that you have Java Development Kit (JDK) installed on your system.
3. Install Docker and Docker Compose.
4. Navigate to the root directory of the cloned repository.
5. Execute the Docker Compose file to start both MongoDB and the project:

    ```bash
    docker-compose up
    ```

## Kubernetes Deployment

The Product Inventory API can also be deployed using Kubernetes:

1. Ensure you have a Kubernetes cluster set up and configured.
2. Navigate to the root directory of the cloned repository.
3. Apply the Kubernetes manifests to deploy the application and MongoDB:

    ```bash
    kubectl apply -f mongo.yml
    kubectl apply -f k8-deployment.yml
    ```

4. Monitor the deployment and services:

    ```bash
    kubectl get pods
    kubectl get services
    ```
## Running Locally without Docker or Kubernetes:
   - Ensure that you have Java Development Kit (JDK) installed on your system.
   - Clone this repository to your local machine.
   - Navigate to the root directory of the cloned repository.
   - Configure MongoDB connection details in the application properties.
   - Build the project using Maven:
     ```bash
     mvn clean install
     ```
   - Run the application:
     ```bash
     mvn spring-boot:run
     ```
## API Documentation

Once the application is running, you can access the Swagger UI for API documentation and testing by navigating to [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) in your web browser.

## Endpoints

- **Create Product:** `POST /products/addProduct`
- **Update Product:** `PUT /products/updateProductByID/{id}`
- **Delete Product:** `DELETE /products/deleteProductByID/{id}`
- **Retrieve Product by ID:** `GET /products/getProductById/{id}`
- **Retrieve Products by Status:** `GET /products/status?status={status}`
- **Retrieve All Products:** `GET /products?page={page}&pageSize={pageSize}`

## How to Execute

1. Use a tool like Postman or Swagger UI to send HTTP requests to the respective endpoints.
2. Provide the required parameters in the request body or query parameters as per the API documentation.
3. Review the response to ensure that the operation was successful or to identify any errors.

## Contributing

Contributions to this project are welcome. You can contribute by submitting bug reports, feature requests, or pull requests.

**Please write in English language.**
