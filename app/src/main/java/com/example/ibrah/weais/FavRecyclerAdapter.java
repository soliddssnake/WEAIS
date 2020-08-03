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
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FavRecyclerAdapter extends RecyclerView.Adapter <FavRecyclerAdapter.CityHolder>   {


    private ArrayList<String> SehirList;
    private ArrayList<String> IconList;
    private ArrayList<String> DurumList;
    private ArrayList<String> SıcaklıkList;



    public FavRecyclerAdapter(ArrayList<String> SehirList, final ArrayList<String> IconList, ArrayList<String> DurumList,
    ArrayList<String> SıcaklıkList) {

        this.SehirList = SehirList;
        this.IconList = IconList;
        this.DurumList = DurumList;
        this.SıcaklıkList = SıcaklıkList;


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
                holder.iconadı.setImageDrawable(MainActivity.getContext().getResources().getDrawable(R.drawable.gunes));
                break;
            case "01n":
                holder.iconadı.setImageDrawable(MainActivity.getContext().getResources().getDrawable(R.drawable.acikgece));
                break;
            case "02d":
                holder.iconadı.setImageDrawable(MainActivity.getContext().getResources().getDrawable(R.drawable.parcalibulut));
                break;
            case "02n":
                holder.iconadı.setImageDrawable(MainActivity.getContext().getResources().getDrawable(R.drawable.geceparcali));
                break;
            case "03d":
                holder.iconadı.setImageDrawable(MainActivity.getContext().getResources().getDrawable(R.drawable.cokbulut));
                break;
            case "03n":
                holder.iconadı.setImageDrawable(MainActivity.getContext().getResources().getDrawable(R.drawable.bulutgece));
                break;
            case "04d":
                holder.iconadı.setImageDrawable(MainActivity.getContext().getResources().getDrawable(R.drawable.bulut));
                break;
            case "04n":
                holder.iconadı.setImageDrawable(MainActivity.getContext().getResources().getDrawable(R.drawable.bulut));
                break;
            case "09d":
                holder.iconadı.setImageDrawable(MainActivity.getContext().getResources().getDrawable(R.drawable.yagmur));
                break;
            case "09n":
                holder.iconadı.setImageDrawable(MainActivity.getContext().getResources().getDrawable(R.drawable.yagmur));
                break;
            case "10d":
                holder.iconadı.setImageDrawable(MainActivity.getContext().getResources().getDrawable(R.drawable.gunyagmur));
                break;
            case "10n":
                holder.iconadı.setImageDrawable(MainActivity.getContext().getResources().getDrawable(R.drawable.yagmurgece));
                break;
            case "11d":
                holder.iconadı.setImageDrawable(MainActivity.getContext().getResources().getDrawable(R.drawable.simsek));
                break;
            case "11n":
                holder.iconadı.setImageDrawable(MainActivity.getContext().getResources().getDrawable(R.drawable.simsek));
                break;
            case "13d":
                holder.iconadı.setImageDrawable(MainActivity.getContext().getResources().getDrawable(R.drawable.kar));
                break;
            case "13n":
                holder.iconadı.setImageDrawable(MainActivity.getContext().getResources().getDrawable(R.drawable.kargece));
                break;
            case "50d":
                holder.iconadı.setImageDrawable(MainActivity.getContext().getResources().getDrawable(R.drawable.sis));
                break;
            case "50n":
                holder.iconadı.setImageDrawable(MainActivity.getContext().getResources().getDrawable(R.drawable.sisgece));
                break;
            default:
                Toast.makeText(MainActivity.getContext(), "Lütfen bir şehir giriniz!", Toast.LENGTH_LONG).show();
                break;
                }

        holder.durumadı.setText(DurumList.get(position));
        holder.sıcaklıkadı.setText(SıcaklıkList.get(position));
        }

        @Override
        public int getItemCount () {
            return SehirList.size();
        }

        @Entity
        class CityHolder extends RecyclerView.ViewHolder {

            @PrimaryKey(autoGenerate = true)
            private int id;
            TextView sehiradı;
            ImageView iconadı;
            TextView durumadı;
            TextView sıcaklıkadı;

            public CityHolder(@NonNull View itemView, TextView sehiradı, ImageView iconadı, TextView durumadı, TextView sıcaklıkadı) {
                super(itemView);
                this.sehiradı = sehiradı;
                this.iconadı = iconadı;
                this.durumadı = durumadı;
                this.sıcaklıkadı = sıcaklıkadı;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public TextView getSehiradı() {
                return sehiradı;
            }

            public void setSehiradı(TextView sehiradı) {
                this.sehiradı = sehiradı;
            }

            public ImageView getIconadı() {
                return iconadı;
            }

            public void setIconadı(ImageView iconadı) {
                this.iconadı = iconadı;
            }

            public TextView getDurumadı() {
                return durumadı;
            }

            public void setDurumadı(TextView durumadı) {
                this.durumadı = durumadı;
            }

            public TextView getSıcaklıkadı() {
                return sıcaklıkadı;
            }

            public void setSıcaklıkadı(TextView sıcaklıkadı) {
                this.sıcaklıkadı = sıcaklıkadı;
            }

            public CityHolder(@NonNull View itemView) {
                super(itemView);

                sehiradı = itemView.findViewById(R.id.recycler_row_sehiradı);
                iconadı = itemView.findViewById(R.id.recycler_row_imageview);
                durumadı = itemView.findViewById(R.id.recycler_row_durumadı);
                sıcaklıkadı = itemView.findViewById(R.id.recycler_row_sıcaklıkadı);
            }
        }



}
