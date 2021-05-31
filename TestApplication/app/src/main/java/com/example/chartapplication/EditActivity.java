package com.example.chartapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.chartapplication.sqlite.SQLDAOImpl;


public class EditActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etInput;
    Button btnUpdate;
    Button btnDelete;
    SQLDAOImpl questionDAO;

    String strOldValue="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        init();
    }

    private void init() {
        initUI();

        strOldValue=getIntent().getStringExtra("data");
        etInput.setText(strOldValue);
        questionDAO=new SQLDAOImpl(this);

    }

    private void initUI() {
        etInput = findViewById(R.id.etInput);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strInput= etInput.getText().toString();

                if(strInput==null||strInput.length()==0)
                {
                    Toast.makeText(EditActivity.this,"请输入",Toast.LENGTH_SHORT).show();
                    return;
                }
                update(strInput);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                delete( );
            }
        });
    }


    private void update(String question ) {

        //插入一条数据
        questionDAO.updateQuestions(strOldValue,question);
        Intent data = new Intent();
        data.putExtra("value",question);
        this.setResult(RESULT_OK,data);
        this.finish();
    }


    private void delete( ) {

        //插入一条数据
        questionDAO.deleteByValue(strOldValue );
        Intent data = new Intent();
        data.putExtra("delete",1);
        this.setResult(RESULT_OK,data);
        this.finish();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    public void onClick(View view) {

    }
}
