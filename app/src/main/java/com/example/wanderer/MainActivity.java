package com.example.wanderer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
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

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, PopupMenu.OnMenuItemClickListener, LocationListener {

    TextView textView;
    Location myLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    private GoogleMap gMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        Context context = this;

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapMain);
        //assert mapFragment != null;
        mapFragment.getMapAsync(this);

        //Imam li dozvolu da koristim lokaciju
        //if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)){}

        getLocation();
        /*if(!checkLocationPermission()) {
            return ContextCompat.checkSelfPermission(
                    this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        }*/



        /*button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GradDao gradDao = db.gradDao();
                List<Grad> grad = gradDao.getByGradName("Sarajevo");
                if(grad.isEmpty()) {

                }

            }
        });*/


            /*JsonDB jsonDB = new JsonDB();
            jsonDB.createFolderInInternalStorage(context);
            String jsonContent = JsonDB.getGradIfNotExists(context, "Sarajevo.json");
            System.out.println(29+" "+jsonContent);
            String gradovi = "gradovi.json";
            Grad Sarajevo = new Grad("Sarajevo.json");
            // private Korisnik korisnik;
            //Grad grad = new Grad(gradovi);*/



    }

    private boolean checkLocationPermission() {
        return true;
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        this.gMap = googleMap;
        LatLng sarajevo = new LatLng(43.8486, 18.3564);
        this.gMap.addMarker(new MarkerOptions().position(sarajevo).title("Dobrodošli u Sarajevo"));
        this.gMap.moveCamera(CameraUpdateFactory.newLatLng(sarajevo));
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
                startSettings();
                return true;
            case(R.id.exit):
                Toast.makeText(this, "exit", Toast.LENGTH_SHORT);
                System.out.println("exit");
                finish();
                    //metoda za izlaz iz app
                return true;
            default:
                System.out.println("nista..");
                return false;
        }

    }
    public void getLocation() {

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        LocationListener.super.onStatusChanged(provider, status, extras);
    }

    @Override
    public void onProviderEnabled(@NonNull String provider) {
        LocationListener.super.onProviderEnabled(provider);
    }

    @Override
    public void onProviderDisabled(@NonNull String provider) {
        LocationListener.super.onProviderDisabled(provider);
    }

    private void startSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}