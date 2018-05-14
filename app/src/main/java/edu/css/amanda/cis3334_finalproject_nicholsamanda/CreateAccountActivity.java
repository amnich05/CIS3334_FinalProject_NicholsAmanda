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

public class CreateAccountActivity extends AppCompatActivity {

    /**
     * Declare EditText, Button variables
     */
    private EditText etCreateEmail;
    private EditText etCreatePassword;
    private Button buttonCreateLogin;
    private FirebaseAuth mAuth;

    /**
     * Create the mobile application
     * User will be able to create an account
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        // initialize variables
        etCreateEmail = (EditText) findViewById(R.id.editTextCreateEmail);
        etCreatePassword = (EditText) findViewById(R.id.editTextCreatePassword);
        buttonCreateLogin = (Button) findViewById(R.id.buttonCreateLogin);
        mAuth = FirebaseAuth.getInstance(); //declare object for Firebase


        /**
         * Set up the Create Account button
         * User will enter email and password
         * When button is clicked, get user input
         */
        buttonCreateLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // when Create Account button is clicked, get user email and password
                createAccount(etCreateEmail.getText().toString(), etCreatePassword.getText().toString());
                startActivity(new Intent(CreateAccountActivity.this, NewVisionActivity.class));
            }
        });
    }

    /**
     * Create the user account
     * Store the email and password in Firebase
     * @param email
     * @param password
     */
    private void createAccount(String email, String password) {
        //create account for new users
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {  //update listener.
                if (!task.isSuccessful()) { //when failed
                    Toast.makeText(CreateAccountActivity.this, "createAccount--Authentication failed.",Toast.LENGTH_LONG).show();
                } else {
                    //return to MainActivity is login works
                    finish();
                }
            }
        });
    }
}
