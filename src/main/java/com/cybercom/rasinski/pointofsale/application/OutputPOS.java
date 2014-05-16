package com.cybercom.rasinski.pointofsale.application;

import com.cybercom.rasinski.pointofsale.domain.ShoppingCart;

public interface OutputPOS {
    void print(ShoppingCart shoppingCart);
}
