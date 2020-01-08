package com.example.mcproject;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mcproject.database.database;
import com.example.mcproject.database.toDoList.to_do_list;

import java.util.List;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.MyViewHolder> {
    public List<to_do_list> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout LinearView;

        public MyViewHolder(LinearLayout v) {
            super(v);
            LinearView = v;
        }
    }

    public ToDoListAdapter(List<to_do_list> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public ToDoListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.to_do_list_card, parent, false);
        ToDoListAdapter.MyViewHolder vh = new ToDoListAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ToDoListAdapter.MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Log.d("Data_list_COUNT",mDataset.size()+"");
        TextView text_view = holder.LinearView.findViewById(R.id.to_do_list_text_view);
        final ImageView deleteButton = holder.LinearView.findViewById(R.id.to_do_list_done);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.getAppDatabase(v.getContext()).to_do_listDAO().delete(mDataset.get(position));
                removeElement(position);
            }
        });
        text_view.setText(mDataset.get(position).getTo_do_list_item());
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public void removeElement(int position) {
        mDataset.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mDataset.size());
    }

    public void addItem(to_do_list item){
        mDataset.add(item);
        notifyItemInserted(mDataset.size());
        notifyItemRangeChanged(mDataset.size()-1,mDataset.size());
    }
}
