package com.example.wanderer;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ZnamenitostDao {
    @Query("SELECT * FROM Znamenitost")
    List<Znamenitost> getAll();

    @Query("SELECT * FROM Znamenitost WHERE uid IN (:znamenitostIds)")
    List<Znamenitost> loadAllByIds(int[] znamenitostIds);

    /*@Query("SELECT * FROM Grad WHERE ime_grada LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    Grad findByName(String first, String last);*/

    @Insert
    void insertAll(Znamenitost... znamenitosti);

    @Delete
    void delete(Znamenitost znamenitost);
}
