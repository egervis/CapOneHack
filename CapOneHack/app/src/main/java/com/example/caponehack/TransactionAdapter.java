package com.example.caponehack;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

public class TransactionAdapter extends RecyclerView.Adapter<TransactionAdapter.ViewHolder>{

    private List<Transaction> transactions = new ArrayList<>();
    private TransactionClickedListener listener;

    public TransactionAdapter(List<Transaction> transactions, TransactionClickedListener listener) {
        this.transactions = transactions;
        this.listener = listener;
    }

    /**
     * Called when the UI needs the a new row (at {position}) to be <b>created</b>.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_transaction, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Called when the UI needs the next row (at {position}) to be <b>filled with data</b> rendered
     * and passes the {@link ViewHolder} which should be filled with data.
     */
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.merchant.setText(transactions.get(position).getMerchant());
        holder.amount.setText(transactions.get(position).getAmount());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inform the click listener that this row was clicked and pass the Transaction
                // associated with this row.
                //Maybe change this **********************
                if (listener != null) {
                    listener.onTransactionClicked(transactions.get(holder.getAdapterPosition()));
                }
            }
        });
    }

    /**
     * Used to determine how many rows the list should be in total.
     */
    @Override
    public int getItemCount() {
        return transactions.size();
    }

    /**
     * Holds the UI widgets which will comprise a single row in the list (to render
     * a {@link Transaction}).
     */
    static class ViewHolder extends RecyclerView.ViewHolder {

        CardView cardView;

        TextView merchant;

        TextView amount;

        ViewHolder(View rootView) {
            super(rootView);
            cardView = rootView.findViewById(R.id.card_container);
            merchant = rootView.findViewById(R.id.merchant);
            amount = rootView.findViewById(R.id.amount);
        }
    }

    public interface TransactionClickedListener {
        void onTransactionClicked(Transaction transaction);
    }
}