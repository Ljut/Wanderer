package com.example.wanderer;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaksiDao {
    @Query("SELECT * FROM Taksi")
    List<Taksi> getAll();

    @Query("SELECT * FROM Taksi WHERE id IN (:taksiIds)")
    List<Taksi> loadAllByIds(int[] taksiIds);

    /*@Query("SELECT * FROM Grad WHERE ime_grada LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    Grad findByName(String first, String last);*/

    @Insert
    void insertAll(Taksi... taksiji);

    @Delete
    void delete(Taksi taksi);
}
