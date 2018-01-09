package com.glowingsoft.habbits;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.glowingsoft.habbits.Model.HabbitModelClass;

import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {
    TextView inserthabbit;
    TextView ShowHabbits;

    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        db = new DatabaseHandler(this);

        inserthabbit = (TextView) findViewById(R.id.inserthabbit);
        inserthabbit.setOnClickListener(this);

        ShowHabbits = (TextView) findViewById(R.id.ShowHabbits);
        ShowHabbits.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.inserthabbit:

                // go to insert activity
                Intent i = new Intent(MainActivity.this, InsertActivity.class);
                startActivity(i);

                break;
            case R.id.ShowHabbits:

                // čekira če obstajajo habiti oz ne
                List<HabbitModelClass> DataLsit = db.getAllHabbits();
                if (DataLsit.size() > 0) {
                    Intent show = new Intent(MainActivity.this, ShowHabbitsActivity.class);
                    startActivity(show);
                } else {
                    Toast.makeText(this, "No habbit available, Please add habbit", Toast.LENGTH_SHORT).show();
                }


                break;


        }
    }

}
