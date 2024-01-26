package com.example.wanderer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.util.List;

public class SplashScreen extends AppCompatActivity {

    public static List<Znamenitost> lista_znamenitosti;
    public static List<Grad> lista_gradova;
    public static List<Opcina> lista_opcina;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Runnable runnable = new Runnable(){
            public void run() {
                //some code here
                prepare();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        //Do prep stuff.
        //prepare();



    }

    private void prepare() {
        //Start App
        System.out.println("test");
        //AppDatabase db = Room.databaseBuilder(getApplicationContext(),
        //        AppDatabase.class, "Gradovi").build();
        AppDatabase.database = Room.databaseBuilder(this, AppDatabase.class, "gradovi1.db")
                .createFromAsset("database/gradovi.db")
                .build();
        System.out.println("Mozda imam bazu?");

        //ZnamenitostDao dao = AppDatabase.database.znamenitostDao();
        GradDao gradDao = AppDatabase.database.gradDao();
        OpcinaDao opcinaDao = AppDatabase.database.opcinaDao();
        ZnamenitostDao znamenitostDao = AppDatabase.database.znamenitostDao();
        System.out.println("deklarisao ZnamenitostDao dao");
        lista_znamenitosti = znamenitostDao.getAll();//dao.getAll();
        lista_gradova = gradDao.getAll();
        lista_opcina = opcinaDao.getAll();

        Log.d("znamenitosti u bazi","broj znamenitosti u bazi je 40/"+lista_znamenitosti.size());
        Log.d("opcine u bazi","broj opcina u bazi je 5/"+lista_opcina.size());
        Log.d("gradovi u bazi","broj gradova u bazi je 1/"+lista_gradova.size());
        for(Znamenitost znamenitost : lista_znamenitosti) {
            Log.d("lista_znamnitosti",znamenitost.ime_znamenitosti);
        }
        startApp();
    }

    private void startApp() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


}