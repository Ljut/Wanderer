package com.example.wanderer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.wanderer.jsondb.JsonDB;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, PopupMenu.OnMenuItemClickListener {

    static private boolean firstRun=false;
    TextView textView;
    Button button;
    private GoogleMap gMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //textView = findViewById(R.id.autori);
        //button = findViewById(R.id.button);
        Context context = this;

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        //assert mapFragment != null;
        mapFragment.getMapAsync(this);


        /*if(!checkLocationPermission()) {
            return ContextCompat.checkSelfPermission(
                    this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        }*/

        System.out.println("test");
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "Gradovi").build();

        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GradDao gradDao = db.gradDao();
                List<Grad> grad = gradDao.getByGradName("Sarajevo");
                if(grad.isEmpty()) {

                }

            }
        });*/
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

    private boolean checkLocationPermission() {
        return true;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.other_options_menu);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case(R.id.settings):
                System.out.println("settings");
                    // metoda za settings
                return true;
            case(R.id.exit):
                Toast.makeText(this, "exit", Toast.LENGTH_SHORT);
                System.out.println("exit");
                    //metoda za izlaz iz app
                return true;
            default:
                System.out.println("nista..");
                return false;
        }

    }
}