package com.cybercom.rasinski.pointofsale.domain;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShoppingCartTest {

    private Product productMock1;
    private Product productMock2;
    private Product productMock3;

    @BeforeClass
    public void setUp() {
        productMock1 = mock(Product.class);
        when(productMock1.getPrice()).thenReturn(new Money("3.465"));
        productMock2 = mock(Product.class);
        when(productMock2.getPrice()).thenReturn(new Money("1.46"));
        productMock3 = mock(Product.class);
        when(productMock3.getPrice()).thenReturn(new Money("1.65"));
    }

    @Test
    public void shouldAddOneProduct() {
        //given
        ShoppingCart cart = new ShoppingCart();
        //when
        cart.add(productMock1);
        //then
        assertThat(cart.getProducts().get(0)).isEqualTo(productMock1);
        assertThat(cart.getTotalSum()).isEqualTo(new Money("3.47"));
    }

    @Test
    public void shouldAddThreeProducts() {
        //given
        ShoppingCart cart = new ShoppingCart();
        //when
        cart.add(productMock1);
        cart.add(productMock2);
        cart.add(productMock3);
        //then
        assertThat(cart.getProducts().size()).isEqualTo(3);
        assertThat(cart.getTotalSum()).isEqualTo(new Money("6.58"));
    }
}