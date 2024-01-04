package com.example.wanderer;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class TaksiBrojevi {
    @PrimaryKey
    public int uid;
    @ColumnInfo(name = "id_taksija")
    public int id_taksija;
    @ColumnInfo(name = "broj_taksija")
    public String broj_taksija;
}
