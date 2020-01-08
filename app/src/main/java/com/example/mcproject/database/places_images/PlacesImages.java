package com.example.mcproject.database.places_images;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
//Class is the Places Images entity with the setters and Getters
@Entity
public class PlacesImages {
    @PrimaryKey(autoGenerate = true)
    public int place_image_id;
    public String image_link;
    public String image_alt;
    public int place_id;

    public void setPlace_id(int place_id) {
        this.place_id = place_id;
    }

    public int getPlace_id() {
        return place_id;
    }

    public int getPlace_image_id() {
        return place_image_id;
    }

    public String getImage_alt() {
        return image_alt;
    }

    public String getImage_link() {
        return image_link;
    }

    public void setImage_alt(String image_alt) {
        this.image_alt = image_alt;
    }

    public void setImage_link(String image_link) {
        this.image_link = image_link;
    }

    public void setPlace_image_id(int place_image_id) {
        this.place_image_id = place_image_id;
    }

}
