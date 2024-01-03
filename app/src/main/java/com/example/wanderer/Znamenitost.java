package com.example.wanderer;

import org.json.JSONException;
import org.json.JSONObject;

public class Znamenitost {
	String ime_znamenitosti;
	String geo_lokacija;
	
	
	public Znamenitost(String ime_znamenitosti, String geo_lokacija) {
		this.ime_znamenitosti=ime_znamenitosti;
		this.geo_lokacija=geo_lokacija;
	}

	public Znamenitost(JSONObject znamenitost) throws JSONException {
		this.ime_znamenitosti=znamenitost.getString("ime_znamenitosti");
		this.geo_lokacija=znamenitost.getString("geo_lokacija");
	}
}
