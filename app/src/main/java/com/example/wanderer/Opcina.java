package com.example.wanderer;

import org.json.JSONException;
import org.json.JSONObject;

public class Opcina {
	String ime_opcine;
	String geo_lokacija;
	String broj_policije;
	
	public Opcina() {
		
	}

    public Opcina(JSONObject opcina) throws JSONException {
		this.ime_opcine = opcina.getString("ime_opcine");
		this.geo_lokacija = opcina.getString("geo_lokacija");
		this.broj_policije = opcina.getString("geo_lokacija");
    }
}
