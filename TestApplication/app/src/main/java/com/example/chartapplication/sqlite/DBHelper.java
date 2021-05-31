package com.example.chartapplication.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "mysqlite1.db";
    private static final int version = 1;

    private static final String SQL_CREATE = "create table question_info(_id integer primary key autoincrement,questionNO integer,question text)";

    private static final String SQL_DROP = "drop table if exists question_info";
 
    public DBHelper(Context context) {
        super(context, DB_NAME, null, version);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DROP);
        db.execSQL(SQL_CREATE);
    }
}