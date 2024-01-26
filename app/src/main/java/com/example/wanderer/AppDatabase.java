package com.example.wanderer;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Database(entities = {Grad.class, Opcina.class, Znamenitost.class, Taksi.class, TaksiBrojevi.class}, version = 1, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GradDao gradDao();
    public abstract OpcinaDao opcinaDao();
    public abstract ZnamenitostDao znamenitostDao();
    public abstract TaksiDao taksiDao();
    public abstract TaksiBrojeviDao taksiBrojeviDao();

    private static final String DATABASE_NAME = "gradovi.db";

    public static AppDatabase database;




    /*public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, DATABASE_NAME)
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // Copy the pre-existing database file on database creation
            new CopyDbTask().execute();
        }
    };

    private static class CopyDbTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Context context = this;
                InputStream inputStream = context.getAssets().open("your_database.db");
                OutputStream outputStream = new FileOutputStream(
                        context.getDatabasePath(DATABASE_NAME)
                );
                byte[] buffer = new byte[1024];
                int length;
                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                outputStream.flush();
                outputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }*/

}
