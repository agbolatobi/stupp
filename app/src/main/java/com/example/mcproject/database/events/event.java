package com.example.mcproject.database.events;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
//Class is the Event entity with the setters and Getters
@Entity
public class event {
    @PrimaryKey(autoGenerate = true)
    public int event_id;

    public String event_name;
    public int place_id;
    public int year;
    public  int month;
    public  int day;
    public int hour;
    public  int min;
    public  int second;
    public String event_time;
    public String event_date;
    public String event_Description;
    public int event_Banner_link;
    public boolean isAttending;

    public int getEvent_id() {
        return event_id;
    }

    public int getEvent_Banner_link() {
        return event_Banner_link;
    }

    public String getEvent_Description() {
        return event_Description;
    }

    public String getEvent_name() {
        return event_name;
    }

    public String getEvent_time() {
        return event_time;
    }

    public int getPlace_id() {
        return place_id;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_Banner_link(int event_Banner_link) {
        this.event_Banner_link = event_Banner_link;
    }

    public void setEvent_Description(String event_Description) {
        this.event_Description = event_Description;
    }

    public void setAttending(boolean attending) {
        isAttending = attending;
    }

    public boolean isAttending() {
        return isAttending;
    }

    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public void setEvent_time(String event_time) {
        this.event_time = event_time;
    }

    public void setPlace_id(int place_id) {
        this.place_id = place_id;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    public int getDay() {
        return day;
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }

    public int getMonth() {
        return month;
    }

    public int getSecond() {
        return second;
    }

    public int getYear() {
        return year;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    public void setYear(int year) {
        this.year = year;
    }


}
