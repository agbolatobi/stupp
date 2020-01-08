package com.example.mcproject;

import android.content.Intent;
import android.net.Uri;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.TreeMap;

public class MobileRecommenderAppAdapter extends RecyclerView.Adapter<MobileRecommenderAppAdapter.MyViewHolder> {
    private TreeMap<String,String> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public MyViewHolder(TextView v) {
            super(v);
            textView = v;
        }
    }

    public MobileRecommenderAppAdapter(TreeMap<String,String> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public MobileRecommenderAppAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_text_view, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final String key = mDataset.keySet().toArray()[position].toString();
        holder.textView.setText(mDataset.keySet().toArray()[position].toString());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(mDataset.get(key)));
                v.getContext().startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
