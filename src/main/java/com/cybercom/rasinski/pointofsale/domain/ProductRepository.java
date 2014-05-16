package com.cybercom.rasinski.pointofsale.domain;

public interface ProductRepository {
    Product findByBarcode(Barcode barcode);
}
