package edu.css.amanda.cis3334_finalproject_nicholsamanda;

/**
 * Created by Amanda Nichols
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class NewVisionActivity extends AppCompatActivity {

    /**
     * Declare final variable for days in a year
     * does not consider a leap year
     */
    private static final int DAYS_IN_YEAR = 365;

    /**
     * Declare public static ints
     * They can be retrieved by other activities or methods
     */
    public static int countdown;
    public static int wDayOfYear;
    public static int cDayOfYear;

    /**
     * Declare public static strings
     * They can be retrieved by other activities or methods
     */
    public static String newWeddingDate;
    public static String newWeddingColors;
    public static String newWeddingSetting;
    public static String newWeddingBudget;
    public static String month;
    public static String day;
    public static String year;
    public static String color;
    public static String setting;
    public static String budget;

    /**
     * Declare EditText and Button variables
     */
    private EditText etNewMonth;
    private EditText etNewDay;
    private EditText etNewYear;
    private EditText etNewColor;
    private EditText etNewSetting;
    private EditText etNewBudget;
    private Button buttonNewVision;

    /**
     * Create the activity
     * User will be able to input their wedding Vision preferences
     * After they create an account
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_vision);

        /**
         * Initialize variables
         * Link variables to buttons and edit text fields
         */
        etNewMonth = (EditText) findViewById(R.id.editTextNewMonth);
        etNewDay = (EditText) findViewById(R.id.editTextNewDay);
        etNewYear = (EditText) findViewById(R.id.editTextNewYear);
        etNewColor = (EditText) findViewById(R.id.editTextNewColor);
        etNewSetting = (EditText) findViewById(R.id.editTextNewSetting);
        etNewBudget = (EditText) findViewById(R.id.editTextNewBudget);

        /**
         * Set up the Generate Wedding Vision button
         * User will enter input
         * When button is clicked, get user input and send to MainActivity
         */
        buttonNewVision = (Button) findViewById(R.id.buttonNewVision);
        buttonNewVision.setOnClickListener(new View.OnClickListener()  {
            public void onClick(View v) {
                startActivity(new Intent(NewVisionActivity.this, MainActivity.class));

                // initialize and parse to use in WeddingCalculator()
                int monthNum = Integer.parseInt(etNewMonth.getText().toString());
                int dayNum = Integer.parseInt(etNewDay.getText().toString());
                int yearNum = Integer.parseInt(etNewYear.getText().toString());

                // try to set countdown using WeddingCalculator() to determine countdown of days
                // catch if error occurs
                try {
                    countdown = WeddingCalculator(yearNum, monthNum, dayNum);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                // set up switch statement to convert numbered month to name of month
                // based on user input
                switch (monthNum) {
                    case 1: month = "January";
                        break;
                    case 2: month = "February";
                        break;
                    case 3: month = "March";
                        break;
                    case 4: month = "April";
                        break;
                    case 5: month = "May";
                        break;
                    case 6: month = "June";
                        break;
                    case 7: month = "July";
                        break;
                    case 8: month = "August";
                        break;
                    case 9: month = "September";
                        break;
                    case 10: month = "October";
                        break;
                    case 11: month = "November";
                        break;
                    case 12: month = "December";
                        break;
                    default: month = "Invalid Month";
                        break;
                }
                // build the wedding date String
                day = etNewDay.getText().toString();
                year = etNewYear.getText().toString();
                newWeddingDate = month + " " + day + ", " + year;

                // set the wedding color String
                color = etNewColor.getText().toString();
                newWeddingColors = color;

                // set the wedding setting String
                setting = etNewSetting.getText().toString();
                newWeddingSetting = setting;

                // set the wedding budget String
                budget = etNewBudget.getText().toString();
                newWeddingBudget = budget;
            }
        });
    }

    /**
     * A method to calculate how many days until the wedding
     * Uses Calendar
     * Throws a ParseException if error occurs
     * @param y
     * @param m
     * @param d
     * @return daysUntilWedding
     * @throws ParseException
     */
    public static int WeddingCalculator(int y, int m, int d) throws ParseException {
    Calendar calendar = Calendar.getInstance();         // create calendar object
    GregorianCalendar gc = new GregorianCalendar();     // create gregorian calendar object

    int daysUntilWedding;

    // get the current day of the year
    cDayOfYear = calendar.get(Calendar.DAY_OF_YEAR);

    gc.set(Calendar.DAY_OF_MONTH, d);
    gc.set(Calendar.MONTH, m);
    gc.set(Calendar.YEAR, y);
    // get the wedding day of the year
    wDayOfYear = gc.get(Calendar.DAY_OF_YEAR)-30;       // subtract 30 days from wedding day of the year

    // if the current day of the year is less than or equal to the wedding day of the year
    if (cDayOfYear <= wDayOfYear) {
        daysUntilWedding = (wDayOfYear - cDayOfYear);   // wedding day - current day
        return daysUntilWedding;
    }
    else {
        daysUntilWedding = ((DAYS_IN_YEAR - cDayOfYear) + wDayOfYear); // 365 - current day + wedding day
        return daysUntilWedding;
    }
}

}
