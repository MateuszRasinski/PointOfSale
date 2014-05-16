package com.cybercom.rasinski.pointofsale.infrastructure;

import com.cybercom.rasinski.pointofsale.application.OutputPOS;
import com.cybercom.rasinski.pointofsale.domain.Money;
import com.cybercom.rasinski.pointofsale.domain.Product;

public class LcdDisplay implements OutputPOS {
    public LcdDisplay() {
    }

    public void print(Product product) {
        StringBuilder sb = new StringBuilder();
        sb.append(product.getName())
                .append("\t\t")
                .append(product.getPrice());
        System.out.print(sb);
    }

    public void print(Money totalSum) {
        System.out.print("TOTAL:\t\t" + totalSum);
    }

    public void printProductNotFound() {
        System.out.print("Product not found");
    }

    public void printInvalidBarcode() {
        System.out.print("Invalid bar-code");
    }
}
