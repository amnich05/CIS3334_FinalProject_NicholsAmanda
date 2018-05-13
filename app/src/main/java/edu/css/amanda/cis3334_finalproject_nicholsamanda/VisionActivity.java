package edu.css.amanda.cis3334_finalproject_nicholsamanda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.ParseException;

public class VisionActivity extends AppCompatActivity {

    public static int countdown;

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

    private EditText etMonth;
    private EditText etDay;
    private EditText etYear;
    private EditText etColor;
    private EditText etSetting;
    private EditText etBudget;
    private Button buttonVision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vision);

        //initialize variables
        etMonth = (EditText) findViewById(R.id.editTextMonth);
        etMonth.setText(NewVisionActivity.month, TextView.BufferType.EDITABLE);

        etDay = (EditText) findViewById(R.id.editTextDay);
        etDay.setText(NewVisionActivity.day, TextView.BufferType.EDITABLE);

        etYear = (EditText) findViewById(R.id.editTextYear);
        etYear.setText(NewVisionActivity.year, TextView.BufferType.EDITABLE);

        etColor = (EditText) findViewById(R.id.editTextColor);
        etColor.setText(NewVisionActivity.newWeddingColors, TextView.BufferType.EDITABLE);

        etSetting = (EditText) findViewById(R.id.editTextSetting);
        etSetting.setText(NewVisionActivity.newWeddingSetting, TextView.BufferType.EDITABLE);

        etBudget = (EditText) findViewById(R.id.editTextBudget);
        etBudget.setText(NewVisionActivity.newWeddingBudget, TextView.BufferType.EDITABLE);


        buttonVision = (Button) findViewById(R.id.buttonVision);
        buttonVision.setOnClickListener(new View.OnClickListener()  {
            public void onClick(View v) {
                startActivity(new Intent(VisionActivity.this, MainActivity.class));
    }
        });
    }
}