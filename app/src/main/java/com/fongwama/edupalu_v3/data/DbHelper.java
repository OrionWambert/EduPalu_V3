package com.fongwama.edupalu_v3.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fongwama.edupalu_v3.contracts.PlacePharmaContract;

public class DbHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "edupalu.db";
    public static final int DB_VERSION = 2;

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_PLACE_PHARMA = "CREATE TABLE " + PlacePharmaContract.PlacePharamEntry.TABLE_NAME + " (" +
                PlacePharmaContract.PlacePharamEntry._ID + " INTEGER PRIMARY KEY," + PlacePharmaContract.PlacePharamEntry.PLACE_NAME + " TEXT," +
                PlacePharmaContract.PlacePharamEntry.PLACE_ADDRESS + " TEXT," + PlacePharmaContract.PlacePharamEntry.PLACE_CITY + " TEXT," +
                PlacePharmaContract.PlacePharamEntry.PLACE_LAT + " REAL," + PlacePharmaContract.PlacePharamEntry.PLACE_LON + " REAL," +
                PlacePharmaContract.PlacePharamEntry.PLACE_TEL_1 + " TEXT," + PlacePharmaContract.PlacePharamEntry.PLACE_TEl_2 + " TEXT);";
        sqLiteDatabase.execSQL(SQL_PLACE_PHARMA);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+ PlacePharmaContract.PlacePharamEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}