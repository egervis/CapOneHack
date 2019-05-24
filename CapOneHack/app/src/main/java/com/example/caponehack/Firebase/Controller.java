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

    //will create a user. Id is returned through callback
    public void createUser(String name,
                           String userType,
                           final OnSuccessListener<String> successListener,
                           final OnFailureListener failureListener) {
        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        user.put("userType", userType);
        db.collection("users").add(user).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.i(TAG, "User successfully created.");
                successListener.onSuccess(documentReference.getId());
            }
        }).addOnFailureListener(failureListener);
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
    public void createCategory(String formId,
                               String categoryName,
                               List<Date> dates,
                               double maxCost,
                               final OnSuccessListener successListener,
                               final OnFailureListener failureListener) {
        Map<String, Object> category = new HashMap<>();
        category.put("categoryName", categoryName);
        category.put("maxCost", maxCost);
        category.put("dates", dates);
        db.collection("forms").document(formId).collection("categories").add(category).addOnSuccessListener(successListener).addOnFailureListener(failureListener);
    }
}
