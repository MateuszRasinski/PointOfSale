package com.cybercom.rasinski.pointofsale.domain;

import com.cybercom.rasinski.pointofsale.system.Settings;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.cybercom.rasinski.pointofsale.system.validation.Validator.checkNotNull;

public class ShoppingCart {
    private List<Product> products;
    private Locale currency;

    public ShoppingCart() {
        this(Settings.DEFAULT_LOCALE);
    }

    public ShoppingCart(Locale currency) {
        this.products = new ArrayList<>();
        this.currency = currency;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Money getTotalSum() {
        Money totalSum = new Money(BigDecimal.ZERO, currency);
        for (Product product : products) {
            totalSum = totalSum.add(product.getPrice());
        }
        return totalSum;
    }

    public void add(Product product) {
        checkNotNull(product);

        products.add(product);
    }
}
