package com.cybercom.rasinski.pointofsale.infrastructure;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
