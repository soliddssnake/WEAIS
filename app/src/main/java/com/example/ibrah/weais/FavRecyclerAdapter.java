package com.example.ibrah.weais;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class FavRecyclerAdapter extends RecyclerView.Adapter <FavRecyclerAdapter.CityHolder> {

   // private ArrayList<String> ImageList;
    private ArrayList<String> SehirList;
   // private ArrayList<String> DurumList;
    //private ArrayList<String> SıcaklıkList;



    public FavRecyclerAdapter( ArrayList<String> sehiradı) {
        //ImageList = imageList;
        SehirList = sehiradı;
        //DurumList = durumList;
        //SıcaklıkList = sıcaklıkList;
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

        ((MainActivity)getActivity()).sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        Gson gson = new Gson();
        String json = sharedPrefs.getString(request.url().toString(), "");
        Type type = new TypeToken<List<SehirList>>() {}.getType();
        List<ArrayObject> arrayList = gson.fromJson(json, type);

        //holder.sehiradı.setText(SehirList.get(position));
        //holder.durumadı.setText(DurumList.get(position));
        //holder.sıcaklıkadı.setText(SıcaklıkList.get(position));
        //ImageList.get(position);
    }

    @Override
    public int getItemCount() {
        return SehirList.size();
    }

    class CityHolder extends RecyclerView.ViewHolder {

        //ImageView imageView;
        TextView sehiradı;
        //TextView durumadı;
       // TextView sıcaklıkadı;

        public CityHolder(@NonNull View itemView) {
            super(itemView);

           // imageView = itemView.findViewById(R.id.recycler_row_imageview);
            sehiradı = itemView.findViewById(R.id.recycler_row_sehiradı);
           // durumadı = itemView.findViewById(R.id.recycler_row_durumadı);
           // sıcaklıkadı = itemView.findViewById(R.id.recycler_row_sıcaklıkadı);
        }
    }
}
