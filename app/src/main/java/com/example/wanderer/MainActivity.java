package com.example.wanderer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    static private boolean firstRun=false;
    private Korisnik korisnik;
    private Grad grad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!firstRun) {

            firstRun=true;
        }
    }
}