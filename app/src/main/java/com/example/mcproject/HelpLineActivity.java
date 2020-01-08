package com.example.mcproject;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.TreeMap;

public class HelpLineActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    //this method initializes all the variable and call the on create methods for this activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_line_activity);
        TreeMap<String,String> HelpData = SetHelpLine();
        recyclerView = (RecyclerView) findViewById(R.id.help_line_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new HelpLineAdapter(HelpData);
        recyclerView.setAdapter(mAdapter);
        getSupportActionBar().setTitle("STUDENT HELP LINE");
    }

    //the method sets the help Line Data
    private TreeMap<String,String> SetHelpLine(){
        TreeMap<String,String> data = new TreeMap<String,String>();
        data.put("Emergency",  "902-494-4109");
        data.put("Security",  "902-494-6400");
        data.put("Advising & Accessibility Service","902-494-2836");
        data.put("(TTY)","902-494-7091");
        data.put("Black Student Advising Centre","902-494-6648");
        data.put("Counselling & Psychological","902-494-2171");
        data.put("Services(Students)","902-494-2081");
        data.put("DalOut (LGBTQ)","902-494-2190");
        data.put("Employee & Family Assistance Program","1-800-387-4765");
        data.put("Student Health  Service","902-494-2171");
        data.put("Human Rights Equity & Harassment Prevention "," 902-494-6672");
        data.put("Human Resources(Employees)","902-494-3700");
        data.put("International Centre","902-494-1566");
        data.put("Dal Legal Aid" , "902-494-8105");
        data.put("Multifaith Centre","902-494-2287");
        data.put("Native Counselling Unit","902-494-8863");
        data.put("Residence Life","902-494-7077");

        return data;
    }



}


