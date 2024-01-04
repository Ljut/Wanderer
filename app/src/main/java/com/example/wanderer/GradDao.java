package com.example.wanderer;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GradDao {
    @Query("SELECT * FROM Grad")
    List<Grad> getAll();

    @Query("SELECT * FROM Grad WHERE uid IN (:gradIds)")
    List<Grad> loadAllByIds(int[] gradIds);

    /*@Query("SELECT * FROM Grad WHERE ime_grada LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    Grad findByName(String first, String last);*/

    @Insert
    void insertAll(Grad... Gradovi);

    @Delete
    void delete(Grad grad);

}
