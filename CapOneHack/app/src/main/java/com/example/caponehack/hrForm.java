package com.example.caponehack;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.caponehack.Firebase.Controller;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class hrForm extends AppCompatActivity {
    private EditText date1;
    private DatePickerDialog.OnDateSetListener mDateSetListener1;
    private EditText codeText;
    private String formId;
    private EditText EditText1, EditText2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr_form);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        EditText1 = findViewById(R.id.category);
        EditText2 = findViewById(R.id.max_price);
        codeText = findViewById(R.id.code);


        date1 = (EditText) findViewById(R.id.date1);

        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        hrForm.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener1,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                Log.i("clicked","c");
            }
        });
        mDateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int month, int day) {
                String date = (month + 1) + "/" + day + "/" + year;
                date1.setText(date);

            }
        };

        Controller c = new Controller();
        if(EditText1.getText()!=null && EditText2.getText()!=null && date1.getText()!=null) {
            c.createForm("hrForm", new OnSuccessListener<String>() {
                @Override
                public void onSuccess(String s) {
                    formId = s;
                    codeText.setText("Form ID: " + s);
                }
            }, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    ;
                }
            });
        }
    }
    public void onClick(View v){
        Controller c = new Controller();
        String[] arr = date1.getText().toString().split("-");
        ArrayList<Date> lst = new ArrayList<>();
        for(String s:arr)
        {
            lst.add(new Date(s));
        }
        c.createCategory(formId, EditText1.getText().toString(), lst, Double.parseDouble(EditText2.getText().toString()), new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(getApplicationContext(),"Successfully made a new form", Toast.LENGTH_SHORT);
                EditText1.setText("");
                EditText2.setText("");
                date1.setText("");
            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }


}
