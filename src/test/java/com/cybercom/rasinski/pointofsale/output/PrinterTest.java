package com.cybercom.rasinski.pointofsale.output;

import com.cybercom.rasinski.pointofsale.domain.Product;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PrinterTest {
    private List<Product> products;
    private ByteArrayOutputStream systemOutStream;
    private PrintStream originalSystemOut;


    @BeforeMethod
    public void setUp() {
        Product[] productsArray = {
                new Product(1L, "Sample product", new BigDecimal(3.5)),
                new Product(2L, "Soap", new BigDecimal(1.5))
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
        BigDecimal totalSum = new BigDecimal(5);
        //when
        printer.print(products, totalSum);
        //then
        String expectedString = getExpectedString();
        assertThat(systemOutStream.toString()).isEqualTo(expectedString);
    }


    private String getExpectedString() {
        String lineSeparator = System.lineSeparator();
        StringBuilder sb = new StringBuilder();
        sb.append("Name\t\tPrice")
                .append(lineSeparator)
                .append("Sample product\t\t3.5")
                .append(lineSeparator)
                .append("Soap\t\t1.5")
                .append(lineSeparator)
                .append("TOTAL:\t\t5")
                .append(lineSeparator);
        return sb.toString();
    }


    @AfterMethod
    public void tearDown() {
        System.setOut(originalSystemOut);
    }
}