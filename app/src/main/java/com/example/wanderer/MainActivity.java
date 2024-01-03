package com.example.wanderer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;


public class MainActivity extends AppCompatActivity {

    static private boolean firstRun=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = this;
        System.out.println("test");
        if(!firstRun) {

            JsonDB jsonDB = new JsonDB();
            JsonDB.createFolderInInternalStorage(context);
            JsonDB.getGradIfNotExists(context, "Sarajevo.json");

            String gradovi = "gradovi.json";
            // private Korisnik korisnik;
            //Grad grad = new Grad(gradovi);
            firstRun=true;
        }
    }

}