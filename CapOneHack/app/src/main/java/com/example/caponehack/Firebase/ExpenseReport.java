package com.example.caponehack.Firebase;

import java.util.ArrayList;
import java.util.List;

public class ExpenseReport {
    private List<Expense> expenses;
    private String name;

    public ExpenseReport(String name) {
        this.expenses = new ArrayList<>();
        this.name = name;
    }

    public void addExpense(Expense expense)
    {
        this.expenses.add(expense);
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public String getName() {
        return name;
    }
}
