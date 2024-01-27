package com.example.wanderer;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
@Entity(tableName = "taksi")/*(foreignKeys = {@ForeignKey(
		entity = Grad.class,
		parentColumns = "id",
		childColumns = "id_grada",
		onDelete = ForeignKey.CASCADE)})*/
public class Taksi {
	@PrimaryKey
	public int id;
	@ColumnInfo(name = "ime_taksija")
	String ime_taksija;
	@ColumnInfo(name = "id_grada")
	public int id_grada;
	@ColumnInfo(name = "broj_telefona")
	String broj_telefona;
	@ColumnInfo(name = "extra")
	public String extra;
	

}
