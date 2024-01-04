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
	@ColumnInfo(name = "ime_znamenitosti")
	public String ime_znamenitosti;
	@ColumnInfo(name = "geo_lokacija")
	public String geo_lokacija;
	
	
	/*public Znamenitost(String ime_znamenitosti, String geo_lokacija) {
		this.ime_znamenitosti=ime_znamenitosti;
		this.geo_lokacija=geo_lokacija;
	}

	public Znamenitost(JSONObject znamenitost) throws JSONException {
		this.ime_znamenitosti=znamenitost.getString("ime_znamenitosti");
		this.geo_lokacija=znamenitost.getString("geo_lokacija");
	}*/
}
