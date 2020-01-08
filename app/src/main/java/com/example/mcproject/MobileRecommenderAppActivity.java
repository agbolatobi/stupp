package com.example.mcproject;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.TreeMap;

public class MobileRecommenderAppActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    //this method initializes all the variable and call the on create methods for this activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mobile_app_recommender_activity);
        getSupportActionBar().setTitle("MOBILE APP RECOMMENDER");
        TreeMap<String,String> MobileAppData = SetMobileData();
        recyclerView = (RecyclerView) findViewById(R.id.mobile_app_recommender_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MobileRecommenderAppAdapter(MobileAppData);
        recyclerView.setAdapter(mAdapter);
    }

    //this method initializes the mobile app recommender data
    private TreeMap<String,String> SetMobileData(){
        TreeMap<String,String> data = new TreeMap<String,String>();
        data.put("DalU",  "https://play.google.com/store/apps/details?id=edu.dalhousie.mobileapp&hl=en");
        data.put("Dal Safe",  "https://play.google.com/store/apps/details?id=com.cutcom.apparmor.dal&hl=en");
        data.put("Transit - Real-Time Transport",  "https://play.google.com/store/apps/details?id=com.thetransitapp.droid&hl=en_CA");
        data.put("Maps - Navigate & Explore",  "https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
        return data;
    }
}
