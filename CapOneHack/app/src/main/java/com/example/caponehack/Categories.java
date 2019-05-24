package com.example.caponehack;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

public class Categories extends AppCompatActivity{

    private EditText date1;
    private EditText date2;
    private Button submitDate;
    private String userId;
    private DatePickerDialog.OnDateSetListener mDateSetListener1;
    private DatePickerDialog.OnDateSetListener mDateSetListener2;

    private TransactionListener listener = new TransactionListener() {
        @Override
        public void onSuccess(List<Transaction> list) {
            submitDate.setEnabled(false);

            //populate the view
            System.out.println(list);

        }

        @Override
        public void onFailure(Exception e) {
            Toast.makeText(Categories.this, "Failed to find transactions", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categories);

        date1 = (EditText) findViewById(R.id.date1);
        date2 = (EditText) findViewById(R.id.date2);
        submitDate = findViewById(R.id.date_button);

        submitDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String startDate = date1.getText().toString();
//                String endDate = date2.getText().toString();

                //Test code:
                String startDate = "2019-05-19";
                String endDate = "2019-05-19";
                userId = "5ce3fbb2322fa06b67794e26";

                TransactionFinder finder = new TransactionFinder(startDate, endDate, userId, listener);
                finder.execute();
            }
        });

        date1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Categories.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener1,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                Log.i("clicked","c");
            }
        });

        date2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Categories.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener2,
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

        mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(android.widget.DatePicker view, int year, int month, int day) {
                String date = (month + 1) + "/" + day + "/" + year;
                date2.setText(date);

            }
        };
    }


}
