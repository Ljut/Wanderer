package com.example.wanderer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private final String gradovi="gradovi.json";
    static private boolean firstRun=false;
    private Korisnik korisnik;
    private Grad grad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = this;

        System.out.println("test");
        if(!firstRun) {
            JsonDB jsonDB = new JsonDB();
            jsonDB.createFolderInInternalStorage_and_populate(context);

            grad = new Grad(gradovi);
            firstRun=true;
        }
    }
}