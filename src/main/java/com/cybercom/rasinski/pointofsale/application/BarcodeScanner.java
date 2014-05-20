package com.cybercom.rasinski.pointofsale.application;

import com.cybercom.rasinski.pointofsale.domain.Barcode;
import com.cybercom.rasinski.pointofsale.domain.Product;
import com.cybercom.rasinski.pointofsale.domain.ProductRepository;

import static com.cybercom.rasinski.pointofsale.System.validation.Validator.checkNotEmpty;
import static com.cybercom.rasinski.pointofsale.System.validation.Validator.checkNotNull;

public class BarcodeScanner {
    private ProductRepository productRepository;

    public BarcodeScanner(ProductRepository productRepository) {
        checkNotNull(productRepository);
        this.productRepository = productRepository;
    }

    public Product scan(Barcode barcode) {
        String code = barcode.getCode();

        if (!checkNotEmpty(code)) {
            throw new InvalidBarcodeException("Invalid barcode: " + code);
        }

        return productRepository.findByBarcode(barcode);
    }
}
