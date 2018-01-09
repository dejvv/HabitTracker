package com.glowingsoft.habbits.Model;

/**
 * Created by Hacked on 1/9/2018.
 */

public class HabbitModelClass {



    int id;
    String habbitName,date,done;


    public HabbitModelClass() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHabbitName() {
        return habbitName;
    }

    public void setHabbitName(String habbitName) {
        this.habbitName = habbitName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }
}
