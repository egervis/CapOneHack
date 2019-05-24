package com.example.caponehack.Firebase;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class Controller {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final static String TAG = Controller.class.getName();

    //will create a user.
    public void createUser(final String userId,
                           final String name,
                           final String userType,
                           final OnSuccessListener<Void> successListener,
                           final OnFailureListener failureListener) {
        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("userType", userType);
        db.collection("users").document(userId).set(user).addOnSuccessListener(successListener).addOnFailureListener(failureListener);
    }

    //will create a form with a random id. Id is returned through a callback.
    public void createForm(final String formName,
                           final OnSuccessListener<String> successListener,
                           final OnFailureListener failureListener) {
        Map<String, Object> form = new HashMap<>();
        form.put("formName", formName);
        db.collection("forms").add(form).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.i(TAG, "From successfully created.");
                successListener.onSuccess(documentReference.getId());
            }
        }).addOnFailureListener(failureListener);
    }

    //will create a category.
    public void createCategory(final String formId,
                               final String categoryName,
                               final List<Date> dates,
                               final double maxCost,
                               final OnSuccessListener successListener,
                               final OnFailureListener failureListener) {
        Map<String, Object> category = new HashMap<>();
        category.put("categoryName", categoryName);
        category.put("maxCost", maxCost);
        category.put("dates", dates);
        db.collection("forms").document(formId).collection("categories").add(category).addOnSuccessListener(successListener).addOnFailureListener(failureListener);
    }

    public void createExpenseReport(final String userName,
                                    final OnSuccessListener<String> successListener,
                                    final OnFailureListener failureListener) {
        Map<String, Object> expenseReport = new HashMap<>();
        expenseReport.put("userName", userName);
        db.collection("expenseReports").add(expenseReport).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.i(TAG, "Expense Report successfully created.");
                successListener.onSuccess(documentReference.getId());
            }
        }).addOnFailureListener(failureListener);
    }

    public void createExpense(final String expenseReportId,
                              final String category,
                              final double cost,
                              final Date date,
                              final String merchant,
                              final String location,
                              final OnSuccessListener successListener,
                              final OnFailureListener failureListener) {
        Map<String, Object> expense = new HashMap<>();
        expense.put("category", category);
        expense.put("cost", cost);
        expense.put("date", date);
        expense.put("merchant", merchant);
        expense.put("location", location);
        db.collection("expenseReports").document(expenseReportId).collection("expenses").add(expense).addOnSuccessListener(successListener).addOnFailureListener(failureListener);
    }
}
