package com.example.caponehack.Firebase;

import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class HackView {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private final static String TAG = HackView.class.getName();

    public void getForm(final String formId,
                        final OnSuccessListener<Form> onSuccessListener,
                        final OnFailureListener onFailureListener) {
        db.collection("forms").document(formId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                final String formName = documentSnapshot.getString("formName");
                final String formId = documentSnapshot.getId();
                db.collection("forms").document(formId).collection("categories")
                        .get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Form form = new Form(formName, formId);
                        for (QueryDocumentSnapshot docSnapshot:queryDocumentSnapshots) {
                            try {
                                Category category = Category.SNAPSHOTPARSER.parseSnapshot(docSnapshot);
                                form.addCategory(category);
                            }catch (NullPointerException e) {
                                Log.e(TAG, "Error parsing Category ", e);
                            }
                        }
                        onSuccessListener.onSuccess(form);
                    }
                }).addOnFailureListener(onFailureListener);
            }
        }).addOnFailureListener(onFailureListener);
    }

    public void getUser(final String name,
                        final OnSuccessListener<User> onSuccessListener,
                        final OnFailureListener onFailureListener) {
        db.collection("users").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots)
                {
                    if(documentSnapshot.getString("name").equals(name))
                    {
                        User user = User.SNAPSHOTPARSER.parseSnapshot(documentSnapshot);
                        onSuccessListener.onSuccess(user);
                        break;
                    }
                }
            }
        }).addOnFailureListener(onFailureListener);
    }

    public void getUserById(final String userId,
                            final OnSuccessListener<User> onSuccessListener,
                            final OnFailureListener onFailureListener) {
        db.collection("users").document(userId).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                User user = User.SNAPSHOTPARSER.parseSnapshot(documentSnapshot);
                onSuccessListener.onSuccess(user);
            }
        }).addOnFailureListener(onFailureListener);
    }

    public void getExpenseReport(final String userName,
                                 final OnSuccessListener<ExpenseReport> onSuccessListener,
                                 final OnFailureListener onFailureListener) {
        db.collection("expenseReports").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots)
                {
                    if(documentSnapshot.getString("userName").equals(userName))
                    {
                        final String expenseReportId = documentSnapshot.getId();
                        db.collection("expenseReports").document(expenseReportId).collection("expenses").get()
                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                    @Override
                                    public void onSuccess(QuerySnapshot snaps) {
                                        ExpenseReport expenseReport = new ExpenseReport(userName);
                                        for(QueryDocumentSnapshot snap : snaps)
                                        {
                                            expenseReport.addExpense(Expense.SNAPSHOTPARSER.parseSnapshot(snap));
                                        }
                                        onSuccessListener.onSuccess(expenseReport);
                                    }
                                }).addOnFailureListener(onFailureListener);
                        break;
                    }
                }
            }
        }).addOnFailureListener(onFailureListener);
    }
}
