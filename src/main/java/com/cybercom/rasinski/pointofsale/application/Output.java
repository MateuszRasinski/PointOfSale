package com.cybercom.rasinski.pointofsale.application;

import com.cybercom.rasinski.pointofsale.domain.Product;
import com.cybercom.rasinski.pointofsale.domain.ShoppingCart;

public interface Output {
    String LINE_SEPARATOR = System.lineSeparator();
    String TABBED_SPACE = "\t\t";
    String TOTAL = "TOTAL:";

    void printSummary(ShoppingCart shoppingCart);
    void print(Product product);
    void printProductNotFound();
    void printInvalidBarcode();
}
