package com.example.yatee.midterm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseOpenHelper extends SQLiteOpenHelper {

    static final String DB_NAME="myItems.db";
    static final int DB_VERSION=12;

    @Override
    public void onCreate(SQLiteDatabase db) {
        ItemTable.onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        ItemTable.onUpgrade(db,oldVersion,newVersion);
    }

    public DatabaseOpenHelper(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }
}
