package com.cybercom.rasinski.pointofsale.application;

import com.cybercom.rasinski.pointofsale.infrastructure.LcdDisplay;
import com.cybercom.rasinski.pointofsale.infrastructure.Printer;

public class SaleService {

    private SaleService() {
    }

    private static final class SaleServiceHolder {
        private static final SaleService INSTANCE = new SaleService();
    }

    public static SaleService getInstance() {
        return SaleServiceHolder.INSTANCE;
    }

    public void sale() {
        BarcodeScanner barcodeScanner;
        LcdDisplay lcdDisplay;
        Printer printer;
    }
}
