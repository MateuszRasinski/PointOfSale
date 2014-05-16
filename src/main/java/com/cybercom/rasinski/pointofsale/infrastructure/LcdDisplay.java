package com.cybercom.rasinski.pointofsale.infrastructure;

import com.cybercom.rasinski.pointofsale.application.OutputPOS;
import com.cybercom.rasinski.pointofsale.domain.Money;
import com.cybercom.rasinski.pointofsale.domain.Product;
import com.cybercom.rasinski.pointofsale.domain.ShoppingCart;

public class LcdDisplay implements OutputPOS {
    public LcdDisplay() {
    }

    @Override
    public void print(ShoppingCart shoppingCart) {
        print(shoppingCart.getTotalSum());
    }

    public void print(Product product) {
        StringBuilder sb = new StringBuilder();
        sb.append(product.getName())
                .append("\t\t")
                .append(product.getPrice());
        System.out.print(sb);
    }

    private void print(Money totalSum) {
        System.out.print("TOTAL:\t\t" + totalSum);
    }

    public void printProductNotFound() {
        System.out.print("Product not found");
    }

    public void printInvalidBarcode() {
        System.out.print("Invalid bar-code");
    }
}
