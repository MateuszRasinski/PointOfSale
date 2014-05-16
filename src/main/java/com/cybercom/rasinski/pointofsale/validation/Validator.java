package com.cybercom.rasinski.pointofsale.validation;

public class Validator {
    public static void checkNotNull(Object object) {
        if (object == null) {
            throw new NullPointerException();
        }
    }
}
