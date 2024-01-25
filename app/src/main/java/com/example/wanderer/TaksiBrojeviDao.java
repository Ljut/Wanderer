package com.example.wanderer;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaksiBrojeviDao {
    @Query("SELECT * FROM TaksiBrojevi")
    List<TaksiBrojevi> getAll();

    @Query("SELECT * FROM TaksiBrojevi WHERE id IN (:taksibrIds)")
    List<TaksiBrojevi> loadAllByIds(int[] taksibrIds);

    /*@Query("SELECT * FROM Grad WHERE ime_grada LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    Grad findByName(String first, String last);*/

    @Insert
    void insertAll(TaksiBrojevi... taksiBrojevi);

    @Delete
    void delete(TaksiBrojevi taksiBroj);
}
