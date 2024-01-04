package com.example.wanderer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

//import com.example.wanderer.jsondb.JsonDB;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    static private boolean firstRun=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Context context = this;
        System.out.println("test");
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "Gradovi").build();

        if(!firstRun) {

            /*JsonDB jsonDB = new JsonDB();
            jsonDB.createFolderInInternalStorage(context);
            String jsonContent = JsonDB.getGradIfNotExists(context, "Sarajevo.json");
            System.out.println(29+" "+jsonContent);
            String gradovi = "gradovi.json";
            Grad Sarajevo = new Grad("Sarajevo.json");
            // private Korisnik korisnik;
            //Grad grad = new Grad(gradovi);*/
            firstRun=true;

        }
    }

}