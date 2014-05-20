package com.cybercom.rasinski.pointofsale.application;

import com.cybercom.rasinski.pointofsale.domain.Barcode;
import com.cybercom.rasinski.pointofsale.domain.Product;
import com.cybercom.rasinski.pointofsale.domain.ProductRepository;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BarcodeScannerTest {

    private ProductRepository productRepository;

    @BeforeClass
    public void setUp() {
        productRepository = mock(ProductRepository.class);
    }

    @Test
    public void shouldScan() {
        //given
        BarcodeScanner barcodeScanner = new BarcodeScanner(productRepository);

        Product productMock = mock(Product.class);
        when(productRepository.findByBarcode(any(Barcode.class))).thenReturn(productMock);
        //when
        Product product = barcodeScanner.scan(new Barcode("x"));
        //then
        assertThat(product).isEqualTo(productMock);
    }

    @Test(expectedExceptions = InvalidBarcodeException.class)
    public void shouldThrowInvalidBarcodeException() {
        //given
        BarcodeScanner barcodeScanner = new BarcodeScanner(productRepository);
        //when
        barcodeScanner.scan(new Barcode(""));
    }
}