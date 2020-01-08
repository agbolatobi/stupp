package com.example.mcproject;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.example.mcproject.database.checklist.Checklist;
import com.example.mcproject.database.database;

import java.util.List;


public class CheckListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    //this method initializes all the variable and call the on create methods for this activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checklist_activity);

        List<Checklist> Data = database.getAppDatabase(getApplicationContext()).checklistDAO().getAll();
        recyclerView = (RecyclerView) findViewById(R.id.check_list_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new CheckListAdapter(Data);
        recyclerView.setAdapter(mAdapter);

        getSupportActionBar().setTitle("STUDENT CHECKLIST");

        TextView mobile_app_recommender = findViewById(R.id.mobile_app_recommender);
        mobile_app_recommender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changeView = new Intent(v.getContext(),MobileRecommenderAppActivity.class);
                startActivity(changeView);
            }
        });
    }

}
