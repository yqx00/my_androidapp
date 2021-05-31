package com.example.db;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;

import android.net.Uri;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.db.model.Food;

import java.util.List;


public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder> {

    List<Food> FoodList;
    Context context;
//    String s1[],s2[];
//    int images[];

    private OnRowClickListener listener;



    String abc = "content://com.google.android.apps.photos.contentprovider/-1/1/content%3A%2F%2Fmedia%2Fexternal%2Fimages%2Fmedia%2F31/ORIGINAL/NONE/image%2Fjpeg/1281063508";
    Uri ccc = Uri.parse(abc);

    public FoodAdapter(List<Food> FoodList,Context context,OnRowClickListener clickListener) {
        this.context = context;
        this.FoodList = FoodList;
        this.listener = clickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.food_row,parent,false);
        return new MyViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AddActivity a = new AddActivity();
       //124posjf
//        holder.imageView.setImageResource(FoodList.get(position).getImage());
        holder.imageView.setImageURI(Uri.parse(FoodList.get(position).getImage_url()));

        holder.textView1.setText(FoodList.get(position).getTitle());
        holder.textView2.setText(FoodList.get(position).getDetail());


    }


    @Override
    public int getItemCount() {

        return FoodList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{


        public View share;
        TextView textView1,textView2;
        ImageView imageView;
        public OnRowClickListener onRowClickListener;

        public MyViewHolder(@NonNull View itemView,OnRowClickListener onRowClickListener) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            imageView =itemView.findViewById(R.id.imageView);

            this.onRowClickListener = onRowClickListener;
            itemView.setOnClickListener((View.OnClickListener) this);
        }

        @Override
        public void onClick(View v) {
            onRowClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnRowClickListener {
        void onItemClick(int position);
    }
}


