package com.cybercom.rasinski.pointofsale.output;

import com.cybercom.rasinski.pointofsale.domain.Product;
import java.math.BigDecimal;
import java.util.List;

public class LcdDisplay implements OutputPOS {
    @Override
    public void print(List<Product> products, BigDecimal totalSum) {
        //TODO: formatting
    }
}
