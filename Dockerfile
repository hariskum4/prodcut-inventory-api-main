FROM openjdk:17-slim

EXPOSE 8080

ADD target/product-inventory-api-0.1.jar product-inventory-api-0.1.jar

ENTRYPOINT [ "java","-jar","product-inventory-api-0.1.jar" ]