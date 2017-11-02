package com.example.jahid.walky;

/**
 * Created by rubai on 11/1/2017.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "User";
    public static final String TABLE_NAME = "Usar_status";
    public static final String COL_1 = "ID";

    public static final String COL_2 = "steps";

    public int ID=0;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,steps INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String username,String status,String steps) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2,steps);

        ID++;
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select  from "+TABLE_NAME,null);
        return res;
    }


    public boolean updateData(String username,String status,String steps) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,username);
        contentValues.put(COL_2,status);
        contentValues.put(COL_2,steps);

       // db.update(TABLE_NAME, contentValues, "ID = ?",new String[] { id });
        return true;
    }

    public void updateSteps(int steps)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("UPDATE "+TABLE_NAME+" SET steps = '"+steps+"' WHERE username = "+ID+";", null);

    }


    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?",new String[] {id});
    }
}