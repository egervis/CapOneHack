package com.example.caponehack;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Categories extends AppCompatActivity implements TransactionAdapter.TransactionClickedListener {

    private EditText date1;
    private EditText date2;
    private Button submitDate;
    private RecyclerView transactionList;
    private RecyclerView.Adapter transactionsAdapter;
    private List<Transaction> list;
    private String userId;

    private TransactionListener listener = new TransactionListener() {
        @Override
        public void onSuccess(List<Transaction> list) {
            submitDate.setEnabled(false);

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

        date1 = findViewById(R.id.date1);
        date2 = findViewById(R.id.date2);
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
    }

    @Override
    public void onTransactionClicked(Transaction transaction) {
        Toast.makeText(this, transaction.toString(), Toast.LENGTH_LONG);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.show();
    }
}
