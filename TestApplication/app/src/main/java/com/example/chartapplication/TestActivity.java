package com.example.chartapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chartapplication.sqlite.SQLDAOImpl;
import com.example.chartapplication.sqlite.QuestionInfo;


public class TestActivity extends AppCompatActivity implements View.OnClickListener {

    EditText tvShow;
    Button tvShowValue3;
    SQLDAOImpl aqlLite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        init();
    }

    private void init() {
        initUI();
        aqlLite =new SQLDAOImpl(this);

    }

    private void initUI() {
        tvShow = findViewById(R.id.etInput);
        tvShowValue3 = findViewById(R.id.tvShowValue3);
        tvShowValue3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String strInput= tvShow.getText().toString();
                if(strInput==null||strInput.length()==0)
                {
                    Toast.makeText(TestActivity.this,"请输入",Toast.LENGTH_SHORT).show();
                    return;
                }
                onClickValueResult(strInput);
            }
        });
    }


    private void onClickValueResult(String question ) {
        QuestionInfo questionInfo=new QuestionInfo( question );
        aqlLite.insertQuestion(questionInfo);
        this.finish();
        //
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    public void onClick(View view) {

    }
}
