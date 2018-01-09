package com.glowingsoft.habbits;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.glowingsoft.habbits.Model.HabbitModelClass;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hacked on 1/9/2018.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Habbits";

    // Contacts table name
    private static final String TABLE_CONTACTS = "MyHabbits";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DATE = "datee";
    private static final String KEY_DONE = "done";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT,"
                + KEY_DATE + " TEXT,"
                + KEY_DONE + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }



    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        // Create tables again
        onCreate(db);
    }

// adding a single habbit in the database
    public void addHabbit(HabbitModelClass contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getHabbitName());
        values.put(KEY_DATE, contact.getDate());
        values.put(KEY_DONE, contact.getDone());

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        db.close(); // Closing database connection
    }

    // update podatkovno bazo za toggle button
    public int updateHabbitWRTID(HabbitModelClass contact) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATE, contact.getDate());
        values.put(KEY_DONE, contact.getDone());
        values.put(KEY_NAME, contact.getHabbitName());


        // updating row
        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?", new String[]{String.valueOf(contact.getId())});
    }

// dobimo listo vseh habbitov
    public List<HabbitModelClass> getAllHabbits() {
        List<HabbitModelClass> contactList = new ArrayList<HabbitModelClass>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                HabbitModelClass contact = new HabbitModelClass();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setHabbitName(cursor.getString(1));
                contact.setDate(cursor.getString(2));
                contact.setDone(cursor.getString(3));

                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        // return contact list
        return contactList;
    }
}