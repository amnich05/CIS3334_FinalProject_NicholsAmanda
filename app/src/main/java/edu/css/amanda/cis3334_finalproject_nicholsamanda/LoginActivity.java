package edu.css.amanda.cis3334_finalproject_nicholsamanda;

/**
 * Created by Amanda Nichols
 */

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    /**
     * Declare EditText, Button, and FirebaseAuth variables
     */
    private EditText etEmail;
    private EditText etPassword;
    private Button buttonLogin;
    private FirebaseAuth mAuth;

    /**
     * Create the activity
     * User will be able to enter logon information
     * Validate logon and send user to MainActivity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // initialize variables
        etEmail = (EditText) findViewById(R.id.editTextEmail);
        etPassword = (EditText) findViewById(R.id.editTextPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        mAuth = FirebaseAuth.getInstance(); //declare object for Firebase

        /**
         * Set up the Log In button
         * User will enter email and password
         * When button is clicked, get user input
         */
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // when Normal Login button is clicked, get user email and password
                signIn(etEmail.getText().toString(), etPassword.getText().toString());
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }

        /**
         * Verify user sign in with Firebase
         * @param email
         * @param password
        */
        private void signIn (String email, String password){
            //sign in the recurrent user with email and password previously created.
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() { //add to listener
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) { //when failed
                        Toast.makeText(LoginActivity.this, "SignIn--Authentication failed.", Toast.LENGTH_LONG).show();
                    } else {
                        //return to MainActivity is login works
                        finish();
                    }
                }
            });
        }

}
