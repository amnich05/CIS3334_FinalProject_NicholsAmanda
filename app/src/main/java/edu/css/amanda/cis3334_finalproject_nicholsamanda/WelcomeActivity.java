package edu.css.amanda.cis3334_finalproject_nicholsamanda;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class WelcomeActivity extends AppCompatActivity {

    private Button buttonWelcomeLogin;
    private Button buttonWelcomeCreateAccount;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mAuthListener = new FirebaseAuth.AuthStateListener() { //initialized mAuthListener
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //track the user when they sign in or out using the firebaseAuth
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("CSS3334","onAuthStateChanged - User IS signed in");
                    Intent signInIntent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(signInIntent);
                }
            }
        };

        buttonWelcomeLogin = (Button) findViewById(R.id.buttonWelcomeLogin);
        buttonWelcomeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
            }
        });

        /**
         * Set up the Create Account button
         * User will enter email and password
         * When button is clicked, get user input
         */
        buttonWelcomeCreateAccount = (Button) findViewById(R.id.buttonWelcomeCreateAccount);
        buttonWelcomeCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this, CreateAccountActivity.class));
            }
        });

    }



}
