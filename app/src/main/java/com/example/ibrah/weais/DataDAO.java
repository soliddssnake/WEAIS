package com.example.ibrah.weais;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao
public interface DataDAO {
    @Query( "SELECT * FROM weather")
    List<Weather> getWeatherList();

    @Insert
    void insertAll(Weather... weathers);


}
