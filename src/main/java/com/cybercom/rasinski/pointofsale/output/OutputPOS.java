package com.cybercom.rasinski.pointofsale.output;

import com.cybercom.rasinski.pointofsale.domain.Product;
import java.math.BigDecimal;
import java.util.List;

public interface OutputPOS {
    void print(List<Product> products, BigDecimal totalSum);
}
