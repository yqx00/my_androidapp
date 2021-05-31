package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Chronometer chronometer;
    private long pauseOffset;

    private boolean running;
    private SharedPreferences sharedPreferences;
    public String type = "";

    TextView spentText;
    EditText typeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);


        chronometer = findViewById(R.id.chronometer);
        chronometer.setFormat("00:%s");


//        chronometer.setText(FormatMiss(current));
        spentText = findViewById(R.id.spentText);
        typeText = findViewById(R.id.typeText);

//        btnStart = findViewById(R.id.btnStart);
        sharedPreferences = getSharedPreferences("com.example.timer", MODE_PRIVATE);
        String a = sharedPreferences.getString("sentence", "");
        spentText.setText("You spent " + a + " last time.");
    }

    public void startClick(View view) {
        if (!running) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
        }
    }

    public void pauseClick(View view) {
        if (running) {
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            running = false;
        }
    }

    public void stopClick(View view) {
        if (running) {
            type = typeText.getText().toString();

            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
//            chronometer.setBase(SystemClock.elapsedRealtime());

            String hour = chronometer.getText().toString().split(":")[0];
            String min = chronometer.getText().toString().split(":")[1];
            String sec = chronometer.getText().toString().split(":")[2];
            String sentence = hour + ":" + min + ":" + sec + " " +type;
            SharedPreferences sp = this.getPreferences(MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("sentence", sentence);
            editor.apply();

            spentText.setText("You spent " + sentence + " last time.");

            running = false;



        }

    }
}
