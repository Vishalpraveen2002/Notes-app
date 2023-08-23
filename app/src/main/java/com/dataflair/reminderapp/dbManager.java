package com.dataflair.reminderapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbManager extends SQLiteOpenHelper {
    private static String dbname = "reminder";                                                      //Table  name to store reminders in sqllite
    public static final String COL_1 = "id";
    public dbManager(@Nullable Context context) {
        super(context, dbname, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {                                           //sql query to insert data in sqllite
        String query = "create table tbl_reminder(title text primary key,date text,time text)";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String query = "DROP TABLE IF EXISTS tbl_reminder";                                         //sql query to check table with the same name or not
        sqLiteDatabase.execSQL(query);                                                              //executes the sql command
        onCreate(sqLiteDatabase);

    }

    public String addreminder(String title, String date, String time) {
        SQLiteDatabase database = this.getReadableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("title", title);                                                          //Inserts  data into sqllite database
        contentValues.put("date", date);
        contentValues.put("time", time);

        float result = database.insert("tbl_reminder", null, contentValues);    //returns -1 if data successfully inserts into database

        if (result == -1) {
            return "Failed";
        } else {
            return "Successfully inserted";
        }

    }

    public Cursor readallreminders() {
        SQLiteDatabase database = this.getWritableDatabase();
        String query = "select * from tbl_reminder order by id desc";                               //Sql query to  retrieve  data from the database
        Cursor cursor = database.rawQuery(query, null);
        return cursor;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("tbl_reminder", "title = ?",new String[] {id});
    }

//    public void delete(long _id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete("tbl_reminder", dbManager.id + "=" + _id, null);
//    }
}
