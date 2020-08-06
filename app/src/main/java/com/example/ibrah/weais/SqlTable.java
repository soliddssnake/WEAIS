package com.example.ibrah.weais;

import android.provider.BaseColumns;

public class SqlTable {

    private SqlTable() {}

    public static final class SqlEntry implements BaseColumns{
        public static final String TABLE_NAME = "weatherList";
        public static final String COLUMN_SEHIR = "sehir";
        public static final String COLUMN_DURUM = "havadurumu";
        public static final String COLUMN_SICAKLIK = "sıcaklık";
    }
}
