package com.cybercom.rasinski.pointofsale.infrastructure;

import com.cybercom.rasinski.pointofsale.application.output.SingleProductOutput;
import com.cybercom.rasinski.pointofsale.application.output.SummaryOutput;
import com.cybercom.rasinski.pointofsale.domain.Money;
import com.cybercom.rasinski.pointofsale.domain.Product;
import com.cybercom.rasinski.pointofsale.domain.ShoppingCart;

public class LcdDisplay implements SummaryOutput, SingleProductOutput {
    @Override
    public void printSummary(ShoppingCart shoppingCart) {
        print(shoppingCart.getTotalSum());
    }

    @Override
    public void print(Product product) {
        System.out.print(product.getName() + TABBED_SPACE + product.getPrice());
    }

    private void print(Money totalSum) {
        System.out.print(TOTAL + TABBED_SPACE + totalSum);
    }

    @Override
    public void printProductNotFound() {
        System.out.print("Product not found");
    }

    @Override
    public void printInvalidBarcode() {
        System.out.print("Invalid bar-code");
    }
}
