package com.cybercom.rasinski.pointofsale.infrastructure;

import com.cybercom.rasinski.pointofsale.domain.Product;
import com.cybercom.rasinski.pointofsale.application.OutputPOS;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class LcdDisplay implements OutputPOS {
    DecimalFormat decimalFormat;

    public LcdDisplay(Locale locale) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        decimalFormat = (DecimalFormat) numberFormat;
        decimalFormat.setMinimumFractionDigits(2);
        decimalFormat.setMaximumFractionDigits(2);
    }

    @Override
    public void print(List<Product> products, BigDecimal totalSum) {
        //TODO: formatting
    }

    public void print(Product product) {
        StringBuilder sb = new StringBuilder();
        sb.append(product.getName())
                .append("\t\t")
                .append(decimalFormat.format(product.getPrice()));
        System.out.print(sb);
    }

    public void print(BigDecimal totalSum) {
        System.out.print("TOTAL:\t\t" + decimalFormat.format(totalSum));
    }

    public void printProductNotFound() {
        System.out.print("Product not found");
    }

    public void printInvalidBarcode() {
        System.out.print("Invalid bar-code");
    }
}
