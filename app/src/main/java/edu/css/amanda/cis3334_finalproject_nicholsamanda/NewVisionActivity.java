package edu.css.amanda.cis3334_finalproject_nicholsamanda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class NewVisionActivity extends AppCompatActivity {

    private static final int DAYS_IN_YEAR = 365;

    public static int countdown;
    public static int wDayOfYear;
    public static int cDayOfYear;

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

    private EditText etNewMonth;
    private EditText etNewDay;
    private EditText etNewYear;
    private EditText etNewColor;
    private EditText etNewSetting;
    private EditText etNewBudget;
    private Button buttonNewVision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_vision);

        //initialize variables
        etNewMonth = (EditText) findViewById(R.id.editTextNewMonth);

        etNewDay = (EditText) findViewById(R.id.editTextNewDay);
        //int day = Integer.parseInt(etNewDay.getText().toString());

        etNewYear = (EditText) findViewById(R.id.editTextNewYear);
        //int year = Integer.parseInt(etNewYear.getText().toString());

        etNewColor = (EditText) findViewById(R.id.editTextNewColor);

        etNewSetting = (EditText) findViewById(R.id.editTextNewSetting);

        etNewBudget = (EditText) findViewById(R.id.editTextNewBudget);


        buttonNewVision = (Button) findViewById(R.id.buttonNewVision);
        buttonNewVision.setOnClickListener(new View.OnClickListener()  {
            public void onClick(View v) {
                startActivity(new Intent(NewVisionActivity.this, MainActivity.class));


                int monthNum = Integer.parseInt(etNewMonth.getText().toString());
                int dayNum = Integer.parseInt(etNewDay.getText().toString());
                int yearNum = Integer.parseInt(etNewYear.getText().toString());

                try {
                    countdown = WeddingCalculator(yearNum, monthNum, dayNum);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

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

                //month = etNewMonth.getText().toString();
                day = etNewDay.getText().toString();
                year = etNewYear.getText().toString();
                newWeddingDate = month + " " + day + ", " + year;

                color = etNewColor.getText().toString();
                newWeddingColors = color;

                setting = etNewSetting.getText().toString();
                newWeddingSetting = setting;

                budget = etNewBudget.getText().toString();
                newWeddingBudget = budget;




            }
        });

        //countdown = WeddingCalculator(year, month, day);
    }
/*
    public static int WeddingCalculator(int y, int m, int d) {
        Calendar today = Calendar .now();
        int wDayOfYear, cDayOfYear;
        int daysUntilWedding;

        LocalDate birthday = LocalDate.of(y, m, d);
        wDayOfYear = birthday.getDayOfYear();
        cDayOfYear = today.getDayOfYear();

        if (cDayOfYear <= wDayOfYear)
        {
            daysUntilWedding = (wDayOfYear - cDayOfYear);
            return daysUntilWedding;
        }
        else
        {
            daysUntilWedding = ((DAYS_IN_YEAR - cDayOfYear) + wDayOfYear);
            return daysUntilWedding;
        }
    }
*/

public static int WeddingCalculator(int y, int m, int d) throws ParseException {
    Calendar calendar = Calendar.getInstance();
    GregorianCalendar gc = new GregorianCalendar();

    //int wDayOfYear, cDayOfYear;
    int daysUntilWedding;

    cDayOfYear = calendar.get(Calendar.DAY_OF_YEAR);

    gc.set(Calendar.DAY_OF_MONTH, d);
    gc.set(Calendar.MONTH, m);
    gc.set(Calendar.YEAR, y);
    wDayOfYear = gc.get(Calendar.DAY_OF_YEAR)-30;

    if (cDayOfYear <= wDayOfYear)
    {
        daysUntilWedding = (wDayOfYear - cDayOfYear);
        return daysUntilWedding;
    }
    else
    {
        daysUntilWedding = ((DAYS_IN_YEAR - cDayOfYear) + wDayOfYear);
        return daysUntilWedding;
    }

    //String input = m + "," + d + "," + y;
    //SimpleDateFormat format = new SimpleDateFormat("MM,dd,yyy");




    //SimpleDateFormat newWeddingDay = new SimpleDateFormat("dd");
    //SimpleDateFormat newWeddingMonth = new SimpleDateFormat("MM");
    //SimpleDateFormat newWeddingYear = new SimpleDateFormat("yyyy");


}

}
