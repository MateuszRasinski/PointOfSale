package com.cybercom.rasinski.pointofsale.application;

import com.cybercom.rasinski.pointofsale.domain.ShoppingCart;

public interface Output {
    String LINE_SEPARATOR = System.lineSeparator();
    String TABBED_SPACE = "\t\t";

    void print(ShoppingCart shoppingCart);
}
