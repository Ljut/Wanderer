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

@Database(entities = {Grad.class, Opcina.class, Znamenitost.class, Taksi.class}, version = 1, exportSchema = true)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GradDao gradDao();
    public abstract OpcinaDao opcinaDao();
    public abstract ZnamenitostDao znamenitostDao();
    public abstract TaksiDao taksiDao();


    private static final String DATABASE_NAME = "gradovi.db";

    public static AppDatabase database;


}
