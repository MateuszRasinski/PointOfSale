package com.cybercom.rasinski.pointofsale.domain;

import com.cybercom.rasinski.pointofsale.System.Settings;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class Money {
    private final DecimalFormat decimalFormat;
    private BigDecimal amount;
    private Locale currency;

    public Money(String amount) {
        this(new BigDecimal(amount));
    }

    public Money(BigDecimal amount) {
        this(amount, Settings.DEFAULT_LOCALE);
    }

    public Money(String amount, Locale currency) {
        this(new BigDecimal(amount), currency);
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
        return Currency.getInstance(currency).equals(Currency.getInstance(other.currency));
    }

    @Override
    public String toString() {
        return decimalFormat.format(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;

        Money money = (Money) o;

        if (!amount.equals(money.amount)) return false;
        if (!currency.equals(money.currency)) return false;
        if (!decimalFormat.equals(money.decimalFormat)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = decimalFormat.hashCode();
        result = 31 * result + amount.hashCode();
        result = 31 * result + currency.hashCode();
        return result;
    }
}
