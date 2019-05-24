package com.example.caponehack;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MyAdapter extends ArrayAdapter<StateV0> {
    private Context mContext;
    private ArrayList<StateV0> listState;
    private MyAdapter myAdapter;
    private boolean isFromView = false;


    private HashMap<String,String> dates;
    private HashMap<String, String> limits;


    public MyAdapter(Context context, int resource, List<StateV0> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.listState = (ArrayList<StateV0>) objects;
        this.myAdapter = this;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(final int position, View convertView,
                              ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.spinner_item, null);
            holder = new ViewHolder();
            holder.mTextView = (TextView) convertView
                    .findViewById(R.id.text);
            holder.mCheckBox = (CheckBox) convertView
                    .findViewById(R.id.checkbox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTextView.setText(listState.get(position).getTitle());

        // To check weather checked event fire from getview() or user input
        isFromView = true;
        holder.mCheckBox.setChecked(listState.get(position).isSelected());
        isFromView = false;

        if ((position == 0)) {
            holder.mCheckBox.setVisibility(View.INVISIBLE);
        } else {
            holder.mCheckBox.setVisibility(View.VISIBLE);
        }
        holder.mCheckBox.setTag(position);
        //Checkbox change listener
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int getPosition = (Integer) buttonView.getTag();

                if (!isFromView) {
                    listState.get(position).setSelected(isChecked);

                    if(listState.get(position).isSelected()){
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        builder.setTitle("Input Date and Price Limit");


// Set up the input
                        final EditText input1 = new EditText(mContext);
                        final EditText input2 = new EditText(mContext);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
                        input1.setInputType(InputType.TYPE_CLASS_DATETIME | InputType.TYPE_DATETIME_VARIATION_DATE);
                        input2.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
                        LinearLayout container = new LinearLayout(mContext);
                        container.setOrientation(LinearLayout.VERTICAL);
                        container.addView(input1);
                        container.addView(input2);
                        builder.setView(container);

// Set up the buttons
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String date = input1.getText().toString();
                                dates.put(listState.get(position).getTitle(),date);
                                String limit = input2.getText().toString();
                                limits.put(listState.get(position).getTitle(),limit);

                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        builder.show();
                    }
                }


            }
        });
        return convertView;
    }

    private class ViewHolder {
        private TextView mTextView;
        private CheckBox mCheckBox;
    }

    public ArrayList<String> getCategories(){
        ArrayList<String> categories = new ArrayList<>();

        for(StateV0 item : listState){
            categories.add(item.getTitle());
        }

        return categories;
    }

    public List<Date> getDate(String category){

        String d=dates.get(category);
        String [] dateRange= d.split("-");

        List<Date> toReturn=new ArrayList<>();

        for(String s: dateRange){
           Date t=new Date(s);
           toReturn.add(t);
        }
        return toReturn;
    }

    public double getLimit(String category){
        return Double.parseDouble(limits.get(category));
    }
}
