package com.cybercom.rasinski.pointofsale.domain;

import com.cybercom.rasinski.pointofsale.Settings;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class Money {
    private final DecimalFormat decimalFormat;
    private BigDecimal amount;
    private Currency currency;

    public Money(BigDecimal amount) {
        this(amount, Settings.DEFAULT_LOCALE);
    }

    public Money(BigDecimal amount, Locale currencyLocale) {
        this.amount = amount.setScale(Settings.MAXIMUM_FRACTION_DIGITS, RoundingMode.HALF_UP);
        this.currency = Currency.getInstance(currencyLocale);

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(currencyLocale);
        decimalFormat = (DecimalFormat) numberFormat;
        initDecimalFormatSettings();
    }

    private void initDecimalFormatSettings() {
        decimalFormat.setMinimumFractionDigits(Settings.MINIMUM_FRACTION_DIGITS);
        decimalFormat.setMaximumFractionDigits(Settings.MAXIMUM_FRACTION_DIGITS);
    }

    @Override
    public String toString() {
        return decimalFormat.format(amount);
    }
}
