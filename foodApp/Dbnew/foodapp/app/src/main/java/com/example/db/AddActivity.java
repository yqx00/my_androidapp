package com.example.db;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.db.model.Food;

import com.example.db.data.DatabaseHelper;
//import com.example.db.model.YYY;

public class AddActivity extends AppCompatActivity {

    DatabaseHelper db2;
    public ImageView iv;
    public ImageView iv1;
    public Uri uri0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        db2 = new DatabaseHelper(this);
        Intent intent = getIntent();

        ImageButton btn_pic = findViewById(R.id.btn_pic);
//        EditText titleEditText = findViewById(R.id.titleEditText);
//        EditText detailEditText = findViewById(R.id.detailEditText);


        iv = (ImageView)findViewById(R.id.imageView2);


    }

    public void alertClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setTitle("Ask for permission");
        builder.setMessage("Add or not?");
        builder.setCancelable(true);

        builder.setPositiveButton("ALLOW", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 1);
            }
        });

        builder.setNegativeButton("DENY", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }

        });

        builder.create().show();


    }

    public void savePicClick(View view){
        EditText titleEditText = findViewById(R.id.titleEditText);
        EditText detailEditText = findViewById(R.id.detailEditText);

//        Integer image =  .getImageAlpha();
//        String image_url = uri0;
        String title = titleEditText.getText().toString();
        String detail = detailEditText.getText().toString();
//        String pick = pickEditText.getText().toString();
//        String quatity = quantityEditText.getText().toString();
//        String location = locationEditText.getText().toString();


        long result = db2.insertFood(new Food(title,uri0.toString(),detail));
        if(result > 0){
            Toast.makeText(AddActivity.this, "Add successfully!", Toast.LENGTH_SHORT).show();
            Intent savePicIntent = new Intent(AddActivity.this, HomeActivity.class);
            startActivity(savePicIntent);
        }else{
            Toast.makeText(AddActivity.this, "Add error!", Toast.LENGTH_SHORT).show();
        }

//        Intent savePicIntent = new Intent(AddActivity.this,HomeActivity.class);
//        startActivity(savePicIntent);



    }

    public void onActivityResult(int arg0, int arg1, Intent data) {

        if(data!=null)
        {
            Uri uri = data.getData();
            uri0 = data.getData();
//            iv1.setImageURI(yyy);
            System.out.println("abcdef:" + uri0);
            iv.setImageURI(uri);

        } else {
            System.out.println("meiypu");
        }
        super.onActivityResult(arg0, arg1, data);
    }




}