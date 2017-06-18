package com.example.user.appfinal.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.appfinal.data.FoodContract.FoodEntry;
/**
 * Created by user on 2017/6/17.
 */

public class FoodDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "foodstoreDb.db";
    private static final int VERSION = 2;

    FoodDbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String CREATE_TABLE = "CREATE TABLE "  + FoodEntry.TABLE_NAME + " (" +
                FoodEntry._ID                + " INTEGER PRIMARY KEY, " +
                FoodEntry.COLUMN_FOOD_NAME + " TEXT NOT NULL, " +
                FoodEntry.COLUMN_BUY_TIME + " TEXT NOT NULL, " +
                FoodEntry.COLUMN_ALERT_TIME + " TEXT NOT NULL, " +
                FoodEntry.COLUMN_REMARK    + " TEXT"+ ");";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + FoodEntry.TABLE_NAME);
        onCreate(db);
    }
}
