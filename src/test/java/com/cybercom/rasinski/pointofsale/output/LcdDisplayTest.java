package com.cybercom.rasinski.pointofsale.output;

import com.cybercom.rasinski.pointofsale.domain.Product;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Locale;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class LcdDisplayTest {
    private PrintStream originalSystemOut;
    private ByteArrayOutputStream systemOutStream;

    @BeforeMethod
    public void setUp() {
        originalSystemOut = System.out;
        systemOutStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOutStream));
    }

    @AfterMethod
    public void tearDown() {
        System.setOut(originalSystemOut);
    }

    @Test
    public void shouldPrintProductInPLN() {
        //given
        LcdDisplay lcdDisplay = new LcdDisplay(new Locale("pl", "PL"));
        BigDecimal price = new BigDecimal("0.33").setScale(2, RoundingMode.HALF_UP);
        Product product = new Product(1L, "Bread", price);
        //when
        lcdDisplay.print(product);
        //then
        String expected = "Bread\t\t0,33 zł";
        assertThat(systemOutStream.toString()).isEqualTo(expected);
    }

    @Test
    public void shouldPrintProductInUSD() {
        //given
        LcdDisplay lcdDisplay = new LcdDisplay(Locale.US);
        BigDecimal price = new BigDecimal("0.33").setScale(2, RoundingMode.HALF_UP);
        Product product = new Product(1L, "Bread", price);
        //when
        lcdDisplay.print(product);
        //then
        String expected = "Bread\t\t$0.33";
        assertThat(systemOutStream.toString()).isEqualTo(expected);
    }

    @Test
    public void shouldPrintTotalSumInPLN() {
        //given
        LcdDisplay lcdDisplay = new LcdDisplay(new Locale("pl", "PL"));
        BigDecimal totalSum = new BigDecimal("54.135").setScale(2, RoundingMode.HALF_UP);
        //when
        lcdDisplay.print(totalSum);
        //then
        String expected = "TOTAL:\t\t54,14 zł";
        assertThat(systemOutStream.toString()).isEqualTo(expected);
    }

    @Test
    public void shouldPrintTotalSumInUSD() {
        //given
        LcdDisplay lcdDisplay = new LcdDisplay(Locale.US);
        BigDecimal totalSum = new BigDecimal("54.135").setScale(2, RoundingMode.HALF_UP);
        //when
        lcdDisplay.print(totalSum);
        //then
        String expected = "TOTAL:\t\t$54.14";
        assertThat(systemOutStream.toString()).isEqualTo(expected);
    }

    @Test
    public void shouldPrintProductNotFound() {
        //given
        LcdDisplay lcdDisplay = new LcdDisplay(new Locale("any locale"));
        //when
        lcdDisplay.printProductNotFound();
        //then
        String expected = "Product not found";
        assertThat(systemOutStream.toString()).isEqualTo(expected);
    }

    @Test
    public void shouldPrintInvalidBarcode() {
        //given
        LcdDisplay lcdDisplay = new LcdDisplay(new Locale("any locale"));
        //when
        lcdDisplay.printInvalidBarcode();
        //then
        String expected = "Invalid bar-code";
        assertThat(systemOutStream.toString()).isEqualTo(expected);
    }
}