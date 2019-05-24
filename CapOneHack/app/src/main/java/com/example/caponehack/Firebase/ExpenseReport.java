package com.example.caponehack.Firebase;

import java.util.ArrayList;
import java.util.List;

public class ExpenseReport {
    private List<Expense> expenses;

    public ExpenseReport() {
        this.expenses = new ArrayList<>();
    }

    public void addExpense(Expense expense)
    {
        this.expenses.add(expense);
    }

    public List<Expense> getExpenses() {
        return expenses;
    }
}
