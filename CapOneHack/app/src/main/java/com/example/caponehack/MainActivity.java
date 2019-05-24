package com.example.caponehack;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.caponehack.Firebase.Controller;
import com.example.caponehack.Firebase.Form;
import com.example.caponehack.Firebase.HackView;
import com.example.caponehack.Firebase.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

          Controller c = new Controller();
//        c.createUser("Dave White", "regular", new OnSuccessListener<String>() {
//            @Override
//            public void onSuccess(String s) {
//                ;
//            }
//        }, new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                ;
//            }
//        });
//        c.createForm("Summit", new OnSuccessListener<String>() {
//            @Override
//            public void onSuccess(String s) {
//                ;
//            }
//        }, new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                ;
//            }
//        });
//        List<Date> d = new ArrayList<Date>();
//        d.add(new Date());
//        c.createCategory("a9RZm888Z9p4JdTlYKjV", "Taxi", d,
//                 50, new OnSuccessListener() {
//                    @Override
//                    public void onSuccess(Object o) {
//                        ;
//                    }
//                }, new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        ;
//                    }
//                });

        HackView v = new HackView();
//        v.getForm("a9RZm888Z9p4JdTlYKjV", new OnSuccessListener<Form>() {
//            @Override
//            public void onSuccess(Form form) {
//                System.out.println(form.getFormName());
//                System.out.println(form.getCategories().get(0).getCategoryName());
//                System.out.println(form.getCategories().get(0).getDates().get(0));
//                System.out.println(form.getCategories().get(0).getMaxCost());
//            }
//        }, new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                ;
//            }
//        });
//        v.getUser("Dave White", new OnSuccessListener<User>() {
//            @Override
//            public void onSuccess(User user) {
//                System.out.println(user.getName());
//                System.out.println(user.getUserType());
//                System.out.println(user.getUserId());
//            }
//        }, new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                ;
//            }
//        });
    }

}
