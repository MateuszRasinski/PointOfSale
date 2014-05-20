package com.cybercom.rasinski.pointofsale.System.validation;

public final class Validator {
    private Validator() {}

    public static void checkNotNull(Object object) {
        if (object == null) {
            throw new NullPointerException();
        }
    }

    public static boolean checkNotEmpty(String string) {
        return string != null && !string.isEmpty();
    }
}
