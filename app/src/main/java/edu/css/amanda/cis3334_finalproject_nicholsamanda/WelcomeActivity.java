package edu.css.amanda.cis3334_finalproject_nicholsamanda;

/**
 * Created by Amanda Nichols
 */

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

    /**
     * Declare Button and FirebaseAuth variables
     */
    private Button buttonWelcomeLogin;
    private Button buttonWelcomeCreateAccount;
    private FirebaseAuth.AuthStateListener mAuthListener;


    /**
     * Create the activity
     * User will choose to either log in with an existing user account
     * Or create a new user account
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        mAuthListener = new FirebaseAuth.AuthStateListener() { //initialized mAuthListener
            /**
             * Start up Firebase and check if user is signed in already
             * @param firebaseAuth
             */
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

        /**
         * Set up the Log In button
         * User will enter email and password
         * When button is clicked, sign user in
         * Go to MainActivity
         */
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
         * Go to CreateAccountActivity
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
