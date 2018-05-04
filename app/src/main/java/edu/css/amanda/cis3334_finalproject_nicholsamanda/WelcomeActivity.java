package edu.css.amanda.cis3334_finalproject_nicholsamanda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    private Button buttonWelcomeLogin;
    private Button buttonWelcomeCreateAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

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
