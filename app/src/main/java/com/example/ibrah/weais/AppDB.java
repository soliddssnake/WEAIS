package com.example.ibrah.weais;

import android.content.Context;

import androidx.annotation.VisibleForTesting;
import androidx.room.Database;
import androidx.room.DatabaseView;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.transition.Visibility;

@Database(entities = {Weather.class}, version = 2)
public abstract class AppDB extends RoomDatabase {

    private static final String DB_NAME = "weather_db";
    public static AppDB db;

    public static synchronized AppDB getInstance(Context context) {
        if(db == null) {
            db = Room.databaseBuilder(context.getApplicationContext(),AppDB.class, DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return db;
    }
    public abstract DataDAO dataDAO();

}






