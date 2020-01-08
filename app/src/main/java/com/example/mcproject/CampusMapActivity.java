package com.example.mcproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mcproject.database.database;
import com.example.mcproject.database.places.Places;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.tasks.OnSuccessListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CampusMapActivity extends FragmentActivity implements OnMapReadyCallback {

    public static GoogleMap mMap;
    public static Marker mMarker;
    public static  LatLng CurrentLocation;
    public  static List<List<HashMap<String,String>>> routes;
    private Polyline mPolyline;
    public static List<Marker> PlaceMarkers = new ArrayList<Marker>();
    private FusedLocationProviderClient fusedLocationClient;
    private int destination_id = 0;
    Button back;
    Button center;
    Button change_destination;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.campus_map_activity);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            destination_id= extras.getInt("place_id");
        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        back = findViewById(R.id.back);
        back.setText("Back");
        center = findViewById(R.id.center_me);
        center.setText("Recenter");
        change_destination = findViewById(R.id.change_destination);
        change_destination.setText("Destination");

        change_destination.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(v.getContext(),PlacesActivity.class);
                v.getContext().startActivity(intent);
            }
        });

        center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setMyNewLocation();

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),MainActivity.class);
                v.getContext().startActivity(intent);
            }
        });
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            CurrentLocation = new LatLng(44.636732, -63.591719);
            mMarker = mMap.addMarker(new MarkerOptions().position(CurrentLocation).icon(BitmapDescriptorFactory.fromResource(R.drawable.man_user)).draggable(true));
            mMap.setMinZoomPreference(15);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(CurrentLocation));
            Log.d("MAP API","Here");
            if(destination_id != 0){
                Places destination = database.getAppDatabase(getApplicationContext()).placeDAO().loadById(destination_id);
                if(destination != null){
                    LatLng location2 = new LatLng(destination.getPlace_lat(), destination.getPlace_lng());
                    getDirections(mMarker.getPosition(),location2);
                }

            }
        }else{
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                CurrentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                                mMarker = mMap.addMarker(new MarkerOptions().position(CurrentLocation).icon(BitmapDescriptorFactory.fromResource(R.drawable.man_user)).draggable(true));
                                mMap.setMinZoomPreference(15);
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(CurrentLocation));
                                if(destination_id != 0){
                                    Places destination = database.getAppDatabase(getApplicationContext()).placeDAO().loadById(destination_id);
                                    if(destination != null){
                                        LatLng location2 = new LatLng(destination.getPlace_lat(), destination.getPlace_lng());
                                        getDirections(mMarker.getPosition(),location2);
                                    }

                                }
                            }
                        }
                    });
        }
        List<Places> places = database.getAppDatabase(getApplicationContext()).placeDAO().getAll();
        for(Places place : places){
            LatLng Location = new LatLng(place.place_lat, place.place_lng);
            Marker marker = mMap.addMarker(new MarkerOptions().position(Location).title(place.getPlace_name()));
            PlaceMarkers.add(marker);
        }


    }

    public void setMyNewLocation(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Toast toast = Toast.makeText(getApplicationContext(),"Grant Location Permission",Toast.LENGTH_LONG);
            toast.show();
        }else{
            fusedLocationClient.getLastLocation()
                    .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                        @Override
                        public void onSuccess(Location location) {
                            if (location != null) {
                                CurrentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                                mMarker = mMap.addMarker(new MarkerOptions().position(CurrentLocation).icon(BitmapDescriptorFactory.fromResource(R.drawable.man_user)).draggable(true));
                                mMap.setMinZoomPreference(15);
                                mMap.animateCamera(CameraUpdateFactory.newLatLng(CurrentLocation));
                            }
                        }
                    });
        }

    }

    public void getDirections(LatLng origin, LatLng destination){
        RequestQueue queue = Volley.newRequestQueue(this);
        String api = "AIzaSyCJjh8Vl7UHwXzEDwH8oNUjhz4Oxd9C130";
        String start = origin.latitude+","+origin.longitude;
        String stop = destination.latitude+","+destination.longitude;
        String url ="https://maps.googleapis.com/maps/api/directions/json?origin="+start+"&destination="+stop+"&key="+api;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        try{
                            JSONObject json = new JSONObject(response);
                            DirectionsConverter converter = new DirectionsConverter();
                            routes = converter.parse(json);
                            ArrayList<LatLng> points = null;
                            PolylineOptions lineOptions = null;

                            // Traversing through all the routes
                            for(int i=0;i<routes.size();i++){
                                points = new ArrayList<LatLng>();
                                lineOptions = new PolylineOptions();

                                // Fetching i-th route
                                List<HashMap<String, String>> path = routes.get(i);

                                // Fetching all the points in i-th route
                                for(int j=0;j<path.size();j++){
                                    HashMap<String,String> point = path.get(j);

                                    double lat = Double.parseDouble(point.get("lat"));
                                    double lng = Double.parseDouble(point.get("lng"));
                                    LatLng position = new LatLng(lat, lng);

                                    points.add(position);
                                }

                                // Adding all the points in the route to LineOptions
                                lineOptions.addAll(points);
                                lineOptions.width(8);
                                lineOptions.color(Color.RED);
                            }

                            // Drawing polyline in the Google Map for the i-th route
                            if(lineOptions != null) {
                                if(mPolyline != null){
                                    mPolyline.remove();
                                }
                                mPolyline = mMap.addPolyline(lineOptions);

                            }else{ Toast.makeText(getApplicationContext(),"No route is found", Toast.LENGTH_LONG).show(); }
                        }catch (Exception e){
                            Log.d("error",e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(getApplicationContext(), "Not Working", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        queue.add(stringRequest);
    }
}
