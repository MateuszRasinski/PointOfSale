package com.cybercom.rasinski.pointofsale.infrastructure;

import com.cybercom.rasinski.pointofsale.application.Output;
import com.cybercom.rasinski.pointofsale.domain.Money;
import com.cybercom.rasinski.pointofsale.domain.Product;
import com.cybercom.rasinski.pointofsale.domain.ShoppingCart;
import java.util.List;

public class Printer implements Output {
    @Override
    public void print(ShoppingCart shoppingCart) {
        StringBuilder sb = new StringBuilder(100);

        buildContent(shoppingCart.getProducts(), shoppingCart.getTotalSum(), sb);

        System.out.print(sb);
    }

    private void buildContent(List<Product> products, Money totalSum, StringBuilder sb) {
        buildHeader(sb);
        buildBody(sb, products);
        buildFooter(sb, totalSum);
    }

    private void buildHeader(StringBuilder sb) {
        sb.append("Name")
                .append(TABBED_SPACE)
                .append("Price")
                .append(LINE_SEPARATOR);
    }

    private void buildBody(StringBuilder sb, List<Product> products) {
        for(Product product : products) {
            sb.append(product.getName())
                    .append(TABBED_SPACE)
                    .append(product.getPrice())
                    .append(LINE_SEPARATOR);
        }
    }

    private void buildFooter(StringBuilder sb, Money totalSum) {
        sb.append("TOTAL:")
                .append(TABBED_SPACE)
                .append(totalSum)
                .append(LINE_SEPARATOR);
    }
}
