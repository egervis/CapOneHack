package com.example.caponehack;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class Categories extends AppCompatActivity{

    private EditText date1;
    private EditText date2;
    private Button submitDate;
    private String userId;

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

        date1 = findViewById(R.id.date1);
        date2 = findViewById(R.id.date2);
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
    }
}
