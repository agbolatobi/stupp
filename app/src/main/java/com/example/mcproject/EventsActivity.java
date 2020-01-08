package com.example.mcproject;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mcproject.database.database;
import com.example.mcproject.database.events.event;

import java.util.List;

public class EventsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    //this method initializes all the variable and call the on create methods for this activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_activity);
        getSupportActionBar().setTitle("EVENTS");

        List<event> events = database.getAppDatabase(getApplicationContext()).eventDAO().getAll();
        recyclerView = (RecyclerView) findViewById(R.id.events_recycler_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new EventsAdapter(events);
        recyclerView.setAdapter(mAdapter);
    }
}
