package com.example.db;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.db.data.DatabaseHelper;
import com.example.db.model.User;

public class SignupActivity extends AppCompatActivity {

    DatabaseHelper db2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        EditText sNameEditText = findViewById(R.id.sNameEditText);

//        EditText emailEditText = findViewById(R.id.emailEditText);
//
//
//        EditText phoneEditText = findViewById(R.id.phoneEditText);
//
//
//        EditText addressEditText = findViewById(R.id.addressEditText);

        EditText sPasswordEditText = findViewById(R.id.sPasswordEditText);

        EditText confirmEditText = findViewById(R.id.confirmEditText);

        Button btn_save = findViewById(R.id.btn_save);

        db2 = new DatabaseHelper(this);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = sNameEditText.getText().toString();
                String password = sPasswordEditText.getText().toString();
                String confirm = confirmEditText.getText().toString();
//                String email = emailEditText.getText().toString();
//                String phone = phoneEditText.getText().toString();
//                String address = addressEditText.getText().toString();

                if(password.equals(confirm)){

                    long result = db2.insertUser(new User(1,username,password));

                    System.out.println(username);
                    System.out.println(password);
                    if(result > 0){
                        Toast.makeText(SignupActivity.this,"Successfully", Toast.LENGTH_SHORT).show();
                        Intent mainIntent = new Intent(SignupActivity.this,MainActivity.class);
                        startActivity(mainIntent);

                    }else{
                        Toast.makeText(SignupActivity.this,"Error", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(SignupActivity.this,"Two password do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}