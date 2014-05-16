package com.cybercom.rasinski.pointofsale.domain;

import static com.cybercom.rasinski.pointofsale.validation.Validator.checkNotNull;

public class Barcode {
    private String code;

    public Barcode(String code) {
        checkNotNull(code);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return getCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Barcode)) {
            return false;
        }

        Barcode barcode = (Barcode) o;

        if (!code.equals(barcode.code)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }
}
