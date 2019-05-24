package com.example.caponehack;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Category_Select extends AppCompatActivity {
    public static final String DATE = "DATE";
    public static final String MERCHANT = "MERCHANT";
    public static final String AMOUNT = "AMOUNT";

    private String date;
    private String merchant;
    private String amount;

    private TextView dateTv;
    private TextView merchantTv;
    private TextView amountTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category__select);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        date = getIntent().getStringExtra(DATE);
        merchant = getIntent().getStringExtra(MERCHANT);
        amount = getIntent().getStringExtra(AMOUNT);

        dateTv = findViewById(R.id.trans_date);
        merchantTv = findViewById(R.id.merchant);
        amountTv = findViewById(R.id.amount);
    }

}
