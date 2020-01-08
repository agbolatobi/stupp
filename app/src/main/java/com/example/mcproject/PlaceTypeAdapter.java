package com.example.mcproject;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mcproject.database.places.Places;

import java.util.List;

public class PlaceTypeAdapter extends RecyclerView.Adapter<PlaceTypeAdapter.MyViewHolder> {
    private List<Places> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public LinearLayout LinearView;
        public MyViewHolder(LinearLayout v) {
            super(v);
            LinearView = v;
        }
    }

    public PlaceTypeAdapter(List<Places> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public PlaceTypeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.place_type_view, parent, false);

        PlaceTypeAdapter.MyViewHolder vh = new PlaceTypeAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final int id = mDataset.get(position).getPlace_id();
        TextView place_title = holder.LinearView.findViewById(R.id.place_title);
        place_title.setText(mDataset.get(position).getPlace_name());

        TextView place_description = holder.LinearView.findViewById(R.id.place_description);
        place_description.setText(mDataset.get(position).getPlace_description());

        TextView place_ratings = holder.LinearView.findViewById(R.id.place_ratings);
        place_ratings.setText(mDataset.get(position).getPlace_ratings()+"");

        place_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),PlaceDetailsActivity.class);
                intent.putExtra("place_id",id);
                v.getContext().startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
