package com.example.mcproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class PlacesActivity extends AppCompatActivity {

    TextView buildings;
    TextView stores;
    TextView restaurants;

    //this method initializes all the variable and call the on create methods for this activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.places_activity);
        getSupportActionBar().setTitle("PLACES");


        buildings = findViewById(R.id.buildings);
        stores = findViewById(R.id.stores);
        restaurants = findViewById(R.id.restaurants);

        buildings.setText("Buildings");
        stores.setText("Stores");
        restaurants.setText("Restaurants");

        buildings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),PlaceTypeActivity.class);
                i.putExtra("place_type","Buildings");
                startActivity(i);
            }
        });

        stores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),PlaceTypeActivity.class);
                i.putExtra("place_type","Stores");
                startActivity(i);
            }
        });

        restaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(),PlaceTypeActivity.class);
                i.putExtra("place_type","Restaurants");
                startActivity(i);
            }
        });
    }
}
