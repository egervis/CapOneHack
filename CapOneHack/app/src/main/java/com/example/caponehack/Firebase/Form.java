package com.example.caponehack.Firebase;

import java.util.ArrayList;
import java.util.List;

public class Form {
    private List<Category> categories;
    private String formName;
    public Form(String formName) {
        this.formName = formName;
        categories = new ArrayList<>();
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void addCategory(Category c)
    {
        this.categories.add(c);
    }

    public String getFormName() {
        return this.formName;
    }
}
