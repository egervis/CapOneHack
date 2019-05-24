package com.example.caponehack;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.caponehack.Firebase.Controller;
import com.example.caponehack.Firebase.ExpenseReport;
import com.example.caponehack.Firebase.Form;
import com.example.caponehack.Firebase.HackView;
import com.example.caponehack.Firebase.User;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 773;
    private static final String TAG = MainActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            checkSignIn();
        } else {
            startActivityForResult(
                    // Get an instance of AuthUI based on the default app
                    AuthUI.getInstance().createSignInIntentBuilder()
                            .setAvailableProviders(Arrays.asList(
                                    new AuthUI.IdpConfig.EmailBuilder().build()))
                            .build(),
                    RC_SIGN_IN);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.v(TAG, "Request Code: " + requestCode);
        Log.v(TAG, "Result Code: " + resultCode);

        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // Successfully signed in
            if (resultCode == RESULT_OK) {
                Log.i(TAG, "Successfully logged in");
                checkSignIn();
            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    Log.d(TAG, "User did not sign in");
                    return;
                }

                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    Log.d(TAG, "No internet connection");
                    return;
                }

                Log.e(TAG, "Sign-in error: ", response.getError());
            }
        }
    }

    private void checkSignIn() {
        final FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            HackView view = new HackView();

            view.getUserById(auth.getCurrentUser().getUid(), new OnSuccessListener<User>() {
                @Override
                public void onSuccess(User user) {
                    if (user == null) {
                        Log.v(TAG, "First time student signs in to this app");

                        final String userId, userFirstName, userLastName;
                        final String userName = auth.getCurrentUser().getDisplayName();
                        userId = auth.getCurrentUser().getUid();

                        Controller controller = new Controller();
                        controller.createUser(userName,
                                "regular",
                                new OnSuccessListener<String>() {
                                    @Override
                                    public void onSuccess(String s) {
                                        Intent intent = new Intent(MainActivity.this, SelectionActivity.class);

                                        intent.putExtra("userName",userName);
                                        intent.putExtra("userType","regular");
                                        intent.putExtra("userId",userId);


                                        startActivity(intent);
                                        finish();

                                    }
                                }, new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.e(TAG, "Error creating the user");
                                    }
                                });

                    } else {
                        Intent intent = new Intent(MainActivity.this, SelectionActivity.class);
                        intent.putExtra("userName",auth.getCurrentUser().getDisplayName());
                        intent.putExtra("userType","regular");
                        intent.putExtra("userId",auth.getCurrentUser().getUid());
                        startActivity(intent);
                        finish();
                    }
                }
            }, new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e(TAG, "Couldn't check if user exists", e);
                    // todo tell them we couldn't sign them in and try again later?
                }
            });

        } else {
            // This should never happen
            NullPointerException exc = new NullPointerException("User signed in but null user");
            Log.e(TAG, "User attempted to sign in but auth wasn't initialized", exc);
            throw exc;
        }
    }
}
