package com.example.caponehack;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.view.View.OnClickListener;

import com.example.caponehack.Firebase.Controller;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class hr_makeForm extends AppCompatActivity {

    private List<Spinner> categories=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr_make_form);

        Spinner spinner = (Spinner) findViewById(R.id.spinner1);

        ArrayList<StateV0> listVOs = new ArrayList<>();
        String [] options =  getResources().getStringArray(R.array.category_array);

        for (int i = 0; i < options.length; i++) {
            StateV0 stateVO = new StateV0();
            stateVO.setTitle(options[i]);
            stateVO.setSelected(false);
            listVOs.add(stateVO);
        }

        final MyAdapter myAdapter = new MyAdapter(hr_makeForm.this, 0,
                listVOs);
        spinner.setAdapter(myAdapter);

        Button finish= findViewById(R.id.finish_button);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Controller control = new Controller();
                control.createForm("hr-form", new OnSuccessListener<String>() {
                    @Override
                    public void onSuccess(String s) {

                        ArrayList<String> categories=myAdapter.getCategories();

                        for(String category : categories){
                            List<Date> range = myAdapter.getDate(category);
                            double limit = myAdapter.getLimit(category);
                            control.createCategory(s, category, range, limit, new OnSuccessListener() {
                                @Override
                                public void onSuccess(Object o) {

                                }
                            }, new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    
                                }
                            });
                        }
                    }
                }, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });




            }
        });
    }

    /*
    public void addItemsOnSpinner(){
        Spinner aSpinner= (Spinner) findViewById(R.id.)
    }
    */



}
