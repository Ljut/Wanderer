package com.example.wanderer;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "opcine")/*(foreignKeys = {@ForeignKey(
		entity = Grad.class,
		parentColumns = "id",
		childColumns = "id_grada",
		onDelete = ForeignKey.CASCADE)})*/


public class Opcina {

	@PrimaryKey
	public int id;
	@ColumnInfo(name = "id_grada")
	public int id_grada;
	@ColumnInfo(name = "ime_opcine")
	public String ime_opcine;
	@ColumnInfo(name = "latitude")
	public Double latitude;
	@ColumnInfo(name = "longitude")
	public Double longitude;
	@ColumnInfo(name = "broj_policije")
	public String broj_policije;
	@ColumnInfo(name = "extra")
	public String extra;
	

}
