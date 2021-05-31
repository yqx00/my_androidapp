package com.example.chartapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import com.example.chartapplication.sqlite.SQLDAOImpl;
import com.example.chartapplication.sqlite.QuestionInfo;

import java.util.List;

public class ShowActivity extends AppCompatActivity {


    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        listView = (ListView) findViewById(R.id.listView);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                initUI();
            }
        }, 100);

    }

    public void initUI() {

        SQLDAOImpl questionDAO = new SQLDAOImpl(this);

        List<QuestionInfo> lQues = questionDAO.getQuestions();

        if(lQues==null )
            return;




        final String[] data=new String[lQues.size()];

         for(int n=0;n<lQues.size();n++)
         {
             data[n]=lQues.get(n).getQuestion();
         }
        ArrayAdapter<String>  adapter=new ArrayAdapter<String>(ShowActivity.this,android.R.layout.simple_list_item_1,data);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                toDo(i,data[i]);
            }
        });

    }

    private void toDo(int i, String datum) {

        Intent intent = new Intent( this, EditActivity.class);
        intent.putExtra("data",datum);
        startActivityForResult(intent, 1);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            initUI();
        }
    }


}
