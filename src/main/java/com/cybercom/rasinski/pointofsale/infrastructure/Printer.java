package com.cybercom.rasinski.pointofsale.infrastructure;

import com.cybercom.rasinski.pointofsale.application.OutputPOS;
import com.cybercom.rasinski.pointofsale.domain.Money;
import com.cybercom.rasinski.pointofsale.domain.Product;
import java.util.List;

public class Printer implements OutputPOS {
    private static String LINE_SEPARATOR = System.lineSeparator();

    public void print(List<Product> products, Money totalSum) {
        StringBuilder sb = new StringBuilder(100);

        buildContent(products, totalSum, sb);

        System.out.print(sb);
    }

    private void buildContent(List<Product> products, Money totalSum, StringBuilder sb) {
        buildHeader(sb);
        buildBody(sb, products);
        buildFooter(sb, totalSum);
    }

    private void buildHeader(StringBuilder sb) {
        sb.append("Name\t\tPrice")
                .append(LINE_SEPARATOR);
    }

    private void buildBody(StringBuilder sb, List<Product> products) {
        for(Product product : products) {
            sb.append(product.getName())
                    .append("\t\t")
                    .append(product.getPrice())
                    .append(LINE_SEPARATOR);
        }
    }

    private void buildFooter(StringBuilder sb, Money totalSum) {
        sb.append("TOTAL:\t\t")
                .append(totalSum)
                .append(LINE_SEPARATOR);
    }
}
