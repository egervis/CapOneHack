package com.example.caponehack.Firebase;

import android.support.annotation.NonNull;

import com.firebase.ui.firestore.SnapshotParser;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.Date;

public class Expense {
    private String category;
    private double cost;
    private Date date;
    private String merchant;
    private String location;
    public final static SnapshotParser<Expense> SNAPSHOTPARSER = new SnapshotParser<Expense>() {
        @NonNull
        @Override
        public Expense parseSnapshot(@NonNull DocumentSnapshot snapshot) {
            return new Expense(snapshot.getString("category"),
                    snapshot.getDouble("cost"),
                    snapshot.getDate("date"),
                    snapshot.getString("location"),
                    snapshot.getString("merchant"));
        }
    };

    public Expense(String category, double cost, Date date, String merchant, String location) {
        this.category = category;
        this.cost = cost;
        this.date = date;
        this.merchant = merchant;
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public double getCost() {
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
