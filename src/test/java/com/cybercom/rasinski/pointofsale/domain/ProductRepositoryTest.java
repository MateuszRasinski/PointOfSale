package com.cybercom.rasinski.pointofsale.domain;

import com.cybercom.rasinski.pointofsale.infrastructure.ProductNotFoundException;
import com.cybercom.rasinski.pointofsale.infrastructure.ProductRepositoryImpl;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class ProductRepositoryTest {
    private ProductRepository productRepository;
    private Product sampleProduct;


    @BeforeClass
    public void setUp() {
        Map<Long, Product> productTable = new HashMap<>();
        sampleProduct = new Product(1L, "Sample name", new BigDecimal("3.5"));
        productTable.put(1L, sampleProduct);
        productRepository = new ProductRepositoryImpl(productTable);
    }

    @Test
    public void shouldFind() {
        //when
        Product product = productRepository.find(1L);
        //then
        assertThat(product).isEqualTo(sampleProduct);
    }


    @Test(expectedExceptions = ProductNotFoundException.class)
    public void shouldThrowProductNotFoundException() {
        //when
        productRepository.find(2L);
    }
}