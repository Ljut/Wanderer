package com.example.wanderer;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONException;
import org.json.JSONObject;
@Entity
public class Opcina {

	@PrimaryKey
	public int uid;
	@ColumnInfo(name = "id_grada")
	public int id_grada;
	@ColumnInfo(name = "ime_opcine")
	public String ime_opcine;
	@ColumnInfo(name = "geo_lokacija")
	public String geo_lokacija;
	@ColumnInfo(name = "broj_policije")
	public String broj_policije;
	
	/*public Opcina() {
		
	}

    public Opcina(JSONObject opcina) throws JSONException {
		this.ime_opcine = opcina.getString("ime_opcine");
		this.geo_lokacija = opcina.getString("geo_lokacija");
		this.broj_policije = opcina.getString("geo_lokacija");
    }*/
}
