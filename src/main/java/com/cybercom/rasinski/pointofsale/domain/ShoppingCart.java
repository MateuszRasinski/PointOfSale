package com.cybercom.rasinski.pointofsale.domain;

import com.cybercom.rasinski.pointofsale.System.Settings;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.cybercom.rasinski.pointofsale.System.validation.Validator.checkNotNull;

public class ShoppingCart {
    private List<Product> products;
    private Money totalSum;

    public ShoppingCart() {
        this(Settings.DEFAULT_LOCALE);
    }

    public ShoppingCart(Locale currency) {
        this.products = new ArrayList<>();
        this.totalSum = new Money(BigDecimal.ZERO, currency);
    }

    public List<Product> getProducts() {
        return products;
    }

    public Money getTotalSum() {
        return totalSum;
    }

    public void add(Product product) {
        checkNotNull(product);

        products.add(product);
        totalSum = totalSum.add(product.getPrice());
    }
}
