package com.example.ibrah.weais;

import android.os.Bundle;
import java.util.List;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.OkHttpClient;
import okhttp3.Callback;
import okhttp3.Request;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.io.*;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import okhttp3.OkHttpClient;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.widget.ImageView;
import android.content.ContentValues;
import android.os.Handler;
public class favorites extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    private SQLiteDatabase mDatabase;


    public static favorites newInstance(String param1, String param2) {
        favorites fragment = new favorites();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_favorites, container, false);

        RecyclerView recyclerView = viewGroup.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(((MainActivity)getActivity()).favRecyclerAdapter);

        DBHelper dbHelper = new DBHelper(this.getContext());
        mDatabase = dbHelper.getWritableDatabase();

        String[] projection = {
                SqlTable.SqlEntry.COLUMN_SEHIR
        };
        Cursor cursor = mDatabase.query(SqlTable.SqlEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                SqlTable.SqlEntry.COLUMN_TIMESTAMP + " DESC");
        final List itemIds = new ArrayList<>();

        while(cursor.moveToNext()) {
            String sehirr = cursor.getString(cursor.getColumnIndexOrThrow(SqlTable.SqlEntry.COLUMN_SEHIR));
            itemIds.add(sehirr);
        }

        for (int i = 0; i < itemIds.size(); i++) {
            rowapi(itemIds.get(i).toString());
        }
        cursor.close();

        return viewGroup;
    }



        public void rowapi(final String sehir){

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.openweathermap.org/data/2.5/weather?q=" + sehir
                        + "&id=524901&APPID=d0280f511ea4590b2706e1b67f31e030&units=metric&lang=tr")
                .get()
                .build();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Response response= client.newCall(request).execute();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {

                }
                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {


                    String responseData= response.body().string();
                    try {
                        JSONObject json = new JSONObject(responseData);
                        JSONArray array=json.getJSONArray("weather");
                        JSONObject object=array.getJSONObject(0);

                        final String description = object.getString("description");
                        final String icons = object.getString("icon");

                        JSONObject temp1= json.getJSONObject("main");
                        Double Temperature=temp1.getDouble("temp");

                        final String temps = Math.round(Temperature)+" °C";

                        System.out.println(temps + description);


                            getActivity().runOnUiThread(new Thread(new Runnable() {
                                public void run() {

                                        FavRecyclerAdapter.CityHolder.durumadı.setText(description);
                                        FavRecyclerAdapter.CityHolder.sıcaklıkadı.setText(temps);
                                    
                                    switch (icons) {
                                        case "01d":
                                            FavRecyclerAdapter.CityHolder.iconadı.setImageDrawable(getResources().getDrawable(R.drawable.gunes));
                                            break;
                                        case "01n":
                                            FavRecyclerAdapter.CityHolder.iconadı.setImageDrawable(getResources().getDrawable(R.drawable.acikgece));
                                            break;
                                        case "02d":
                                            FavRecyclerAdapter.CityHolder.iconadı.setImageDrawable(getResources().getDrawable(R.drawable.parcalibulut));
                                            break;
                                        case "02n":
                                            FavRecyclerAdapter.CityHolder.iconadı.setImageDrawable(getResources().getDrawable(R.drawable.geceparcali));
                                            break;
                                        case "03d":
                                            FavRecyclerAdapter.CityHolder.iconadı.setImageDrawable(getResources().getDrawable(R.drawable.cokbulut));
                                            break;
                                        case "03n":
                                            FavRecyclerAdapter.CityHolder.iconadı.setImageDrawable(getResources().getDrawable(R.drawable.bulutgece));
                                            break;
                                        case "04d":
                                            FavRecyclerAdapter.CityHolder.iconadı.setImageDrawable(getResources().getDrawable(R.drawable.bulut));
                                            break;
                                        case "04n":
                                            FavRecyclerAdapter.CityHolder.iconadı.setImageDrawable(getResources().getDrawable(R.drawable.bulut));
                                            break;
                                        case "09d":
                                            FavRecyclerAdapter.CityHolder.iconadı.setImageDrawable(getResources().getDrawable(R.drawable.yagmur));
                                            break;
                                        case "09n":
                                            FavRecyclerAdapter.CityHolder.iconadı.setImageDrawable(getResources().getDrawable(R.drawable.yagmur));
                                            break;
                                        case "10d":
                                            FavRecyclerAdapter.CityHolder.iconadı.setImageDrawable(getResources().getDrawable(R.drawable.gunyagmur));
                                            break;
                                        case "10n":
                                            FavRecyclerAdapter.CityHolder.iconadı.setImageDrawable(getResources().getDrawable(R.drawable.yagmurgece));
                                            break;
                                        case "11d":
                                            FavRecyclerAdapter.CityHolder.iconadı.setImageDrawable(getResources().getDrawable(R.drawable.simsek));
                                            break;
                                        case "11n":
                                            FavRecyclerAdapter.CityHolder.iconadı.setImageDrawable(getResources().getDrawable(R.drawable.simsek));
                                            break;
                                        case "13d":
                                            FavRecyclerAdapter.CityHolder.iconadı.setImageDrawable(getResources().getDrawable(R.drawable.kar));
                                            break;
                                        case "13n":
                                            FavRecyclerAdapter.CityHolder.iconadı.setImageDrawable(getResources().getDrawable(R.drawable.kargece));
                                            break;
                                        case "50d":
                                            FavRecyclerAdapter.CityHolder.iconadı.setImageDrawable(getResources().getDrawable(R.drawable.sis));
                                            break;
                                        case "50n":
                                            FavRecyclerAdapter.CityHolder.iconadı.setImageDrawable(getResources().getDrawable(R.drawable.sisgece));
                                            break;
                                    }
                                }
                            }));



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });
        }catch (IOException e) {
            e.printStackTrace();
        }


    }




}