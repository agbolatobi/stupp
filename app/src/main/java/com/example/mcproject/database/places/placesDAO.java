package com.example.mcproject.database.places;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import java.util.List;
//This is the Places DAO with all the queries to be performed on the Database entity
@Dao
public interface placesDAO {
    @Query("SELECT * FROM Places")
    List<Places> getAll();

    @Query("SELECT * FROM Places WHERE place_id IN (:placeids)")
    List<Places> loadAllByIds(int[] placeids);

    @Query("SELECT * FROM Places WHERE place_id = :placeid")
    Places loadById(int placeid);

    @Query("SELECT * FROM Places WHERE place_name LIKE :place_name")
    Places findByName(String place_name);

    @Query("SELECT * FROM Places WHERE place_type LIKE :place_type")
    List<Places> findByType(String place_type);

    @Query("SELECT * FROM Places WHERE place_ratings LIKE :place_ratings")
    Places findByRatings(String place_ratings);

    @Insert
    void insertAll(Places... place);

    @Delete
    void delete(Places place);
}
