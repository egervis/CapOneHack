package com.example.caponehack;

import java.io.Serializable;

public class Transaction implements Serializable{
/**
 * Represents a simple credit card transaction -- containing the merchant and the amount.
 */

    private final String merchant;

    private final String amount;

    private final String date;

    public Transaction(String merchant, String amount, String date) {
        this.merchant = merchant;
        this.amount = amount;
        this.date = date;
    }

    public String getMerchant() {
        return merchant;
    }

    public String getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Date: " + date + "Merchant: " + merchant + ", Amount: " + amount;
    }

}
