package com.cybercom.rasinski.pointofsale.input;

import com.cybercom.rasinski.pointofsale.domain.Product;
import com.cybercom.rasinski.pointofsale.domain.ProductRepository;

public class BarcodeScanner {
    private ProductRepository productRepository;

    public BarcodeScanner(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product scan(Barcode barcode) {
        String barcodeValue = barcode.getValue();

        if (barcodeValue.isEmpty()) {
            throw new InvalidBarcodeException("Invalid barcode: " + barcodeValue);
        }

        Long productId = new Long(barcodeValue);

        return productRepository.find(productId);
    }
}
