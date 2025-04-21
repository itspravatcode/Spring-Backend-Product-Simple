# E-commerce API Documentation

## Overview

This is a RESTful API for an e-commerce platform built with Spring Boot. The API allows for managing products including creating, reading, updating, and deleting products (CRUD operations), as well as searching for products based on keywords. Each product can have an associated image which is also handled by the API.

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- RESTful architecture

## Base URL

All API endpoints are prefixed with `/api`

## API Endpoints

### 1. Get All Products

Retrieves a list of all products available in the system.

- **URL**: `/api/products`
- **Method**: `GET`
- **Authentication**: None
- **URL Parameters**: None
- **Success Response**:
  - **Code**: 200 OK
  - **Content**: Array of product objects
  ```json
  [
    {
      "id": 1,
      "name": "Product Name",
      "description": "Product Description",
      "price": 29.99,
      "category": "Category",
      "brand": "Brand",
      // Other product properties
      // Note: imageData is not included in the response
    },
    // More products...
  ]
  ```

### 2. Get Product by ID

Retrieves a specific product by its ID.

- **URL**: `/api/product/{id}`
- **Method**: `GET`
- **Authentication**: None
- **URL Parameters**: 
  - `id` (required): The ID of the product
- **Success Response**:
  - **Code**: 200 OK
  - **Content**: Product object
  ```json
  {
    "id": 1,
    "name": "Product Name",
    "description": "Product Description",
    "price": 29.99,
    "category": "Category",
    "brand": "Brand",
    // Other product properties
    // Note: imageData is not included in the response
  }
  ```
- **Error Response**:
  - **Code**: 404 Not Found
  - **Content**: None

### 3. Add New Product

Creates a new product with an associated image.

- **URL**: `/api/product`
- **Method**: `POST`
- **Authentication**: None
- **Content-Type**: `multipart/form-data`
- **Request Parts**: 
  - `product` (required): A JSON object containing product details
  - `imageFile` (required): Image file to be associated with the product
- **Example Request**:
  ```
  // product part
  {
    "name": "New Product",
    "description": "Product Description",
    "price": 49.99,
    "category": "Electronics",
    "brand": "MyBrand"
    // Other product properties
  }
  
  // imageFile part: binary image data
  ```
- **Success Response**:
  - **Code**: 201 Created
  - **Content**: Created product object
- **Error Response**:
  - **Code**: 500 Internal Server Error
  - **Content**: None

### 4. Get Product Image

Retrieves the image associated with a specific product.

- **URL**: `/api/product/{productId}/image`
- **Method**: `GET`
- **Authentication**: None
- **URL Parameters**: 
  - `productId` (required): The ID of the product
- **Success Response**:
  - **Code**: 200 OK
  - **Content-Type**: The original image MIME type (e.g., `image/jpeg`, `image/png`)
  - **Content**: Binary image data
- **Error Response**: 
  - If the product or image does not exist, an appropriate error status will be returned

### 5. Update Product

Updates an existing product and its associated image.

- **URL**: `/api/product/{id}`
- **Method**: `PUT`
- **Authentication**: None
- **URL Parameters**: 
  - `id` (required): The ID of the product to update
- **Content-Type**: `multipart/form-data`
- **Request Parts**: 
  - `product` (required): A JSON object containing updated product details
  - `imageFile` (required): Updated image file
- **Success Response**:
  - **Code**: 200 OK
  - **Content**: "Updated"
- **Error Response**:
  - **Code**: 400 Bad Request
  - **Content**: "Failed"

### 6. Delete Product

Deletes a product by its ID.

- **URL**: `/api/product/{id}`
- **Method**: `DELETE`
- **Authentication**: None
- **URL Parameters**: 
  - `id` (required): The ID of the product to delete
- **Success Response**:
  - **Code**: 200 OK
  - **Content**: "deleted successfully"
- **Error Response**:
  - **Code**: 400 Bad Request
  - **Content**: "Product not Found"

### 7. Search Products

Searches for products based on a keyword. The search is performed across product name, description, brand, and category fields.

- **URL**: `/api/product/search`
- **Method**: `GET`
- **Authentication**: None
- **URL Parameters**: 
  - `keyword` (required): The search term
- **Success Response**:
  - **Code**: 200 OK
  - **Content**: Array of matching product objects
  ```json
  [
    {
      "id": 1,
      "name": "Matching Product",
      "description": "This matches your search",
      "price": 29.99,
      "category": "Category",
      "brand": "Brand",
      // Other product properties
    },
    // More matching products...
  ]
  ```

## Important Notes for Front-End Developers

1. **Image Handling**:
   - When displaying product images, use the dedicated endpoint `/api/product/{productId}/image`
   - When creating or updating products, ensure to send both the product JSON and the image file as separate parts in a multipart/form-data request

2. **Error Handling**:
   - Be prepared to handle the various HTTP status codes returned by the API
   - Implement appropriate error messaging for users based on response codes

3. **Product Object Structure**:
   - The product objects returned by the API contain various fields such as id, name, description, price, category, and brand
   - The binary image data is not included in the main product object to reduce payload size - use the dedicated image endpoint instead

4. **Search Functionality**:
   - The search functionality performs a case-insensitive search across multiple fields
   - It uses partial matching, so incomplete words will still return results


