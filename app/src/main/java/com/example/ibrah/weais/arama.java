package com.example.ibrah.weais;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.gson.Gson;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.content.Context;
import android.preference.PreferenceManager;
import android.content.SharedPreferences.Editor;
import androidx.room.Room;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.RoomDatabase;
import android.app.Activity;
import android.support.v4.app.*;
import android.os.StrictMode;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnKeyListener;
import android.view.KeyEvent;
import android.widget.TextView.OnEditorActionListener;
import 	android.view.inputmethod.EditorInfo;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Response;
import okhttp3.OkHttpClient;
import okhttp3.Callback;
import okhttp3.Request;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.List;
import 	android.content.ContentValues;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link arama#newInstance} factory method to
 * create an instance of this fragment.
 */
public class arama extends Fragment   {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    TextView view_sehir;
    TextView view_sıcaklık;
    TextView view_durum;
    ImageView view_hava;
    EditText aramatext;

    FloatingActionButton aramabutonu;
    FloatingActionButton eklemebutonu;

    public static SQLiteDatabase mDatabase;


    public static arama newInstance(String param1, String param2) {
        arama fragment = new arama();
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

    private void addItem() {
        if(view_sehir.getText().toString().trim().length() == 0) {
            return;
        }
        String sehir = view_sehir.getText().toString();

        ContentValues cv = new ContentValues();
        cv.put(SqlTable.SqlEntry.COLUMN_SEHIR, sehir);

        mDatabase.insert(SqlTable.SqlEntry.TABLE_NAME, null, cv);
        ((MainActivity)getActivity()).favRecyclerAdapter.swapCursor(getAllItems());
    }

    public static Cursor getAllItems() {
        String[] projection = {
                SqlTable.SqlEntry.COLUMN_SEHIR
        };
        return mDatabase.query(
                SqlTable.SqlEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                SqlTable.SqlEntry.COLUMN_TIMESTAMP + " DESC"
        );
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_arama, container, false);

        view_sehir = viewGroup.findViewById(R.id.sehir);
        view_sehir.setText("");
        view_sıcaklık = viewGroup.findViewById(R.id.sıcaklık);
        view_sıcaklık.setText("");
        view_durum = viewGroup.findViewById(R.id.durum);
        view_durum.setText("");
        view_hava = viewGroup.findViewById(R.id.ic_hava);
        aramatext = viewGroup.findViewById(R.id.arama_text);
        aramabutonu = viewGroup.findViewById(R.id.arama_butonu);
        eklemebutonu = viewGroup.findViewById(R.id.ekleme_butonu);


        DBHelper dbHelper = new DBHelper(this.getContext());
        mDatabase = dbHelper.getWritableDatabase();

        ((MainActivity)getActivity()).favRecyclerAdapter = new FavRecyclerAdapter(this.getContext(), getAllItems());




        aramatext.setOnEditorActionListener(new OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    InputMethodManager inmetman = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inmetman.hideSoftInputFromWindow(getActivity().getCurrentFocus().getRootView().getWindowToken(), 0);
                    apiKey(String.valueOf(aramatext.getText()));
                    handled = true;
                }
                return handled;
            }
        });
        aramabutonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager inmetman = (InputMethodManager) getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
                inmetman.hideSoftInputFromWindow(getActivity().getCurrentFocus().getRootView().getWindowToken(), 0);
                apiKey(String.valueOf(aramatext.getText()));
            }

        });




        return viewGroup;
    }

    public void apiKey(final String Sehir) {

        OkHttpClient client = new OkHttpClient();
        ((MainActivity)getActivity()).request = new Request.Builder()
                .url("https://api.openweathermap.org/data/2.5/weather?q=" +Sehir+ "&id=524901&APPID=d0280f511ea4590b2706e1b67f31e030&units=metric&lang=tr")
                .get()
                .build();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            Response response= client.newCall(((MainActivity)getActivity()).request).execute();
            client.newCall(((MainActivity)getActivity()).request).enqueue(new Callback() {
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

                        final String description=object.getString("description");
                        final String icons = object.getString("icon");

                        JSONObject temp1= json.getJSONObject("main");
                        Double Temperature=temp1.getDouble("temp");

                        final String temps = Math.round(Temperature)+" °C";

                        setText(view_sehir, Sehir);
                        setText(view_sıcaklık, temps);
                        setText(view_durum,description);
                        setImage(view_hava ,icons);

                        eklemebutonu.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View v) {
                                addItem();
                            }
                        });


                    } catch (JSONException e) {

                        getActivity().runOnUiThread(new Runnable(){
                            public void run() {
                                Toast.makeText(getActivity(), "Geçerli bir şehir giriniz!",Toast.LENGTH_LONG).show();
                            }
                        });

                    }
                }
            });
        }catch (IOException e){

            e.printStackTrace();
        }

    }
    private void setText(final TextView text, final String value){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                    text.setText(value);
            }
        });
    }

    private void setImage(final ImageView imageView, final String value){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                switch (value){
                    case "01d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.gunes));
                        break;
                    case "01n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.acikgece));
                        break;
                    case "02d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.parcalibulut));
                        break;
                    case "02n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.geceparcali));
                        break;
                    case "03d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.cokbulut));
                        break;
                    case "03n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.bulutgece));
                        break;
                    case "04d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.bulut));
                        break;
                    case "04n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.bulut));
                        break;
                    case "09d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.yagmur));
                        break;
                    case "09n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.yagmur));
                        break;
                    case "10d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.gunyagmur));
                        break;
                    case "10n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.yagmurgece));
                        break;
                    case "11d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.simsek));
                        break;
                    case "11n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.simsek));
                        break;
                    case "13d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.kar));
                        break;
                    case "13n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.kargece));
                        break;
                    case "50d": imageView.setImageDrawable(getResources().getDrawable(R.drawable.sis));
                        break;
                    case "50n": imageView.setImageDrawable(getResources().getDrawable(R.drawable.sisgece));
                        break;
                    default:
                        Toast.makeText(getActivity(), "Lütfen bir şehir giriniz!",Toast.LENGTH_LONG).show();
                    break;
                }
            }
        });
    }

}