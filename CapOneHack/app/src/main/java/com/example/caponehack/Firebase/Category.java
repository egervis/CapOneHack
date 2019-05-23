package com.example.caponehack.Firebase;

import android.support.annotation.NonNull;
import com.firebase.ui.firestore.SnapshotParser;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.Date;
import java.util.List;

public class Category {
    private String categoryName;
    private List<Date> dates;
    private List<String> people;
    private double maxCost;
    public final static SnapshotParser<Category> SNAPSHOTPARSER = new SnapshotParser<Category>() {
        @NonNull
        @Override
        public Category parseSnapshot(@NonNull DocumentSnapshot snapshot) {
            return new Category(snapshot.getString("categoryName"),
                    (List<Date>)snapshot.get("dates"),
                    (List<String>) snapshot.get("people"),
                    snapshot.getDouble("maxCost"));
        }
    };

    public Category(String categoryName, List<Date> dates, List<String> people, double maxCost)
    {
        this.categoryName = categoryName;
        this.dates = dates;
        this.people = people;
        this.maxCost = maxCost;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public List<Date> getDates() {
        return dates;
    }

    public List<String> getPeople() {
        return people;
    }

    public double getMaxCost() {
        return maxCost;
    }
}
