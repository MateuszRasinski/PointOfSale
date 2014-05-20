package com.cybercom.rasinski.pointofsale.application.output;

import com.cybercom.rasinski.pointofsale.domain.ShoppingCart;

public interface SummaryOutput extends Output {
    void printSummary(ShoppingCart shoppingCart);
}
