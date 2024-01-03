package com.example.wanderer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Taksi {
	String ime_taksija;
	String geo_lokacija;
	String[] broj_taksija;
	String broj_taksija_viber;
	String broj_taksija_WhatsApp;
	String email;
	String adresa_udruzenja;
	
	public Taksi() {
		
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
	}
}
