package com.example.chartapplication.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
 
public class SQLDAOImpl {
    private DBHelper mHelpter = null;
 
    public SQLDAOImpl(Context context) {
        mHelpter = new DBHelper(context);
    }



    public void insertQuestion(QuestionInfo questionInfo) {
        SQLiteDatabase db = mHelpter.getWritableDatabase();
        db.execSQL("insert into question_info( question ) values( ? )",
                new Object[]{  questionInfo.getQuestion() });
        db.close();
    }

    public List<QuestionInfo> getQuestions() {
        SQLiteDatabase db = mHelpter.getWritableDatabase();
        List<QuestionInfo> list = new ArrayList<QuestionInfo>();
        Cursor c = db.rawQuery("select * from question_info", new String[]{});
        while (c.moveToNext()) {
        	QuestionInfo question = new QuestionInfo();
        	question.setQuestion(c.getString(c.getColumnIndex("question")));
            list.add(question);
        }
        c.close();
        db.close();
 
        return list;
    }

    public void updateQuestions(String oldValue,String newValue) {

        SQLiteDatabase db = mHelpter.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("question", newValue);
        int count = db
                .update("question_info", values,  "question = ?", new String[]{oldValue});
        db.close();

    }


    public void deleteByValue(String oldValue ) {

        SQLiteDatabase db = mHelpter.getWritableDatabase();

        int count = db.delete("question_info",  "question = ?", new String[]{oldValue});

        db.close();

    }


    public void cleanData() {
        SQLiteDatabase db = mHelpter.getWritableDatabase();
        List<QuestionInfo> list = new ArrayList<QuestionInfo>();
        db.execSQL("delete from question_info");
        db.close();
    }

}