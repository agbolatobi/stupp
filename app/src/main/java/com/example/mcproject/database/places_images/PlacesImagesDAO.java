package com.example.mcproject.database.places_images;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;



import java.util.List;
//This is the to Place Images DAO with all the queries to be performed on the Database entity
@Dao
public interface PlacesImagesDAO {
    @Query("SELECT * FROM PlacesImages")
    List<PlacesImages> getAll();

    @Query("SELECT * FROM PlacesImages WHERE place_image_id IN (:place_image_ids)")
    List<PlacesImages> loadAllByIds(int[] place_image_ids);

    @Query("SELECT * FROM PlacesImages WHERE place_image_id LIKE :place_image_id")
    PlacesImages findByPlaceImageId(int place_image_id);

    @Query("SELECT * FROM PlacesImages WHERE place_id LIKE :place_id")
    PlacesImages findByPlaceId(String place_id);

    @Query("SELECT * FROM PlacesImages WHERE image_alt LIKE :image_alt")
    PlacesImages findByImageAlt(String image_alt);

    @Insert
    void insertAll(PlacesImages... placeImage);

    @Delete
    void delete(PlacesImages placeImage);
}
