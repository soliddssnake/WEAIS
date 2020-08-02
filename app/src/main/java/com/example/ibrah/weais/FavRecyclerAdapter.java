package com.example.ibrah.weais;

import android.content.Context;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FavRecyclerAdapter extends RecyclerView.Adapter <FavRecyclerAdapter.CityHolder>   {

    private ArrayList<String> SehirList;


    public FavRecyclerAdapter(ArrayList<String> SehirList) {

        this.SehirList = SehirList;


    }

    @NonNull
    @Override
    public CityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_row, parent,false);

        return new CityHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityHolder holder, int position) {

        holder.sehiradı.setText(SehirList.get(position));


    }

    @Override
    public int getItemCount() {
        return SehirList.size();
    }

    class CityHolder extends RecyclerView.ViewHolder {


        TextView sehiradı;
        ImageView iconadı ;



        public CityHolder(@NonNull View itemView) {
            super(itemView);


            sehiradı = itemView.findViewById(R.id.recycler_row_sehiradı);
            iconadı = itemView.findViewById(R.id.recycler_row_imageview);
        }
    }
}
