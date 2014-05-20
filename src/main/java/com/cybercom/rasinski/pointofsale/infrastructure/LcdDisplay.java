package com.cybercom.rasinski.pointofsale.infrastructure;

import com.cybercom.rasinski.pointofsale.application.Output;
import com.cybercom.rasinski.pointofsale.domain.Money;
import com.cybercom.rasinski.pointofsale.domain.Product;
import com.cybercom.rasinski.pointofsale.domain.ShoppingCart;

public class LcdDisplay implements Output {
    @Override
    public void print(ShoppingCart shoppingCart) {
        print(shoppingCart.getTotalSum());
    }

    public void print(Product product) {
        System.out.print(product.getName() + TABBED_SPACE + product.getPrice());
    }

    private void print(Money totalSum) {
        System.out.print("TOTAL:" + TABBED_SPACE + totalSum);
    }

    public void printProductNotFound() {
        System.out.print("Product not found");
    }

    public void printInvalidBarcode() {
        System.out.print("Invalid bar-code");
    }
}
