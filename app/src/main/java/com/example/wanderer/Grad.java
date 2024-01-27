package com.example.wanderer;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "grad")
public class Grad {
	@PrimaryKey
	public int id;

	@ColumnInfo(name = "ime_grada")
	public String ime_grada;
	@ColumnInfo(name = "latitude")
	public Double latitude;
	@ColumnInfo(name = "longitude")
	public Double longitude;
	@ColumnInfo(name = "broj_hitne")
	public String broj_hitne;
	@ColumnInfo(name = "broj_vatrogasaca")
	public String broj_vatrogasaca;
	@ColumnInfo(name = "broj_centralne_policije")
	public String broj_centralne_policije;

	
}
