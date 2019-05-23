package com.example.caponehack.Firebase;

import java.util.ArrayList;
import java.util.List;

public class Form {
    private List<Category> categories;
    public Form() {
        categories = new ArrayList<>();
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void addCategory(Category c)
    {
        this.categories.add(c);
    }
}
