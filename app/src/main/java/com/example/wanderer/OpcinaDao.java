package com.example.wanderer;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface OpcinaDao {
    @Query("SELECT * FROM Opcine")
    List<Opcina> getAll();

    @Query("SELECT * FROM Opcine WHERE id IN (:opcinaIds)")
    List<Opcina> loadAllByIds(int[] opcinaIds);

    /*@Query("SELECT * FROM Grad WHERE ime_grada LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    Grad findByName(String first, String last);*/

    @Insert
    void insertAll(Opcina... Opcine);

    @Delete
    void delete(Opcina opcina);
}
