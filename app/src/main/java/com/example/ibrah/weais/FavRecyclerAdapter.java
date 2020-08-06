package com.example.ibrah.weais;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class FavRecyclerAdapter extends RecyclerView.Adapter <FavRecyclerAdapter.CityHolder> {


    /*private ArrayList<String> SehirList;
    private ArrayList<String> IconList;
    private ArrayList<String> DurumList;
    private ArrayList<String> SıcaklıkList;*/
    private Context mContext;
    private Cursor mCursor;

    public FavRecyclerAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }
/*public FavRecyclerAdapter(ArrayList<String> SehirList, ArrayList<String> IconList, ArrayList<String> DurumList,
                              ArrayList<String> SıcaklıkList) {

        this.SehirList = SehirList;
        this.IconList = IconList;
        this.DurumList = DurumList;
        this.SıcaklıkList = SıcaklıkList;


    }*/

    @NonNull
    @Override
    public CityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_row, parent, false);
        return new CityHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CityHolder holder, int position) {
        if (!mCursor.moveToPosition(position)){
            return;
        }

        String sehir = mCursor.getString(mCursor.getColumnIndex(SqlTable.SqlEntry.COLUMN_SEHIR));
        String durum = mCursor.getString(mCursor.getColumnIndex(SqlTable.SqlEntry.COLUMN_DURUM));
        String sıcaklık = mCursor.getString(mCursor.getColumnIndex(SqlTable.SqlEntry.COLUMN_SICAKLIK));

        holder.sehiradı.setText(sehir);

        /*switch (IconList.get(position)) {
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
        }*/

        holder.durumadı.setText(durum);
        holder.sıcaklıkadı.setText(sıcaklık);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null){
            mCursor.close();
        }
        mCursor = newCursor;

        if (newCursor != null) {
            notifyDataSetChanged();
        }
    }


    class CityHolder extends RecyclerView.ViewHolder {


        TextView sehiradı;
        ImageView iconadı;
        TextView durumadı;
        TextView sıcaklıkadı;



        public CityHolder (View itemView) {
            super(itemView);

            sehiradı = itemView.findViewById(R.id.recycler_row_sehiradı);
            iconadı = itemView.findViewById(R.id.recycler_row_imageview);
            durumadı = itemView.findViewById(R.id.recycler_row_durumadı);
            sıcaklıkadı = itemView.findViewById(R.id.recycler_row_sıcaklıkadı);

        }




    }
}

