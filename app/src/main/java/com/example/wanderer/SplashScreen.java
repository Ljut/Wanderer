package com.example.wanderer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.util.List;

public class SplashScreen extends AppCompatActivity {

    public static List<Znamenitost> lista_znamenitosti;
    public static List<Grad> lista_gradova;
    public static List<Opcina> lista_opcina;
    public static List<Taksi> lista_taksija;
    public static List<Taksi> taksijiUGradu;
    public static int id_grada=0;
    public static Grad mojGrad;
    public static Opcina mojaOpcina;
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
        TaksiDao taksiDao = AppDatabase.database.taksiDao();
        System.out.println("deklarisao ZnamenitostDao dao");
        lista_znamenitosti = znamenitostDao.getAll();//dao.getAll();
        lista_gradova = gradDao.getAll();
        lista_opcina = opcinaDao.getAll();
        lista_taksija = taksiDao.getAll();



        SharedPreferences sharedPreferences = getSharedPreferences("AppSettingsPref", MODE_PRIVATE);
        int grad = sharedPreferences.getInt("mojGrad",0);
        int opcina = sharedPreferences.getInt("mojaOpstina",0);
        mojGrad=SplashScreen.lista_gradova.get(grad);
        mojaOpcina=SplashScreen.lista_opcina.get(opcina);

        taksijiUGradu = taksiDao.loadAllFromCity(mojGrad.id);

        //Provjera da je sve učitano
        Log.d("znamenitosti u bazi","broj znamenitosti u bazi je 40/"+lista_znamenitosti.size());
        Log.d("opcine u bazi","broj opcina u bazi je 5/"+lista_opcina.size());
        Log.d("gradovi u bazi","broj gradova u bazi je 1/"+lista_gradova.size());
        Log.d("taksiji u bazi","broj taksija u bazi je 6/"+lista_taksija.size());
        /*for(Znamenitost znamenitost : lista_znamenitosti) {
            Log.d("lista_znamnitosti",znamenitost.ime_znamenitosti);
        }*/
        startApp();
    }

    private void startApp() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


}