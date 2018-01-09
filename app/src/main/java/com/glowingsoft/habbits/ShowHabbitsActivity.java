package com.glowingsoft.habbits;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ListView;

import com.glowingsoft.habbits.Adapter.HabbitAdapter;
import com.glowingsoft.habbits.Model.HabbitModelClass;

import java.util.ArrayList;
import java.util.List;

public class ShowHabbitsActivity extends Activity {
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_habbits);


        listview = (ListView) findViewById(R.id.listview);


        DatabaseHandler db = new DatabaseHandler(this);

        //dobimo listo habitov
        List<HabbitModelClass> DataLsit = db.getAllHabbits();


        // iteriramo skozi podatkovno bazo, da bidim če obstajajo vrednosti
        for (int i = 0; i < DataLsit.size(); i++) {
            Log.d("HabbitName", DataLsit.get(i).getHabbitName());
            Log.d("HabbitID", DataLsit.get(i).getId() + "");
        }

// spremenimo adapter, da pokaže listo habitov v listview
        HabbitAdapter adapter = new HabbitAdapter(getApplicationContext(), DataLsit);
        listview.setAdapter(adapter);


    }
}
