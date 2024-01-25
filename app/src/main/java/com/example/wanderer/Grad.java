package com.example.wanderer;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
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

	/*Znamenitost[] znamenitosti;
	Opcina[] opcine;
	Taksi[] taksiji;*/

	/*public Grad(String path_to_grad) {
		try {
			JSONObject jsonObject = new JSONObject(path_to_grad);
			this.ime_grada = jsonObject.getString("ime_grada");
			this.geo_lokacija = jsonObject.getString("geo_lokacija");
			this.broj_hitne = jsonObject.getString("broj_hitne");
			this.broj_vatrogasaca = jsonObject.getString("broj_vatrogasaca");
			this.broj_centralne_policije = jsonObject.getString("broj_centralne_policije");

			JSONArray znamenitosti = jsonObject.getJSONArray("znamenitosti");
			this.znamenitosti = new Znamenitost[znamenitosti.length()];
			for(int i=0;i<this.znamenitosti.length;i++) {

				JSONObject znamenitost = (JSONObject) znamenitosti.get(i);
				this.znamenitosti[i]= new Znamenitost(znamenitost);
			}


			JSONArray opcine = jsonObject.getJSONArray("opcine");
			this.opcine = new Opcina[opcine.length()];
			for(int i=0;i<this.opcine.length;i++) {
				JSONObject opcina = (JSONObject) opcine.get(i);
				this.opcine[i] = new Opcina(opcina);
			}

			JSONArray taksiji = jsonObject.getJSONArray(("brojevi_Taksija"));
			this.taksiji = new Taksi[taksiji.length()];
			for(int i=0;i<this.taksiji.length;i++) {
				JSONObject taksi = (JSONObject) taksiji.get(i);
				this.taksiji[i] = new Taksi(taksi);
			}
			
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}*/
	
}
