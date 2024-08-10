package com.example.myrunbuddyapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Run_data.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table RunDetails(RunName TEXT primary key, RunDate TEXT, RunTime TEXT, RunDistance TEXT, RunBPM TEXT, RunPace TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("drop Table if exists RunDetails");
    }

    public Boolean insertRunData(String RunName, String RunDate, String RunTime, String RunDistance, String RunBPM, String RunPace){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("RunName", RunName);
        contentValues.put("RunDate", RunDate);
        contentValues.put("RunTime", RunTime);
        contentValues.put("RunDistance", RunDistance);
        contentValues.put("RunBPM", RunBPM);
        contentValues.put("RunPace", RunPace);

        long result = DB.insert("RunDetails", null, contentValues);
        if(result == -1){
            return false;
        }else {
            return true;
        }
    }

    public Boolean updateRunData(String RunName, String RunDate, String RunTime, String RunDistance, String RunBPM, String RunPace){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("RunName", RunName);
        contentValues.put("RunDate", RunDate);
        contentValues.put("RunTime", RunTime);
        contentValues.put("RunDistance", RunDistance);
        contentValues.put("RunBPM", RunBPM);
        contentValues.put("RunPace", RunPace);

        Cursor cursor = DB.rawQuery("Select * from RunDetails where RunName = ?", new String[]{RunName});
        if(cursor.getCount() > 0) {
            long result = DB.update("RunDetails", contentValues, "RunName=?", new String[]{RunName});
            if (result == -1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    public Boolean deleteRunData(String RunName){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from RunDetails where RunName = ?", new String[]{RunName});
        if(cursor.getCount() > 0) {
            long result = DB.delete("RunDetails", "RunName=?", new String[]{RunName});
            if (result == -1){
                return false;
            }else{
                return true;
            }
        }else{
            return false;
        }
    }

    public Cursor getRunData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from RunDetails", null);
        return cursor;
    }
}

