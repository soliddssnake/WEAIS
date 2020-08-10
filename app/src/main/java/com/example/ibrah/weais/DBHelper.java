package com.example.ibrah.weais;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.ibrah.weais.SqlTable.*;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "weatherlist.db";
    public static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_WEATHERLIST_TABLE = "CREATE TABLE " +
                SqlEntry.TABLE_NAME + " (" +
                SqlEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SqlEntry.COLUMN_SEHIR + " TEXT NOT NULL,  " +
                SqlEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        db.execSQL(SQL_CREATE_WEATHERLIST_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + SqlEntry.TABLE_NAME);
        onCreate(db);
    }
}
