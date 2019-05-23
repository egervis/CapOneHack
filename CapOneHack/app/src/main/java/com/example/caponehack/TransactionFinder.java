package com.example.caponehack;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;

public class TransactionFinder extends AsyncTask<Void, Void, String> {

    private static String baseUrl = "http://api.reimaginebanking.com/accounts/";

    private String date1;
    private String date2;
    private String userId;
    private TransactionListener listener;

    public TransactionFinder(String d1, String d2, String id, TransactionListener listener){
        date1 = d1;
        date2 = d2;
        userId = id;
        this.listener = listener;
    }

    public TransactionFinder(){
        userId = "5ce3fbb2322fa06b67794e25";
        date1 = "2019-05-19";
        date2 = "2019-05-24";
    }

    @Override
    protected String doInBackground(Void... voids) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(logging).build();

        Request request = new Request.Builder()
                .get()
                .url(baseUrl + userId + "/purchases?key=bba75eeeefb1bcfbc715be5edfaba980")
                .build();

        try {
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            return body != null ? body.string() : null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(String response){
        if(response == null) {
            return;
        }

        try {
            JSONArray rootObject = new JSONArray(response);
            for(int i = 0; i < rootObject.length(); i++){
                JSONObject curr = rootObject.getJSONObject(i);
                curr.getString("type");
                curr.getDouble("amount");
            }
            System.out.println(rootObject);
        } catch(Exception e) {
            return;
        }
    }
}
