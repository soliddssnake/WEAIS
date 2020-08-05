package com.example.ibrah.weais;


import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.view.ViewGroup;
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

import okhttp3.Call;
import okhttp3.Response;
import okhttp3.OkHttpClient;
import okhttp3.Callback;
import okhttp3.Request;

import 	androidx.viewpager.widget.ViewPager;
import androidx.fragment.app.Fragment;
import com.google.android.material.tabs.TabLayout;
import android.content.Context;
import java.util.ArrayList;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.FragmentPagerAdapter;
import android.app.ListFragment;
import java.util.List;
import androidx.annotation.NonNull;
import android.preference.PreferenceManager;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity  {

    ArrayList<String> sehirFromApi;
    ArrayList<String> iconFromApi;
    ArrayList<String> durumFromApi;
    ArrayList<String> sıcaklıkFromApi;


    Request request;

    public static Context context;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    private favorites favoritesFragment;
    private arama aramaFragment;

    FavRecyclerAdapter favRecyclerAdapter;

    AppDB appDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        viewPager = findViewById(R.id.viewpager);
        tabLayout = findViewById(R.id.tablayout);

        favoritesFragment = new favorites();
        aramaFragment = new arama();

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),  0);
        viewPagerAdapter.addFragment(aramaFragment, "Arama");
        viewPagerAdapter.addFragment(favoritesFragment, "Favoriler");
        viewPager.setAdapter(viewPagerAdapter);



        sehirFromApi = new ArrayList<>();
        iconFromApi = new ArrayList<>();
        durumFromApi = new ArrayList<>();
        sıcaklıkFromApi = new ArrayList<>();

        context = this;

        appDb = AppDB.getInstance(this);


    }
    public static final Context getContext (){


        return context;
    }


    private class ViewPagerAdapter extends FragmentPagerAdapter{

            private List<Fragment> fragmentList = new ArrayList<>();
            private List<String> fragmentTitle = new ArrayList<>();

            public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior){
                super(fm, behavior);
            }

            public void addFragment(Fragment fragment, String title){
                fragmentList.add(fragment);
                fragmentTitle.add(title);
            }

            @NonNull
            @Override
            public Fragment getItem(int position){
                return fragmentList.get(position);
            }

            @Override
            public int getCount(){
                return fragmentList.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position){
                return fragmentTitle.get(position);
            }

        }
}
