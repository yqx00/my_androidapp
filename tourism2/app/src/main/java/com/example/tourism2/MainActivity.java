package com.example.tourism2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView topTextView,placeTextView;
    RecyclerView recyclerView,topRecyclerView;
    String s1[],s2[];
    int images[] = {R.drawable.pic1,
                 R.drawable.pic2,
                 R.drawable.pic3,
                 R.drawable.pic4,
                 R.drawable.pic5,};

    int topImages[] = {R.drawable.pic1,
                    R.drawable.pic2,
                    R.drawable.pic3,
                    R.drawable.pic4,
                    R.drawable.pic5,};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        topTextView = findViewById(R.id.topTextView);
        placeTextView = findViewById(R.id.placeTextView);

        recyclerView = findViewById(R.id.recyclerView);
        topRecyclerView = findViewById(R.id.topRecyclerView);

        s1 = getResources().getStringArray(R.array.place_name);
        s2 = getResources().getStringArray(R.array.description);

        MyAdapter myAdapter = new MyAdapter(this,s1,s2,images,this::onItemClick);
        TopMyAdapter topMyAdapter = new TopMyAdapter(this,topImages);

        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));

       topRecyclerView.setAdapter(topMyAdapter);
       topRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));

    }


    public void onItemClick(int position){
        Fragment fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragment = PlaceFragment.newInstance(s1[position],s2[position],images[position]);
        fragmentTransaction.add(R.id.frameLayout, fragment).commitAllowingStateLoss();

        recyclerView.setVisibility(View.INVISIBLE);
        topRecyclerView.setVisibility(View.INVISIBLE);
        topTextView.setVisibility(View.INVISIBLE);
        placeTextView.setVisibility(View.INVISIBLE);

    }

    public void onBackPressed() {
        if (recyclerView.getVisibility() == View.INVISIBLE) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragmentManager.getFragments().get(0));
            fragmentTransaction.commit();
            recyclerView.setVisibility(View.VISIBLE);
            topRecyclerView.setVisibility(View.VISIBLE);
            topTextView.setVisibility(View.VISIBLE);
            placeTextView.setVisibility(View.VISIBLE);
        }
    }
}