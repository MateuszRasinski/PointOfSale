package com.cybercom.rasinski.pointofsale.domain;

import com.cybercom.rasinski.pointofsale.Settings;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class Money {
    private final DecimalFormat decimalFormat;
    private BigDecimal amount;
    private Locale currency;

    public Money(BigDecimal amount) {
        this(amount, Settings.DEFAULT_LOCALE);
    }

    public Money(BigDecimal amount, Locale currency) {
        this.amount = amount.setScale(Settings.MAXIMUM_FRACTION_DIGITS, RoundingMode.HALF_UP);
        this.currency = currency;

        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(currency);
        decimalFormat = (DecimalFormat) numberFormat;
        initDecimalFormatSettings();
    }

    private void initDecimalFormatSettings() {
        decimalFormat.setMinimumFractionDigits(Settings.MINIMUM_FRACTION_DIGITS);
        decimalFormat.setMaximumFractionDigits(Settings.MAXIMUM_FRACTION_DIGITS);
    }

    public Money add(Money other) {
        if (!isTheSameCurrency(other)) {
            throw new UnsupportedOperationException();
        }
        return new Money(amount.add(other.amount), currency);
    }

    private boolean isTheSameCurrency(Money other) {
        return currency.equals(other.currency);
    }

    @Override
    public String toString() {
        return decimalFormat.format(amount);
    }
}
