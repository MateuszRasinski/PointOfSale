package com.cybercom.rasinski.pointofsale.application.output;

import com.cybercom.rasinski.pointofsale.domain.Product;

public interface SingleProductOutput extends Output {
    void print(Product product);
    void printProductNotFound();
    void printInvalidBarcode();
}
