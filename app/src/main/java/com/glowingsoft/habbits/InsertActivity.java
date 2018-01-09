package com.glowingsoft.habbits;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.glowingsoft.habbits.Model.HabbitModelClass;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class InsertActivity extends Activity {

    EditText habbitNameEt;
    TextView submit;
    String radioCheck = "";
    Calendar myCalendar;
    TextView datePick;
    String dateSet = "";
    DatePickerDialog.OnDateSetListener date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);


        submit = (TextView) findViewById(R.id.submit);
        habbitNameEt = (EditText) findViewById(R.id.habbitNameEt);

// radio button koda (yes ali no)
        final RadioGroup radioGroup = (RadioGroup) findViewById(R.id.RGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // find which radio button is selected
                if (checkedId == R.id.Yes) {

                    radioCheck = "Yes";

                } else if (checkedId == R.id.No) {

                    radioCheck = "No";

                }
            }

        });

// koledar date picker koda
        myCalendar = Calendar.getInstance();

        datePick = (TextView) findViewById(R.id.datePicker);
        date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);


                String myFormat = "dd-MM-yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                dateSet = sdf.format(myCalendar.getTime());

                datePick.setText(dateSet);

            }

        };


        datePick.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);


                // TODO Auto-generated method stub
                new DatePickerDialog(InsertActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

// sumbit button click listner
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String habbit = habbitNameEt.getText().toString();

// validacije za sumbit gumb
                if (habbit.equals("")) {
                    Toast.makeText(InsertActivity.this, "Enter Habbit", Toast.LENGTH_SHORT).show();
                } else if (dateSet.equals("")) {
                    Toast.makeText(InsertActivity.this, "Please Select Date", Toast.LENGTH_SHORT).show();
                } else if (radioGroup.equals("")) {
                    Toast.makeText(InsertActivity.this, "Please select the habbit is done or not", Toast.LENGTH_SHORT).show();
                } else {

                    // submit data in the model class
                    HabbitModelClass modelClass = new HabbitModelClass();
                    modelClass.setDate(dateSet);
                    modelClass.setDone(radioCheck);
                    modelClass.setHabbitName(habbit);

                    // adding the single habbit in the database
                    DatabaseHandler db = new DatabaseHandler(getApplicationContext());
                    db.addHabbit(modelClass);
                    Toast.makeText(InsertActivity.this, "Successfully added!", Toast.LENGTH_SHORT).show();
                    finish();
                    Intent i = new Intent(InsertActivity.this, ShowHabbitsActivity.class);
                    startActivity(i);

                }


            }
        });


    }
}
