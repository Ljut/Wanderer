package com.example.wanderer;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Opcina {

	@PrimaryKey
	public int uid;
	@ColumnInfo(name = "id_grada")
	public int id_grada;
	@ColumnInfo(name = "ime_opcine")
	public String ime_opcine;
	@ColumnInfo(name = "latitude")
	public Double latitude;
	@ColumnInfo(name = "longtitude")
	public Double longtitude;
	@ColumnInfo(name = "broj_policije")
	public String broj_policije;
	@ColumnInfo(name = "extra")
	public String extra;
	

}
