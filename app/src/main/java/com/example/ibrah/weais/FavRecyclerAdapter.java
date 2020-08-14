package com.example.ibrah.weais;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.telecom.Call;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.StrictMode;
import okhttp3.Response;
import okhttp3.OkHttpClient;
import okhttp3.Callback;
import okhttp3.Request;
import java.io.IOException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import 	android.content.ContentValues;
import java.sql.PreparedStatement;
import org.jetbrains.annotations.NotNull;
import 	android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class FavRecyclerAdapter extends RecyclerView.Adapter <FavRecyclerAdapter.CityHolder> {

    private Context mContext;
     Cursor mCursor;

    public FavRecyclerAdapter(Context context, Cursor cursor) {
        mContext = context;
        mCursor = cursor;
    }

    @NonNull
    @Override
    public CityHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.recycler_row, parent, false);
        return new CityHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final CityHolder holder, int position) {
        if (!mCursor.moveToPosition(position)) {
            return;
        }
        final String sehir = mCursor.getString(mCursor.getColumnIndex(SqlTable.SqlEntry.COLUMN_SEHIR));

        holder.sehiradı.setText(sehir);
        holder.silmebutonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 String[] selectionArgs = {sehir};
                arama.mDatabase.delete(SqlTable.SqlEntry.TABLE_NAME,
                        SqlTable.SqlEntry.COLUMN_SEHIR + "=?", selectionArgs);
                swapCursor(arama.getAllItems());

            }

        });
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

    static class CityHolder extends RecyclerView.ViewHolder {


        static TextView sehiradı;
        static ImageView iconadı;
        static TextView durumadı;
        static TextView sıcaklıkadı;

        FloatingActionButton silmebutonu;

        public CityHolder (View itemView) {
            super(itemView);

            sehiradı = itemView.findViewById(R.id.recycler_row_sehiradı);
            iconadı = itemView.findViewById(R.id.recycler_row_imageview);
            durumadı = itemView.findViewById(R.id.recycler_row_durumadı);
            sıcaklıkadı = itemView.findViewById(R.id.recycler_row_sıcaklıkadı);
            silmebutonu = itemView.findViewById(R.id.silme_butonu);
        }

    }

}



