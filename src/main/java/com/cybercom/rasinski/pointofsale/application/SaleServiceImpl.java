package com.cybercom.rasinski.pointofsale.application;

import com.cybercom.rasinski.pointofsale.System.Settings;
import com.cybercom.rasinski.pointofsale.application.output.SingleProductOutput;
import com.cybercom.rasinski.pointofsale.application.output.SummaryOutput;
import com.cybercom.rasinski.pointofsale.domain.Barcode;
import com.cybercom.rasinski.pointofsale.domain.Product;
import com.cybercom.rasinski.pointofsale.domain.ShoppingCart;
import com.cybercom.rasinski.pointofsale.infrastructure.ProductNotFoundException;

import java.util.List;

import static com.cybercom.rasinski.pointofsale.System.validation.Validator.checkNotNull;

public class SaleServiceImpl implements SaleService {
    public static final String EXIT = "exit";
    private BarcodeScanner barcodeScanner;
    private List<SingleProductOutput> singleProductOutputs;
    private List<SummaryOutput> summaryOutputs;
    private ShoppingCart shoppingCart;

    public SaleServiceImpl(BarcodeScanner barcodeScanner, List<SingleProductOutput> singleProductOutputs,
                           List<SummaryOutput> summaryOutputs) {
        this(barcodeScanner, singleProductOutputs, summaryOutputs, new ShoppingCart(Settings.DEFAULT_LOCALE));
    }

    public SaleServiceImpl(BarcodeScanner barcodeScanner, List<SingleProductOutput> singleProductOutputs,
                           List<SummaryOutput> summaryOutputs, ShoppingCart cart) {
        this.barcodeScanner = barcodeScanner;
        this.singleProductOutputs = singleProductOutputs;
        this.summaryOutputs = summaryOutputs;
        this.shoppingCart = cart;
    }

    public void scan(String input) {
        checkNotNull(input);

        if (isExit(input)) {
            printSummary();
            return;
        }

        scanSingleProductAndHandleExceptions(input);
    }

    private boolean isExit(String input) {
        return input.equals(EXIT);
    }

    private void scanSingleProductAndHandleExceptions(String input) {
        try {
            scanSingleProduct(new Barcode(input));
        } catch (ProductNotFoundException e) {
            printProductNotFound();
        } catch (InvalidBarcodeException e) {
            printInvalidBarcode();
        }
    }

    private void scanSingleProduct(Barcode barcode) {
        Product product = barcodeScanner.scan(barcode);
        shoppingCart.add(product);
        print(product);
    }

    private void printSummary() {
        for (SummaryOutput summaryOutput : summaryOutputs) {
            summaryOutput.printSummary(shoppingCart);
        }
    }

    private void printInvalidBarcode() {
        for (SingleProductOutput singleProductOutput : singleProductOutputs) {
            singleProductOutput.printInvalidBarcode();
        }
    }

    private void printProductNotFound() {
        for (SingleProductOutput singleProductOutput : singleProductOutputs) {
            singleProductOutput.printProductNotFound();
        }
    }

    private void print(Product product) {
        for (SingleProductOutput singleProductOutput : singleProductOutputs) {
            singleProductOutput.print(product);
        }
    }
}
