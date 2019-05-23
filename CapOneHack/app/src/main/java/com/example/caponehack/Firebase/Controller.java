package com.example.caponehack.Firebase;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Date;
import java.util.List;

public class Controller {

    //will create a user. Id is returned through callback
    public void createUser(String name,
                           String userType,
                           final OnSuccessListener<String> successListener,
                           final OnFailureListener failureListener) {

    }

    //will create a form with a random id. Id is returned through a callback.
    public void createForm(final OnSuccessListener<String> successListener,
                           final OnFailureListener failureListener) {

    }

    //will create a category. Id is returned through callback.
    public void createCategory(String formId,
                               String categoryName,
                               List<Date> dates,
                               List<String> people,
                               double maxCost,
                               final OnSuccessListener<String> successListener,
                               final OnFailureListener failureListener) {

    }
}
