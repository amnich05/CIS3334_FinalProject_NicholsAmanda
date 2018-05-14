package edu.css.amanda.cis3334_finalproject_nicholsamanda;

/**
 * Created by Amanda Nichols
 * Date: April 29, 2018
 * Subject: CIS3334 Mobile Device Programming
 * Final Project
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * Declare TextView and FirebaseAuth variables
     */
    private TextView tvWeddingDate;
    private TextView tvCountdown;
    public TextView tvVisionColors;
    private TextView tvVisionSetting;
    private TextView tvVisionBudget;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    /**
     * Create the activity
     * This is the main activity shown after the user logs in
     * or after the user creates an account and completes NewVisionActivity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize and set up the toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // initialize and set up the navigation drawer
        // the navigation drawer will toggle opened and closed
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // initialize and set up the navigation view
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        /**
         * Initialize variables
         * Set text to the user input gathered from NewVisionActivity
         */
        tvWeddingDate = (TextView) findViewById(R.id.textViewWeddingDate);
        tvWeddingDate.setText(NewVisionActivity.newWeddingDate);

        tvCountdown = (TextView) findViewById(R.id.textViewCountdown);
        int cd = NewVisionActivity.countdown;
        tvCountdown.setText(Integer.toString(cd));


        tvVisionColors = (TextView) findViewById(R.id.textViewVisionColors);
        tvVisionColors.setText(NewVisionActivity.newWeddingColors);

        tvVisionSetting = (TextView) findViewById(R.id.textViewVisionSetting);
        tvVisionSetting.setText(NewVisionActivity.newWeddingSetting);

        tvVisionBudget = (TextView) findViewById(R.id.textViewVisionBudget);
        tvVisionBudget.setText(NewVisionActivity.newWeddingBudget);

        mAuth = FirebaseAuth.getInstance(); // start Firebase
        mAuthListener = new FirebaseAuth.AuthStateListener() { //initialized mAuthListener
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                //track the user when they sign in or out using the firebaseAuth
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // User is signed out
                    Log.d("CSS3334","onAuthStateChanged - User NOT is signed in");
                    Intent signInIntent = new Intent(getBaseContext(), LoginActivity.class);
                    startActivity(signInIntent);
                }
            }
        };
    }

    /**
     * When the application starts up,
     * check if user is signed in or signed out
     */
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth.addAuthStateListener(mAuthListener);
    }

    /**
     * When the application closes,
     * stop Firebase mAuthListener
     */
    @Override
    public void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }

    /**
     * What to do with the navigation drawer if the back button is pressed
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) { // if the drawer is open, close the drawer
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed(); // go back
        }
    }

    /**
     * Creates and inflates the options menu bar
     * @param menu
     * @return true
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Handles what to do once an item in the menu bar is selected or clicked
     * @param item
     * @return true
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Sign out the user
     */
    private void signOut () {
        // sign user out of database and application
        mAuth.signOut();
    }

    /**
     * Handles the items in the Navigation Drawer
     * and the action they perform when selected or clicked
     * @param item
     * @return true
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the home action, go to MainActivity
            startActivity(new Intent(MainActivity.this, MainActivity.class));
        } else if (id == R.id.nav_checklist) {
            // Handle the checklist action, not in use
        } else if (id == R.id.nav_guestList) {
            // Handle the guestList action, not in use
        } else if (id == R.id.nav_vision) {
            // Handle the vision action, go to VisionActivity
            startActivity(new Intent(MainActivity.this, VisionActivity.class));
        } else if (id == R.id.nav_settings) {
            // Handle the settings action, not in use
        } else if (id == R.id.nav_sign_out) {
            // sign the user out of the application, go to WelcomeActivity
            signOut();
            startActivity(new Intent(MainActivity.this, WelcomeActivity.class));
        }
        // close the drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
