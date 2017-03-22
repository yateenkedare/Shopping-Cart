package com.example.yatee.midterm;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class ItemTable {
    static final String TABLENAME="Items_Tabless";
    static final String COLUMN_ID="_id";
    static final String COLUMN_Title="title";
    static final String COLUMN_MSP="msp";
    static final String COLUMN_SP="sp";
    static final String COLUMN_URL="imageurl";
    static final String COLUMN_UID="uid";

    public static void onCreate(SQLiteDatabase db){
        StringBuilder sb=new StringBuilder();
        sb.append("CREATE TABLE "+TABLENAME+" (");
        sb.append(COLUMN_ID+" integer primary key autoincrement, ");
        sb.append(COLUMN_Title+" text not null, ");
        sb.append(COLUMN_MSP+" text not null, ");
        sb.append(COLUMN_SP+" text not null, ");
        sb.append(COLUMN_URL+" text not null, ");
        sb.append(COLUMN_UID+" text not null);");

        try {
            db.execSQL(sb.toString());
        }catch (SQLException ex){
            ex.printStackTrace();
        }

    }

    public static void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLENAME);
        ItemTable.onCreate(db);

    }
}
