package com.example.wanderer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
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
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, PopupMenu.OnMenuItemClickListener, LocationListener {




    ListView listaSugestija;
    ArrayAdapter<String> sugestijeAdapter;
    Location myLocation;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;

    private static String kategorija="ZNAMENITOSTI";
    private GoogleMap gMap;

    private List<String> sugestije;
    Map<String, LatLng> mapZnamenitosti;
    Map<String, LatLng> mapOpcina;
    Set<Marker> setMarkeraZnamenitosti;
    Set<Marker> setMarkeraOpcina;


    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sugestije = new ArrayList<>();
        mapZnamenitosti = new HashMap<>();
        mapOpcina = new HashMap<>();
        for(int i=0;i<SplashScreen.lista_znamenitosti.size();i++) {
            //sugestije.add(SplashScreen.lista_znamenitosti.get(i).ime_znamenitosti);
            mapZnamenitosti.put(SplashScreen.lista_znamenitosti.get(i).ime_znamenitosti,new LatLng(SplashScreen.lista_znamenitosti.get(i).latitude,SplashScreen.lista_znamenitosti.get(i).longitude));
        }
        for(Opcina opcina : SplashScreen.lista_opcina) {
            mapOpcina.put(opcina.ime_opcine,new LatLng(opcina.latitude,opcina.longitude));
        }

        listaSugestija = findViewById(R.id.lista_sugestija);
        if(kategorija=="ZNAMENITOSTI") {
            for(int i=0;i<SplashScreen.lista_znamenitosti.size();i++) {
                sugestije.add(SplashScreen.lista_znamenitosti.get(i).ime_znamenitosti);
            }
        } else if (kategorija=="OPCINE") {
            for(int i=0;i<SplashScreen.lista_opcina.size();i++) {
                sugestije.add(SplashScreen.lista_opcina.get(i).ime_opcine);
            }
        }
        sugestijeAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sugestije);
        listaSugestija.setAdapter(sugestijeAdapter);
        Context context = this;

        //searchView = findViewById(R.id.search);




        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapMain);
        //assert mapFragment != null;
        mapFragment.getMapAsync(this);

        //Imam li dozvolu da koristim lokaciju
        //if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)){}

        //fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        //getLocation();




        listaSugestija.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Handle item click here
                String imeLokacije = (String) parent.getItemAtPosition(position);
                Toast.makeText(MainActivity.this, "Clicked item: " + imeLokacije, Toast.LENGTH_SHORT).show();
                LatLng lokacija = null;
                Marker marker = null;
                if (kategorija == "ZNAMENITOSTI") {
                    lokacija = mapZnamenitosti.get(imeLokacije);
                    for(Marker znamenitost : setMarkeraZnamenitosti) {
                        if (znamenitost.getTitle().equals(imeLokacije)) {
                            marker = znamenitost;
                        }
                    }
                } else if (kategorija == "OPCINE") {
                    lokacija = mapOpcina.get(imeLokacije);
                    for(Marker opcina : setMarkeraZnamenitosti) {
                        if (opcina.getTitle().equals(imeLokacije)) {
                            marker = opcina;
                        }
                    }
                }
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lokacija, 16));

                searchView.setIconified(true);
                marker.showInfoWindow();
                if (marker != null) {
                    marker.showInfoWindow();
                }
            }
        });


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {

        setMarkeraZnamenitosti = new HashSet<>();
        setMarkeraOpcina = new HashSet<>();
        this.gMap = googleMap;
        //LatLng sarajevo = new LatLng(43.8486, 18.3564);
        LatLng grad  = new LatLng(SplashScreen.mojGrad.latitude, SplashScreen.mojGrad.longitude);
        //this.gMap.addMarker(new MarkerOptions().position(sarajevo).title("Dobrodošli u Sarajevo"));
        //this.gMap.addMarker(new MarkerOptions().position(new LatLng(43.8483,18.3567)).title("Dobrodošli Negdje"));
        for(Znamenitost znamenitost : SplashScreen.lista_znamenitosti) {
            setMarkeraZnamenitosti.add(this.gMap.addMarker(new MarkerOptions().position(new LatLng(znamenitost.latitude,znamenitost.longitude)).title(znamenitost.ime_znamenitosti)));
        }

        this.gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(grad, 11));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.other_options_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search);
        searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Koja vas lokacija zanima");


        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Log.d("180", "Closed");

                ListView listView = findViewById(R.id.lista_sugestija);
                listView.setVisibility(View.GONE);
                return false;
            }
        });

        searchView.onActionViewCollapsed();
        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Znamenitost znamenitost : SplashScreen.lista_znamenitosti) {
                    setMarkeraZnamenitosti.add(gMap.addMarker(new MarkerOptions().position(new LatLng(znamenitost.latitude,znamenitost.longitude)).title(znamenitost.ime_znamenitosti)));
                }
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                String imeLokacije = "";
                Marker marker = null;
                if (sugestijeAdapter != null && sugestijeAdapter.getCount() > 0)
                    imeLokacije = sugestijeAdapter.getItem(0);
                LatLng lokacija = new LatLng(0, 0);
                if (kategorija == "ZNAMENITOSTI") {
                    lokacija = mapZnamenitosti.get(imeLokacije);
                    for(Marker znamenitost : setMarkeraZnamenitosti) {
                        if (znamenitost.getTitle().equals(imeLokacije)) {
                            marker = znamenitost;
                        }
                    }
                } else if (kategorija == "OPCINE") {
                    lokacija = mapOpcina.get(imeLokacije);
                    for(Marker opcina : setMarkeraZnamenitosti) {
                        if (opcina.getTitle().equals(imeLokacije)) {
                            marker = opcina;
                        }
                    }
                }
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lokacija, 16));

                if (marker != null) {
                    marker.showInfoWindow();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                String query = searchView.getQuery().toString();
                ListView listView = findViewById(R.id.lista_sugestija);

                if(query.isEmpty()){
                    listView.setVisibility(View.GONE);

                }else{
                    gMap.clear();
                    listView.setVisibility(View.VISIBLE);
                }



                sugestijeAdapter.getFilter().filter(newText);
                for (String item : sugestije) {
                    if (item.toLowerCase().contains(query.toLowerCase())) {
                        //filteredList.add(item);
                        if(kategorija=="ZNAMENITOSTI") {
                           setMarkeraZnamenitosti.add(gMap.addMarker(new MarkerOptions().position(mapZnamenitosti.get(item)).title(item)));
                        } else if (kategorija=="OPCINE") {
                            setMarkeraOpcina.add(gMap.addMarker(new MarkerOptions().position(mapOpcina.get(item)).title(item)));
                        }
                    }
                }
                /*sugestijeAdapter.
                SplashScreen.lista_znamenitosti.
                for(String t : sugestijeAdapter){

                }*/
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
        if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
            Log.d("53","Tu sam");
            return;
        } else {
            Log.d("55","Tu sam");

            Toast.makeText(this,"You woun't see your position in corelation to other locations", Toast.LENGTH_SHORT);
        }

        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location != null) {
                    myLocation = location;
                    SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
                    assert supportMapFragment != null;
                    supportMapFragment.getMapAsync(MainActivity.this);
                    Log.d("71",myLocation.getLatitude()+" "+myLocation.getLongitude());
                }
            }
        });
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

    public void showCategoryMenu(View v) {
        PopupMenu categoryPopup = new PopupMenu(this, v);
        categoryPopup.setOnMenuItemClickListener(item -> {
            if(item.getItemId()==R.id.menu_znamenitosti) {
                kategorija="ZNAMENITOSTI";
                sugestije.clear();
                for(int i=0;i<SplashScreen.lista_znamenitosti.size();i++) {
                    sugestije.add(SplashScreen.lista_znamenitosti.get(i).ime_znamenitosti);
                }
                gMap.clear();
                for(String znamenitost : sugestije)
                    setMarkeraZnamenitosti.add(gMap.addMarker(new MarkerOptions().position(mapZnamenitosti.get(znamenitost)).title(znamenitost)));
                return true;
            } else if (item.getItemId()==R.id.menu_opstine) {
                kategorija="OPCINE";
                sugestije.clear();
                Log.d("398",kategorija);
                for(int i=0;i<SplashScreen.lista_opcina.size();i++) {
                    sugestije.add(SplashScreen.lista_opcina.get(i).ime_opcine);
                }
                gMap.clear();
                for(String opcina : sugestije)
                    setMarkeraOpcina.add(gMap.addMarker(new MarkerOptions().position(mapOpcina.get(opcina)).title(opcina)));
                return true;
            }
            switch (item.getItemId()) {
                case R.id.menu_policija:
                    //return true;
                case R.id.menu_vatrogasci:
                    //return true;
                case R.id.menu_hitna:

                case R.id.menu_taxi:

                    showInformationDialog(item.getTitle().toString());

                    return true;
                // nisam se peglao sa ostalim kategorijama, dajem ti cast
                default:
                    return false;
            }
        });
        categoryPopup.inflate(R.menu.category_menu);
        categoryPopup.show();
    }

    private void showInformationDialog(String category) {
        InformationDialogFragment dialog = InformationDialogFragment.newInstance(category);
        dialog.show(getSupportFragmentManager(), "InformationDialog");
    }

    // ovo je jedino novo u mainu, dodaj i dugmici ce raditi gore
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                startSettings();
                return true;
            case R.id.exit:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}