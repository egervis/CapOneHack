package com.example.caponehack;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.caponehack.Firebase.Controller;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;

public class Categories extends AppCompatActivity implements TransactionAdapter.TransactionClickedListener {

    private EditText date1;
    private EditText date2;
    private Button submitDate;
    private RecyclerView transactionList;
    private RecyclerView.Adapter transactionsAdapter;
    private List<Transaction> list;
    private String userId;
    private DatePickerDialog.OnDateSetListener mDateSetListener1;
    private DatePickerDialog.OnDateSetListener mDateSetListener2;

    //backend
    private String ExpenseId;

    private TransactionListener listener = new TransactionListener() {
        @Override
        public void onSuccess(List<Transaction> list) {
            //submitDate.setEnabled(false);

            //populate the view
            System.out.println(list);

            Categories.this.list.clear();
            Categories.this.list.addAll(list);
            transactionsAdapter.notifyDataSetChanged();
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
        transactionList = findViewById(R.id.transaction_list);

        list = new ArrayList<>();
        transactionsAdapter = new TransactionAdapter(list, Categories.this);
        transactionList.setLayoutManager(new LinearLayoutManager(Categories.this));
        transactionList.setAdapter(transactionsAdapter);

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


        //backend
        Controller cont = new Controller();
        cont.createExpenseReport("Dorian", new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String s) {
                Categories.this.ExpenseId = s;
            }
        }, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        });
    }

    @Override
    public void onTransactionClicked(final Transaction transaction) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Categories.this);
        builder.setTitle("Select Reimbursement Category");

        final Spinner spinner = new Spinner(Categories.this);

        List<String> spinnerItems = new ArrayList<>();
        spinnerItems.add("Food");
        spinnerItems.add("Travel");
        spinnerItems.add("Gas");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerItems);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String[] a = transaction.getDate().split("-");

                Controller expense = new Controller();
                expense.createExpense(ExpenseId, String.valueOf(spinner.getSelectedItem()),
                        Double.parseDouble(transaction.getAmount()), new Date(Integer.parseInt(a[0]), Integer.parseInt(a[1]), Integer.parseInt(a[2])),
                        transaction.getMerchant(), "19 Bele Street, Arlington, Virginia",
                        new OnSuccessListener<Void>(){
                            @Override
                            public void onSuccess(Void aVoid) {

                            }
                        },
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });

            }
        });
        builder.setView(spinner);

        builder.show();



//        Intent intent = new Intent(Categories.this, Category_Select.class);
//        intent.putExtra(Category_Select.DATE, transaction.getDate());
//        intent.putExtra(Category_Select.MERCHANT, transaction.getMerchant());
//        intent.putExtra(Category_Select.AMOUNT, transaction.getAmount());
//        startActivity(intent);
    }
}
