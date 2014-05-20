package com.cybercom.rasinski.pointofsale.output;

import com.cybercom.rasinski.pointofsale.domain.Barcode;
import com.cybercom.rasinski.pointofsale.domain.Money;
import com.cybercom.rasinski.pointofsale.domain.Product;
import com.cybercom.rasinski.pointofsale.domain.ShoppingCart;
import com.cybercom.rasinski.pointofsale.infrastructure.Printer;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static com.cybercom.rasinski.pointofsale.application.output.Output.TABBED_SPACE;
import static com.cybercom.rasinski.pointofsale.application.output.SummaryOutput.LINE_SEPARATOR;
import static com.cybercom.rasinski.pointofsale.application.output.SummaryOutput.TOTAL;
import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PrinterTest {
    private List<Product> products;
    private ByteArrayOutputStream systemOutStream;
    private PrintStream originalSystemOut;

    @BeforeMethod
    public void setUp() {
        Product[] productsArray = {
                new Product(1L, "Sample product", new Barcode("123"), new Money("3.5")),
                new Product(2L, "Soap", new Barcode("123"), new Money("1.5"))
        };
        products = Arrays.asList(productsArray);

        originalSystemOut = System.out;
        systemOutStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOutStream));
    }

    @AfterMethod
    public void tearDown() {
        System.setOut(originalSystemOut);
    }

    @Test
    public void shouldPrintSummary() {
        //given
        Printer printer = new Printer();
        ShoppingCart shoppingCartMock = mock(ShoppingCart.class);
        when(shoppingCartMock.getProducts()).thenReturn(products);
        when(shoppingCartMock.getTotalSum()).thenReturn(new Money("5"));
        //when
        printer.printSummary(shoppingCartMock);
        //then
        String expectedString = getExpectedString();
        assertThat(systemOutStream.toString()).isEqualTo(expectedString);
    }

    private String getExpectedString() {
        return Printer.HEADER
                + "Sample product" + TABBED_SPACE + "3,50 zł" + LINE_SEPARATOR
                + "Soap" + TABBED_SPACE + "1,50 zł" + LINE_SEPARATOR
                + TOTAL + TABBED_SPACE + "5,00 zł" + LINE_SEPARATOR;
    }
}