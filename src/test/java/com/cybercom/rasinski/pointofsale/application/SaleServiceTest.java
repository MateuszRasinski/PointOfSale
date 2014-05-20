package com.cybercom.rasinski.pointofsale.application;

import com.cybercom.rasinski.pointofsale.domain.Product;
import com.cybercom.rasinski.pointofsale.domain.ProductRepository;
import com.cybercom.rasinski.pointofsale.infrastructure.LcdDisplay;
import com.cybercom.rasinski.pointofsale.infrastructure.Printer;
import com.cybercom.rasinski.pointofsale.infrastructure.ProductRepositoryImpl;
import com.cybercom.rasinski.pointofsale.infrastructure.ProductTableStub;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.cybercom.rasinski.pointofsale.application.Output.LINE_SEPARATOR;
import static com.cybercom.rasinski.pointofsale.application.Output.TABBED_SPACE;
import static com.cybercom.rasinski.pointofsale.application.Output.TOTAL;
import static org.fest.assertions.api.Assertions.assertThat;

public class SaleServiceTest {
    private PrintStream originalSystemOut;
    private ByteArrayOutputStream systemOutStream;
    private BarcodeScanner barcodeScanner;

    @BeforeClass
    public void setUpClass() {
        barcodeScanner = setUpBarcodeScanner();
    }

    @BeforeMethod
    public void setUp() {
        setUpSystemOut();

        saleService = new SaleServiceImpl(barcodeScanner, initOutputs());
    }

    private void setUpSystemOut() {
        originalSystemOut = System.out;
        systemOutStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(systemOutStream));
    }

    @AfterMethod
    public void tearDown() {
        System.setOut(originalSystemOut);
    }

    private SaleService saleService;

    private BarcodeScanner setUpBarcodeScanner() {
        Map<Long, Product> productTable = ProductTableStub.getProductTable();
        ProductRepository productRepository = new ProductRepositoryImpl(productTable);
        return new BarcodeScanner(productRepository);
    }

    private List<Output> initOutputs() {
        LcdDisplay lcdDisplay = new LcdDisplay();
        Printer printer = new Printer();

        List<Output> outputs = new ArrayList<>();
        outputs.add(lcdDisplay);
        outputs.add(printer);
        return outputs;
    }

    @Test
    public void shouldScanValidBarcode() {
        //given
        String validBarcode = "11";
        //when
        saleService.scan(validBarcode);
        //then
        String expectedString = "Chleb" + TABBED_SPACE + "3,43 zł";
        assertThat(systemOutStream.toString()).isEqualTo(expectedString);
    }

    @Test
    public void shouldScanEmptyBarcode() {
        //given
        String invalidBarcode = "";
        //when
        saleService.scan(invalidBarcode);
        //then
        String expectedString = "Invalid bar-code";
        assertThat(systemOutStream.toString()).isEqualTo(expectedString);
    }

    @Test
    public void shouldScanBarcodeOfProductNotInDB() {
        //given
        String barcodeNotInDB = "xx";
        //when
        saleService.scan(barcodeNotInDB);
        //then
        String expectedString = "Product not found";
        assertThat(systemOutStream.toString()).isEqualTo(expectedString);
    }

    @Test(dependsOnMethods = "shouldScanValidBarcode")
    public void shouldScanProductsAndExitAndPrintSummary() {
        //given
        String[] barcodes = {"11", "22", "33", "44"};
        //when
        for (String barcode : barcodes) {
            saleService.scan(barcode);
        }
        saleService.scan(SaleService.EXIT);
        //then
        String expectedLcdString = "Chleb" + TABBED_SPACE + "3,43 zł"
                + "Mleko" + TABBED_SPACE + "2,49 zł"
                + "Czekolada" + TABBED_SPACE + "1,19 zł"
                + "Piwo" + TABBED_SPACE + "4,99 zł"
                + TOTAL + TABBED_SPACE + "12,10 zł";
        String expectedPrinterString = Printer.HEADER
                + "Chleb" + TABBED_SPACE + "3,43 zł" + LINE_SEPARATOR
                + "Mleko" + TABBED_SPACE + "2,49 zł" + LINE_SEPARATOR
                + "Czekolada" + TABBED_SPACE + "1,19 zł" + LINE_SEPARATOR
                + "Piwo" + TABBED_SPACE + "4,99 zł" + LINE_SEPARATOR
                + TOTAL + TABBED_SPACE + "12,10 zł" + LINE_SEPARATOR;
        assertThat(systemOutStream.toString()).isEqualTo(expectedLcdString + expectedPrinterString);
    }

    @Test
    public void shouldScanExitAndPrintSummary() {
        //when
        saleService.scan(SaleService.EXIT);
        //then
        String expectedLcdString = "TOTAL:" + TABBED_SPACE + "0,00 zł";
        String expectedPrinterString = "Name" + TABBED_SPACE + "Price" + LINE_SEPARATOR
                + "TOTAL:" + TABBED_SPACE + "0,00 zł" + LINE_SEPARATOR;
        assertThat(systemOutStream.toString()).isEqualTo(expectedLcdString + expectedPrinterString);
    }
}