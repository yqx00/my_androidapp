package com.example.db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.db.data.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText nameEditText = findViewById(R.id.nameEditText);
//        nameEditText.setInputType(InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);

        EditText passwordEditText = findViewById(R.id.passwordEditText);

        Button btn_log = findViewById(R.id.btn_log);
        Button btn_sign = findViewById(R.id.btn_sign);

        db2 = new DatabaseHelper(this);
        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent loginIntent = new Intent(MainActivity.this,HomeActivity.class);
//                startActivity(loginIntent);

                boolean result = db2.fetchUser(nameEditText.getText().toString(), passwordEditText.getText().toString());
               System.out.println("login result:" + result);

                if(result == true)
                {
                    Toast.makeText(MainActivity.this,"Successfully logged in", Toast.LENGTH_SHORT).show();

                    Intent loginIntent = new Intent(MainActivity.this,HomeActivity.class);
                    startActivity(loginIntent);
//                    System.out.println(nameEditText);
//                    System.out.println(passwordEditText);

                }else{
                    Toast.makeText(MainActivity.this,"The user does not exist", Toast.LENGTH_SHORT).show();
                }


            }
        });

        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(MainActivity.this,SignupActivity.class);
                startActivity(signupIntent);

            }
        });
    }
}