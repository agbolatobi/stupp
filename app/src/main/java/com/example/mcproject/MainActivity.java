package com.example.mcproject;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mcproject.database.database;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    //this method initializes all the variable and call the on create methods for this activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("HOME");
        ImageView Campus = findViewById(R.id.campus_icon);
        ImageView Events = findViewById(R.id.events_icon);
        ImageView Checklist = findViewById(R.id.check_list_icon);
        ImageView toDoList = findViewById(R.id.to_do_list_icon);
        ImageView places = findViewById(R.id.places_icon);
        ImageView HelpLine = findViewById(R.id.help_line_icon);
        Campus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changeView = new Intent(v.getContext(),CampusMapActivity.class);
                startActivity(changeView);
            }
        });
        Events.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changeView = new Intent(v.getContext(), EventsActivity.class);
                startActivity(changeView);
            }
        });
        Checklist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changeView = new Intent(v.getContext(),CheckListActivity.class);
                startActivity(changeView);
            }
        });
        toDoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changeView = new Intent(v.getContext(),ToDoListActivity.class);
                startActivity(changeView);
            }
        });
        places.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changeView = new Intent(v.getContext(), PlacesActivity.class);
                startActivity(changeView);
            }
        });
        HelpLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changeView = new Intent(v.getContext(),HelpLineActivity.class);
                startActivity(changeView);
            }
        });

        getWeatherData();
    }

    //this method calls the weather api and gets the data then writes it to the UI thread
    public void getWeatherData(){

        final TextView weather_type = findViewById(R.id.weather_type);
        final TextView location= findViewById(R.id.location);
        final TextView temp = findViewById(R.id.temp);
        final TextView weather_description = findViewById(R.id.weather_description);
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="http://api.openweathermap.org/data/2.5/weather?q=Halifax,ca&appid=26238ff1676b3dd83fc92d9dedd7cd19";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try{
                            JSONObject json = new JSONObject(response);
                            JSONArray weather = json.getJSONArray("weather");
                            JSONObject weather_json = weather.getJSONObject(0);
                            weather_type.setText(weather_json.getString("main"));
                            location.setText(json.getString("name"));
                            JSONObject main  = json.getJSONObject("main");

                            weather_description.setText(weather_json.getString("description"));

                            long temperature = Math.round(main.getDouble("temp") - 273.15);
                            temp.setText(temperature+"°C");

                        }catch(Exception e){
                            Log.d("Error",e.getMessage());
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error",error.getMessage()+"");
            }
        });

        queue.add(stringRequest);
    }
}
