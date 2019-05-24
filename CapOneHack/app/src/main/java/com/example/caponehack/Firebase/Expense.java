package com.example.caponehack.Firebase;

import java.util.Date;

public class Expense {
    private String category;
    private String cost;
    private Date date;
    private String merchant;
    private String location;

    public Expense(String category, String cost, Date date, String merchant, String location) {
        this.category = category;
        this.cost = cost;
        this.date = date;
        this.merchant = merchant;
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public String getCost() {
        return cost;
    }

    public Date getDate() {
        return date;
    }

    public String getMerchant() {
        return merchant;
    }

    public String getLocation() {
        return location;
    }
}
