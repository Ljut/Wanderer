package com.example.wanderer;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Grad.class, Opcina.class, Znamenitost.class, Taksi.class, TaksiBrojevi.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract GradDao gradDao();
    public abstract OpcinaDao opcinaDao();
    public abstract ZnamenitostDao znamenitostDao();
    public abstract TaksiDao taksiDao();
    public abstract TaksiBrojeviDao taksiBrojeviDao();
}
