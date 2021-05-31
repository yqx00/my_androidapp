package com.example.chartapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickTest(View view)
    {
        Intent intent =new Intent(this,TestActivity.class);
        startActivity(intent);
    }

    public void onClickResult(View view)
    {
        Intent intent =new Intent(this, ShowActivity.class);
        startActivity(intent);
    }
}