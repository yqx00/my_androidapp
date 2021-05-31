package com.example.db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


import com.example.db.data.DatabaseHelper;
import com.example.db.model.Food;

import java.util.List;


public class HomeActivity extends AppCompatActivity implements FoodAdapter.OnRowClickListener {

    RecyclerView recyclerView;
    FoodAdapter foodAdapter;
    DatabaseHelper db2;
    List<Food> foodList;
//    ArrayList<String> FoodArrayList;

//    String[] titleList= {"1", "2", "3", "4"};

//    public ImageView iv;
//    String imageUri = "content://com.google.android.apps.photos.contentprovider/-1/1/content%3A%2F%2Fmedia%2Fexternal%2Fimages%2Fmedia%2F31/ORIGINAL/NONE/image%2Fjpeg/1541695904";
//    Uri ccc = Uri.parse(imageUri);

//    Integer[] imageList = {R.drawable.food, R.drawable.food, R.drawable.food, R.drawable.food};
//    String[] detailList = {"Sydney Opera House", "Great Barrier Reef", "Kangaroo Island", "Kakadu National Park"};

    ImageButton btn_add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
//       Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        db2 = new DatabaseHelper(this);
        btn_add = findViewById(R.id.btn_add);

//        FoodArrayList = new ArrayList<>();
        //foodList = new ArrayList<>();
        foodList = db2.fetchAllFood();
        //调函数
        AddActivity a = new AddActivity();

        recyclerView = findViewById(R.id.recyclerView);
        foodAdapter = new FoodAdapter(foodList, HomeActivity.this,this);
        recyclerView.setAdapter(foodAdapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL ,false);
        recyclerView.setLayoutManager(layoutManager);



    }


    @Override
    public void onItemClick(int position) {
    }

    public void addClick(View view) {
        Intent intent = new Intent(this, AddActivity.class);
//        intent.putExtra("username",name);
        startActivity(intent);
    }


    public void menuClick(View view) {
        Fragment fragment;
        fragment = new MenuFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.MenuFragment, fragment).commit();

    }


    }
