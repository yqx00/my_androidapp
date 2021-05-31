package com.example.db.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.db.model.Food;
import com.example.db.model.User;
import com.example.db.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE = "CREATE TABLE " + Util.TABLE_NAME + "( " + Util.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + Util.USERNAME + " TEXT, " + Util.PASSWORD + " TEXT ); ";

        //格式问题
//        String CREATE_USER_TABLE = "insert into users values(111,111)";
        db.execSQL(CREATE_USER_TABLE);

        String CREATE_FOOD_TABLE = "CREATE TABLE " + Util.TABLE_NAME2 + "( " +Util.TITLE + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Util.IMAGE + " TEXT, " + Util.DETAIL + " TEXT ); ";

        db.execSQL(CREATE_FOOD_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String DROP_USER_TABLE = "DROP TABLE IF EXISTS";
        db.execSQL(DROP_USER_TABLE, new String[]{Util.TABLE_NAME});

        onCreate(db);


    }

    public long insertUser(User user) {
        SQLiteDatabase db2 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.USERNAME, user.getUsername());
        contentValues.put(Util.PASSWORD, user.getPassword());
        long newRowId = db2.insert(Util.TABLE_NAME, null, contentValues);
        db2.close();
        return newRowId;
    }

    public long insertFood(Food food){
        SQLiteDatabase db2 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Util.TITLE,food.getTitle());
        contentValues.put(Util.IMAGE,food.getImage_url());
        contentValues.put(Util.DETAIL,food.getDetail());

        long newRowId = db2.insert(Util.TABLE_NAME2,null,contentValues);
        db2.close();
        return newRowId;
    }

    public boolean fetchUser(String username, String password) {
        SQLiteDatabase db2 = this.getReadableDatabase();
        Cursor cursor = db2.query(Util.TABLE_NAME, new String[]{Util.USER_ID},Util.USERNAME + "=? and " + Util.PASSWORD + "=?",
                new String[] {username, password},null,null,null);

        //"=? and "少了一个空格

        int numberOfRows = cursor.getCount();
        db2.close();

        if(numberOfRows > 0){
            return  true;
        }else{
            return false;
        }
    }

    public List<Food> fetchAllFood(){
        List<Food> FoodList = new ArrayList<>();
        SQLiteDatabase mdb = this.getReadableDatabase();

        String selectUser = "SELECT * FROM " + Util.TABLE_NAME2;
        Cursor cursor = mdb.rawQuery(selectUser, null);

        if(cursor.moveToFirst()){
                do {
                    Food food = new Food(null, null, null);
                    food.setTitle(cursor.getString(0));
                    food.setImage_url(cursor.getString(1));
                    food.setDetail(cursor.getString(2));

                    FoodList.add(food);
                } while (cursor.moveToNext());
        }
        return FoodList;
    }
}


