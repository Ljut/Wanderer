package com.example.wanderer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Do prep stuff.
        prepare();



    }

    private void prepare() {
        //Start App
        System.out.println("test");
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "Gradovi").build();
        startApp();
    }

    private void startApp() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


}