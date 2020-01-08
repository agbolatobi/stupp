package com.example.mcproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mcproject.database.database;
import com.example.mcproject.database.places.Places;

import java.util.List;

public class PlaceTypeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    String place_type;

    //this method initializes all the variable and call the on create methods for this activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_type);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            place_type = extras.getString("place_type");
        }
        getSupportActionBar().setTitle(place_type);
        List<Places> places = database.getAppDatabase(getApplicationContext()).placeDAO().findByType(place_type);
        recyclerView = (RecyclerView) findViewById(R.id.place_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new PlaceTypeAdapter(places);
        recyclerView.setAdapter(mAdapter);


    }
}
