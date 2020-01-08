package com.example.mcproject;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mcproject.database.database;
import com.example.mcproject.database.places.Places;

public class PlaceDetailsActivity extends AppCompatActivity {
    TextView title;
    TextView description;
    TextView ratings;
    ImageView imageView;
    Button navigate;
    Button imageToggle;
    ImageView place_image;
    int place_id;
    int current_image = 1;

    //this method initializes all the variable and call the on create methods for this activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_details_activity);
        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            place_id = extras.getInt("place_id");
        }
        getSupportActionBar().setTitle("PLACE DETAILS");
         final Places place = database.getAppDatabase(getApplicationContext()).placeDAO().loadById(place_id);
         title = findViewById(R.id.place_detail_title);
         description = findViewById(R.id.place_detail_description);
         ratings = findViewById(R.id.place_detail_ratings);
         navigate = findViewById(R.id.place_detail_navigate_button);
         imageToggle = findViewById(R.id.place_detail_toggle);
         place_image  = findViewById(R.id.place_detail_image);
         title.setText(place.getPlace_name());
         description.setText(place.getPlace_description());
         ratings.setText(place.getPlace_ratings()+"");
         place_image.setImageResource(place.getPlace_img_1());


         navigate.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(v.getContext(),CampusMapActivity.class);
                 intent.putExtra("place_id",place.place_id);
                 v.getContext().startActivity(intent);
             }
         });

         imageToggle.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                if(current_image==1){
                    current_image = 2;
                    place_image.setImageResource(place.getPlace_img_2());
                }else{
                    current_image = 1;
                    place_image.setImageResource(place.getPlace_img_1());
                }
             }
         });


    }
}
