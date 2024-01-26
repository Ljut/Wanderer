package com.example.wanderer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, PopupMenu.OnMenuItemClickListener, LocationListener {


    ListView listaSugestija;
    ArrayAdapter<String> sugestijeAdapter;
    Location myLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    private static String kategorija="Znamenitost";
    private GoogleMap gMap;

    private GradDao gradDao;
    private OpcinaDao opcinaDao;
    private ZnamenitostDao znamenitostDao;

    private List<String> sugestije;
    private List<Znamenitost> znamenitosti;

    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        /*Runnable runnable = new Runnable(){
            public void run() {
                //some code here
                gradDao = AppDatabase.database.gradDao();
                opcinaDao = AppDatabase.database.opcinaDao();
                znamenitostDao = AppDatabase.database.znamenitostDao();
                znamenitosti = new ArrayList<Znamenitost>();
                System.out.println("query znamenitostDao.getAll()78");
                znamenitosti = znamenitostDao.getAll();
                Log.d("znamenitosti u bazi79","broj znamenitosti u bazi je 40/"+znamenitosti.size());
                for(int i=0;i< znamenitosti.size();i++) {
                    sugestije.add(znamenitosti.get(i).ime_znamenitosti);
                    Log.d("test83",znamenitosti.get(i).ime_znamenitosti);
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();*/

        sugestije = new ArrayList<>();
        for(int i=0;i<SplashScreen.lista_znamenitosti.size();i++) {
            sugestije.add(SplashScreen.lista_znamenitosti.get(i).ime_znamenitosti);
        }

        listaSugestija = findViewById(R.id.lista_sugestija);
        sugestijeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sugestije);

        listaSugestija.setAdapter(sugestijeAdapter);
        Context context = this;

        //searchView = findViewById(R.id.search);

        Grad grad;


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapMain);
        //assert mapFragment != null;
        mapFragment.getMapAsync(this);

        //Imam li dozvolu da koristim lokaciju
        //if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)){}

        getLocation();




        listaSugestija.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle item click here
                String selectedItem = (String) parent.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, "Clicked item: " + selectedItem, Toast.LENGTH_SHORT).show();
            }
        });

        /*searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Log.d("180", "Closed");
                View background = findViewById(R.id.lista_sugestija_background);
                background.setVisibility(View.GONE);
                ListView listView = findViewById(R.id.lista_sugestija);
                listView.setVisibility(View.GONE);
                return false;
            }
        });*/


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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.other_options_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Koja vas lokacija zanima");
        /**/

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Log.d("180", "Closed");
                View background = findViewById(R.id.lista_sugestija_background);
                background.setVisibility(View.GONE);
                ListView listView = findViewById(R.id.lista_sugestija);
                listView.setVisibility(View.GONE);
                return false;
            }
        });

        searchView.onActionViewCollapsed();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //OpcinaDao dao = AppDatabase.database.opcinaDao();
                String query = searchView.getQuery().toString();
                View background = findViewById(R.id.lista_sugestija_background);
                ListView listView = findViewById(R.id.lista_sugestija);

                if(query.isEmpty()){
                    background.setVisibility(View.GONE);
                    listView.setVisibility(View.GONE);
                }else{
                    background.setVisibility(View.VISIBLE);
                    listView.setVisibility(View.VISIBLE);
                }



                sugestijeAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
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