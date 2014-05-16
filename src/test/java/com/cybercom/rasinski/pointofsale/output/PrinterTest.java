package com.cybercom.rasinski.pointofsale.output;

import com.cybercom.rasinski.pointofsale.domain.Money;
import com.cybercom.rasinski.pointofsale.domain.Product;
import com.cybercom.rasinski.pointofsale.domain.Barcode;
import com.cybercom.rasinski.pointofsale.infrastructure.Printer;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class PrinterTest {
    private static final Locale LOCALE_PL = new Locale("pl", "PL");
    private List<Product> products;
    private ByteArrayOutputStream systemOutStream;
    private PrintStream originalSystemOut;


    @BeforeMethod
    public void setUp() {
        Product[] productsArray = {
                new Product(1L, "Sample product", new Barcode("123"),
                        new Money(new BigDecimal(3.5))),
                new Product(2L, "Soap", new Barcode("123"), new Money(new BigDecimal(1.5)))
        };
        products = Arrays.asList(productsArray);

        originalSystemOut = System.out;
        systemOutStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOutStream));
    }

    @Test
    public void shouldPrint() {
        //given
        Printer printer = new Printer();
        Money totalSum = new Money(new BigDecimal(5));
        //when
        printer.print(products, totalSum);
        //then
        String expectedString = getExpectedString();
        assertThat(systemOutStream.toString()).isEqualTo(expectedString);
    }


    private String getExpectedString() {
        String lineSeparator = System.lineSeparator();
        return "Name\t\tPrice" + lineSeparator
                + "Sample product\t\t3,50 zł" + lineSeparator
                + "Soap\t\t1,50 zł" + lineSeparator
                + "TOTAL:\t\t5,00 zł" + lineSeparator;
    }


    @AfterMethod
    public void tearDown() {
        System.setOut(originalSystemOut);
    }
}