package com.cybercom.rasinski.pointofsale.application;

import com.cybercom.rasinski.pointofsale.domain.Barcode;
import com.cybercom.rasinski.pointofsale.domain.Product;
import com.cybercom.rasinski.pointofsale.domain.ProductRepository;

public class BarcodeScanner {
    private ProductRepository productRepository;

    public BarcodeScanner(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product scan(Barcode barcode) {
        String code = barcode.getCode();

        if (code.isEmpty()) {
            throw new InvalidBarcodeException("Invalid barcode: " + code);
        }

        return productRepository.findByBarcode(barcode);
    }
}
