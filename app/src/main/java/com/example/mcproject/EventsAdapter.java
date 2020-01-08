package com.example.mcproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import java.util.Calendar;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mcproject.database.database;
import com.example.mcproject.database.events.event;
import com.example.mcproject.database.places.Places;
import com.example.mcproject.database.toDoList.to_do_list;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.MyViewHolder> {
    private List<event> mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public LinearLayout LinearView;

        public MyViewHolder(LinearLayout v) {
            super(v);
            LinearView = v;
        }
    }

    public EventsAdapter(List<event> myDataset) {
        mDataset = myDataset;
    }

    @Override
    public EventsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.event_view, parent, false);
        EventsAdapter.MyViewHolder vh = new EventsAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(EventsAdapter.MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        TextView title = holder.LinearView.findViewById(R.id.event_title);
        TextView description = holder.LinearView.findViewById(R.id.event_description);
        TextView date_time = holder.LinearView.findViewById(R.id.event_date_time);
        TextView venue = holder.LinearView.findViewById(R.id.event_venue);
        final Button attend = holder.LinearView.findViewById(R.id.event_attend);
        Button navigate = holder.LinearView.findViewById(R.id.event_navigate);
        final int event_id = mDataset.get(position).getEvent_id();
        title.setText(mDataset.get(position).getEvent_name());
        description.setText(mDataset.get(position).getEvent_Description());
        date_time.setText(mDataset.get(position).getEvent_date()+" "+mDataset.get(position).getEvent_time());
        final Places place = database.getAppDatabase(holder.LinearView.getContext()).placeDAO().loadById(mDataset.get(position).getPlace_id());
        venue.setText(place.getPlace_name());
        if(mDataset.get(position).isAttending()){
            attend.setText("Cancel");
        }else{
            attend.setText("Attend");

        }

        navigate.setText("Navigate To Event");
        navigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),CampusMapActivity.class);
                intent.putExtra("place_id",place.place_id);
                v.getContext().startActivity(intent);
            }
        });

        attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDataset.get(position).setAttending(!mDataset.get(position).isAttending());
                String event_to_do_list = mDataset.get(position).event_name+" "+mDataset.get(position).getEvent_date()+" "+mDataset.get(position).getEvent_time();
                database.getAppDatabase(v.getContext()).eventDAO().setAttendingStatus(event_id,mDataset.get(position).isAttending());
                if(mDataset.get(position).isAttending()){
                    attend.setText("Cancel");
                    to_do_list item = new to_do_list();
                    item.setTo_do_list_item(event_to_do_list);
                    item.setTo_do_list_source_id(mDataset.get(position).event_id);
                    item.setTo_do_list_source_type("event");
                    database.getAppDatabase(v.getContext()).to_do_listDAO().insertAll(item);
                        Calendar beginTime = Calendar.getInstance();
                        beginTime.set(mDataset.get(position).getYear(), mDataset.get(position).getMonth(), mDataset.get(position).getDay(), mDataset.get(position).getHour(), mDataset.get(position).getMin());
                        Calendar endTime = Calendar.getInstance();
                        endTime.set(mDataset.get(position).getYear(), mDataset.get(position).getMonth(), mDataset.get(position).getDay(), mDataset.get(position).getHour()+1, mDataset.get(position).getMin());
                        Intent intent = new Intent(Intent.ACTION_INSERT)
                                .setData(CalendarContract.Events.CONTENT_URI)
                                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                                .putExtra(CalendarContract.Events.TITLE, mDataset.get(position).getEvent_name())
                                .putExtra(CalendarContract.Events.DESCRIPTION, mDataset.get(position).getEvent_Description())
                                .putExtra(CalendarContract.Events.EVENT_LOCATION, place.place_name)
                                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);
                        v.getContext().startActivity(intent);

                }else{
                    attend.setText("Attend");
                    to_do_list item = database.getAppDatabase(v.getContext()).to_do_listDAO().findByitem(event_to_do_list);
                    if(item != null){
                        if(item.getTo_do_list_source_type().equals("event")){
                            database.getAppDatabase(v.getContext()).to_do_listDAO().delete(item);
                        }

                    }

                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
