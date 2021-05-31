package com.example.tourism2;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    String s1[],s2[];
    int images[];

    private OnRowClickListener listener;


    public MyAdapter(Context context, String s1[], String s2[], int images[],OnRowClickListener clickListener) {
        this.context = context;
        this.s1 = s1;
        this.s2 = s2;
        this.images = images;

        this.listener = clickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row,parent,false);
        return new MyViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView1.setText(s1[position]);
        holder.textView2.setText(s2[position]);
        holder.myImageView.setImageResource(images[position]);

    }

    @Override
    public int getItemCount() {

//        System.out.println(s1[0]);
//        System.out.println(images.length);
        return images.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textView1,textView2;
        ImageView myImageView;
        public OnRowClickListener onRowClickListener;

        public MyViewHolder(@NonNull View itemView,OnRowClickListener onRowClickListener) {
            super(itemView);

            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
            myImageView =itemView.findViewById(R.id.myImageView);

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



