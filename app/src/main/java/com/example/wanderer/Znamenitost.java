package com.example.wanderer;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONException;
import org.json.JSONObject;
@Entity
public class Znamenitost {

	@PrimaryKey
	public int uid;
	@ColumnInfo(name = "id_grada")
	public int id_grada;
	@ColumnInfo(name = "id_opcine")
	public int id_opcine;
	@ColumnInfo(name = "ime_znamenitosti")
	public String ime_znamenitosti;
	@ColumnInfo(name = "latitude")
	public Double latitude;
	@ColumnInfo(name = "longtitude")
	public Double longtitude;
	@ColumnInfo(name = "extra")
	public String extra;
	
	

}
