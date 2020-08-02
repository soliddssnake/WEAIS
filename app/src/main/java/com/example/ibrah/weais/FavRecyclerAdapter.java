package com.example.ibrah.weais;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FavRecyclerAdapter extends RecyclerView.Adapter <FavRecyclerAdapter.CityHolder>   {

    private ArrayList<String> SehirList;
    private ArrayList<String> IconList;

    Context context;


    public FavRecyclerAdapter(ArrayList<String> SehirList,final ArrayList<String> IconList) {

        this.SehirList = SehirList;
        this.IconList = IconList;



    }
    public final Context getContext (){

        return context;
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

        switch (IconList.get(position)) {
            case "01d":
                holder.iconadı.setImageDrawable(getContext().getResources().getDrawable(R.drawable.gunes));
                break;
            case "01n":
                holder.iconadı.setImageDrawable(getContext().getResources().getDrawable(R.drawable.acikgece));
                break;
            case "02d":
                holder.iconadı.setImageDrawable(getContext().getResources().getDrawable(R.drawable.parcalibulut));
                break;
            case "02n":
                holder.iconadı.setImageDrawable(getContext().getResources().getDrawable(R.drawable.geceparcali));
                break;
            case "03d":
                holder.iconadı.setImageDrawable(getContext().getResources().getDrawable(R.drawable.cokbulut));
                break;
            case "03n":
                holder.iconadı.setImageDrawable(getContext().getResources().getDrawable(R.drawable.bulutgece));
                break;
            case "04d":
                holder.iconadı.setImageDrawable(getContext().getResources().getDrawable(R.drawable.bulut));
                break;
            case "04n":
                holder.iconadı.setImageDrawable(getContext().getResources().getDrawable(R.drawable.bulut));
                break;
            case "09d":
                holder.iconadı.setImageDrawable(getContext().getResources().getDrawable(R.drawable.yagmur));
                break;
            case "09n":
                holder.iconadı.setImageDrawable(getContext().getResources().getDrawable(R.drawable.yagmur));
                break;
            case "10d":
                holder.iconadı.setImageDrawable(getContext().getResources().getDrawable(R.drawable.gunyagmur));
                break;
            case "10n":
                holder.iconadı.setImageDrawable(getContext().getResources().getDrawable(R.drawable.yagmurgece));
                break;
            case "11d":
                holder.iconadı.setImageDrawable(getContext().getResources().getDrawable(R.drawable.simsek));
                break;
            case "11n":
                holder.iconadı.setImageDrawable(getContext().getResources().getDrawable(R.drawable.simsek));
                break;
            case "13d":
                holder.iconadı.setImageDrawable(getContext().getResources().getDrawable(R.drawable.kar));
                break;
            case "13n":
                holder.iconadı.setImageDrawable(getContext().getResources().getDrawable(R.drawable.kargece));
                break;
            case "50d":
                holder.iconadı.setImageDrawable(getContext().getResources().getDrawable(R.drawable.sis));
                break;
            case "50n":
                holder.iconadı.setImageDrawable(getContext().getResources().getDrawable(R.drawable.sisgece));
                break;
            default:
                Toast.makeText(getContext(), "Lütfen bir şehir giriniz!", Toast.LENGTH_LONG).show();
                break;
                }

        }

        @Override
        public int getItemCount () {
            return SehirList.size();
        }

        class CityHolder extends RecyclerView.ViewHolder {


            TextView sehiradı;
            ImageView iconadı;


            public CityHolder(@NonNull View itemView) {
                super(itemView);

                sehiradı = itemView.findViewById(R.id.recycler_row_sehiradı);
                iconadı = itemView.findViewById(R.id.recycler_row_imageview);
            }
        }



}
