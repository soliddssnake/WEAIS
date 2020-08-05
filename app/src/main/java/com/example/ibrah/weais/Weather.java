package com.example.ibrah.weais;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;





@Entity
public class Weather {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "weather")
    public String weather ;

    public Weather(String weather) {
        this.weather = weather;
    }

    @Ignore
    public Weather(int uid) {
        this.uid = uid;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}


