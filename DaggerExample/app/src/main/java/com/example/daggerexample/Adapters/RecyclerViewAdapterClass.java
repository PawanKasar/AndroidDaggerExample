package com.example.daggerexample.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.daggerexample.Models.HerosModelClass;
import com.example.daggerexample.R;

import java.util.ArrayList;

public class RecyclerViewAdapterClass extends RecyclerView.Adapter<RecyclerViewAdapterClass.MyViewHolder> {

    ArrayList<HerosModelClass> dataModelClassArrayList = new ArrayList<>();
    HerosModelClass modelClass = new HerosModelClass();
    Context context;
    String imageUrl;

    public RecyclerViewAdapterClass(ArrayList<HerosModelClass> dataModelClassArrayList, HerosModelClass modelClass, Context context){

        this.dataModelClassArrayList = dataModelClassArrayList;
        this.modelClass = modelClass;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerViewAdapterClass.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.extralayout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        modelClass = dataModelClassArrayList.get(position);

        imageUrl = modelClass.getImageurl();

        Glide.with(context)
                .load(imageUrl)
                .into(holder.img_view);

    }

    @Override
    public int getItemCount() {
        return dataModelClassArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView img_view;

        public MyViewHolder(View itemView) {
            super(itemView);

            img_view = itemView.findViewById(R.id.img_View);
        }
    }
}
