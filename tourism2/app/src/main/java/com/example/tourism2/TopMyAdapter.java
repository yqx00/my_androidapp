package com.example.tourism2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TopMyAdapter extends RecyclerView.Adapter<TopMyAdapter.MyViewHolder> {
    Context context;
    int topImages[];



    public TopMyAdapter(Context context,int topImages[]) {
        this.context = context;

        this.topImages = topImages;
    }

    @NonNull
    @Override
    public TopMyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.top_row,parent,false);
        return new TopMyAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.topImageView.setImageResource(topImages[position]);
    }



    @Override
    public int getItemCount() {

//        System.out.println(s1[0]);
//        System.out.println(images.length);
        return topImages.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{


        ImageView topImageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            topImageView =itemView.findViewById(R.id.topImageView);
        }
    }
}