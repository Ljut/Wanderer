package com.example.wanderer;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {@ForeignKey(
        entity = Taksi.class,
        parentColumns = "id",
        childColumns = "id_taksija",
        onDelete = ForeignKey.CASCADE)})
public class TaksiBrojevi {
    @PrimaryKey
    public int id;
    @ColumnInfo(name = "id_taksija")
    public int id_taksija;
    @ColumnInfo(name = "broj_taksija")
    public String broj_taksija;
    @ColumnInfo(name = "extra")
    public String extra;
}
