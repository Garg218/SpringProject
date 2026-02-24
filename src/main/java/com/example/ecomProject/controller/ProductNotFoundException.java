package com.example.ecomProject.controller;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message)
    {
        super(message);
    }
}
