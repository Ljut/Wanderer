package com.example.wanderer;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import org.json.JSONException;
import org.json.JSONObject;
@Entity/*(foreignKeys = {@ForeignKey(
		entity = Grad.class,
		parentColumns = "id",
		childColumns = "id_grada",
		onDelete = ForeignKey.CASCADE),
		@ForeignKey(
		entity = Opcina.class,
		parentColumns = "id",
		childColumns = "id_opcine",
		onDelete = ForeignKey.CASCADE)})*/
public class Znamenitost {

	@PrimaryKey
	public int id;
	@ColumnInfo(name = "id_grada")
	public int id_grada;
	@ColumnInfo(name = "id_opcine")
	public int id_opcine;
	@ColumnInfo(name = "ime_znamenitosti")
	public String ime_znamenitosti;
	@ColumnInfo(name = "latitude")
	public Double latitude;
	@ColumnInfo(name = "longitude")
	public Double longitude;
	@ColumnInfo(name = "extra")
	public String extra;
	
	

}
