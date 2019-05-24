package com.example.caponehack.Firebase;

import android.support.annotation.NonNull;
import com.firebase.ui.firestore.SnapshotParser;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.Date;
import java.util.List;

public class Category {
    private String categoryName;
    private List<Date> dates;
    private double maxCost;
    public final static SnapshotParser<Category> SNAPSHOTPARSER = new SnapshotParser<Category>() {
        @NonNull
        @Override
        public Category parseSnapshot(@NonNull DocumentSnapshot snapshot) {
            return new Category(snapshot.getString("categoryName"),
                    (List<Date>)snapshot.get("dates"),
                    snapshot.getDouble("maxCost"));
        }
    };

    public Category(String categoryName, List<Date> dates, double maxCost)
    {
        this.categoryName = categoryName;
        this.dates = dates;
        this.maxCost = maxCost;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<Date> getDates() {
        return dates;
    }

    public double getMaxCost() {
        return maxCost;
    }
}
