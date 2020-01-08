package com.example.mcproject;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mcproject.database.checklist.Checklist;
import com.example.mcproject.database.database;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class CheckListAdapter extends RecyclerView.Adapter<CheckListAdapter.MyViewHolder> {

    private List<Checklist> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public LinearLayout LinearView;

        public MyViewHolder(LinearLayout v) {
            super(v);
            LinearView = v;
        }
    }

    public CheckListAdapter(List<Checklist> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public CheckListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.check_list_linear_view, parent, false);
        CheckListAdapter.MyViewHolder vh = new CheckListAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(CheckListAdapter.MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        TextView text_view = holder.LinearView.findViewById(R.id.check_list_text_view);
        final ImageView check_button = holder.LinearView.findViewById(R.id.check_list_button);
        if(mDataset.get(position).check_list_status.equals("Not Done")){
           check_button.setImageResource(R.drawable.ic_exclamation_mark);
        }
        if(mDataset.get(position).check_list_status.equals("Done")){
            check_button.setImageResource(R.drawable.ic_checked);
        }
        check_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mDataset.get(position).getCheck_list_status().equals("Not Done")){
                    mDataset.get(position).setCheck_list_status("Done");
                    database.getAppDatabase(v.getContext()).checklistDAO().setItemAsDone(mDataset.get(position).check_list_id);
                    check_button.setImageResource(R.drawable.ic_checked);
                }else{
                    mDataset.get(position).setCheck_list_status("Not Done");
                    database.getAppDatabase(v.getContext()).checklistDAO().setItemAsNotDone(mDataset.get(position).check_list_id);
                    check_button.setImageResource(R.drawable.ic_exclamation_mark);
                }

            }
        });
        text_view.setText(mDataset.get(position).check_list_item);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
