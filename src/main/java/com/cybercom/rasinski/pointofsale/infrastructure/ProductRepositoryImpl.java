package com.cybercom.rasinski.pointofsale.infrastructure;

import com.cybercom.rasinski.pointofsale.domain.Product;
import com.cybercom.rasinski.pointofsale.domain.ProductRepository;
import java.util.HashMap;
import java.util.Map;

public class ProductRepositoryImpl implements ProductRepository {
    private Map<Long, Product> productTable = new HashMap<>();

    public ProductRepositoryImpl(Map<Long, Product> productTable) {
        this.productTable = productTable;
    }

    @Override
    public Product find(Long id) {
        Product product = productTable.get(id);

        if (product == null) {
            throw new ProductNotFoundException("Cannot find product with id: " + id);
        }

        return product;
    }
}
