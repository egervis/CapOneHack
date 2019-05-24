package com.example.caponehack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.view.View.OnClickListener;

import java.util.ArrayList;
import java.util.List;

public class hr_makeForm extends AppCompatActivity {

    private List<Spinner> categories=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr_make_form);


        final Button addButton = findViewById(R.id.button);

        addButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Spinner aSpinner = new Spinner(hr_makeForm.this);
                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(hr_makeForm.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.category_array));
               // spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                aSpinner.setAdapter(spinnerArrayAdapter);
                categories.add(aSpinner);
                LinearLayout container=findViewById(R.id.spinner_container);
                container.addView(aSpinner);
            }
        });
    }

    /*
    public void addItemsOnSpinner(){
        Spinner aSpinner= (Spinner) findViewById(R.id.)
    }
    */



}
