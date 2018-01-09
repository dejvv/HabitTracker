package com.glowingsoft.habbits.Adapter;

import android.content.Context;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.glowingsoft.habbits.DatabaseHandler;
import com.glowingsoft.habbits.Model.HabbitModelClass;
import com.glowingsoft.habbits.R;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class HabbitAdapter extends BaseAdapter {

    public List<HabbitModelClass> DataArrayList;
    TextView name;
    TextView idNUmber, done, date;
    public LayoutInflater myinflator;
    Context context;
    Switch done_img;

    public HabbitAdapter(Context context, List<HabbitModelClass> DataArrayList) {
        this.context = context;
        this.DataArrayList = DataArrayList;
        myinflator = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


    }


    @Override
    public int getCount() {
        return DataArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return DataArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = myinflator.inflate(R.layout.row, null);


        name = (TextView) view.findViewById(R.id.name);
        date = (TextView) view.findViewById(R.id.date);
        done = (TextView) view.findViewById(R.id.done);
        idNUmber = (TextView) view.findViewById(R.id.idNUmber);

        name.setText("Habbit Name: " + DataArrayList.get(position).getHabbitName());
        date.setText("Habbit Date: " + DataArrayList.get(position).getDate());
        done.setText("Habbit Done: " + DataArrayList.get(position).getDone());
        done.setTag(position);

        // set število vrednosti habitov v listu
        idNUmber.setText(String.valueOf(DataArrayList.get(position).getId()));


        // toggle switch control
        done_img = (Switch) view.findViewById(R.id.done_img);
        done_img.setTag(position);

// toggle setter on first run
        if (DataArrayList.get(position).getDone().equals("Yes")) {
            done_img.setChecked(true);
        } else {
            done_img.setChecked(false);
        }

// toggle switch control implementation
        done_img.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int pos = (int) buttonView.getTag();


                // getting data at the clicked position
                int ID = DataArrayList.get(pos).getId();
                String Naame = DataArrayList.get(position).getHabbitName();
                String DATE = DataArrayList.get(position).getDate();
                String DOne = DataArrayList.get(position).getDone();


                if (DOne.equals("Yes")) {
                    // updating database according the the toggle buttons
                    DatabaseHandler db = new DatabaseHandler(context);
                    HabbitModelClass modelClass = new HabbitModelClass();
                    modelClass.setDate(DATE);
                    modelClass.setDone("No");
                    modelClass.setHabbitName(Naame);
                    modelClass.setId(ID);

                    db.updateHabbitWRTID(modelClass);


                } else {
                    // updating database according the the toggle buttons
                    DatabaseHandler db = new DatabaseHandler(context);
                    HabbitModelClass modelClass = new HabbitModelClass();
                    modelClass.setDate(DATE);
                    modelClass.setDone("Yes");
                    modelClass.setHabbitName(Naame);
                    modelClass.setId(ID);

                    db.updateHabbitWRTID(modelClass);


                }


            }
        });


        return view;
    }
}
