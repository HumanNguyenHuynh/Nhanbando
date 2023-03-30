package com.example.bando;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "mydatabase.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_BANDO = "bando";
    public static final String TABLE_LOGIN = "logintable";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";

    public static final String CREATE_TABLE_BANDO = "CREATE TABLE IF NOT EXISTS " + TABLE_BANDO + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY,"
            + "name TEXT,"
            + "address TEXT,"
            + "phone TEXT"
            + ")";
    public static final String CREATE_TABLE_LOGIN = "CREATE TABLE IF NOT EXISTS " + TABLE_LOGIN + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY,"
            + COLUMN_USERNAME + " TEXT,"
            + COLUMN_PASSWORD + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_BANDO);
        db.execSQL(CREATE_TABLE_LOGIN);
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, "nhan");
        values.put(COLUMN_PASSWORD, "nhan");
        db.insert(TABLE_LOGIN, null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BANDO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOGIN);
        onCreate(db);
    }


}

