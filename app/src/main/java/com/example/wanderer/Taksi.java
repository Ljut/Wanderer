package com.example.wanderer;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
@Entity
public class Taksi {
	@PrimaryKey
	public int uid;
	@ColumnInfo(name = "ime_taksija")
	String ime_taksija;
	@ColumnInfo(name = "id_grada")
	public int id_grada;
	@ColumnInfo(name = "latitude")
	public Double latitude;
	@ColumnInfo(name = "longtitude")
	public Double longtitude;

	//@ColumnInfo(name = "ime_grada")
	//String[] broj_taksija;
	@ColumnInfo(name = "broj_taksija_viber")
	String broj_taksija_viber;
	@ColumnInfo(name = "broj_taksija_WhatsApp")
	String broj_taksija_WhatsApp;
	@ColumnInfo(name = "email")
	String email;
	@ColumnInfo(name = "adresa_udruzenja")
	String adresa_udruzenja;
	@ColumnInfo(name = "extra")
	public String extra;
	
	/*public Taksi() {
		
	}

    public Taksi(JSONObject taksi) throws JSONException {
		this.ime_taksija = taksi.getString("ime_taksija");
		this.geo_lokacija = taksi.getString("geo_lokacija");
		this.broj_taksija = getBrojevi(taksi.getJSONArray("broj_taksija"));
		this.broj_taksija_viber = taksi.getString("broj_taksija_viber");
		this.broj_taksija_WhatsApp = taksi.getString("broj_taksija_WhatsApp");
		this.email = taksi.getString("email");
		this.adresa_udruzenja = taksi.getString("adresa_udruzenja");
    }

	private String[] getBrojevi(JSONArray brojTaksija) throws JSONException {
		String[] brojevi = new String[brojTaksija.length()];
		for(int i=0;i<brojevi.length;i++) {
			brojevi[i] = brojTaksija.getString(i);
		}
		return brojevi;
	}*/
}
