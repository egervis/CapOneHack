package com.example.caponehack;

import java.util.List;

public interface TransactionListener {

    void onSuccess(List<Transaction> list);

    void onFailure(Exception e);
}
