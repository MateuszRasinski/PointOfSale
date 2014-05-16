package com.cybercom.rasinski.pointofsale.output;

import com.cybercom.rasinski.pointofsale.domain.Product;
import java.math.BigDecimal;
import java.util.List;

public class Printer implements OutputPOS {
    private static String LINE_SEPARATOR = System.lineSeparator();

    @Override
    public void print(List<Product> products, BigDecimal totalSum) {
        StringBuilder sb = new StringBuilder(100);
        sb.append("Name\t\tPrice")
                .append(LINE_SEPARATOR);

        for(Product product : products) {
            sb.append(product.getName())
                    .append("\t\t")
                    .append(product.getPrice())
                    .append(LINE_SEPARATOR);
        }

        sb.append("TOTAL:\t\t")
                .append(totalSum)
                .append(LINE_SEPARATOR);

        System.out.print(sb.toString());
    }
}
