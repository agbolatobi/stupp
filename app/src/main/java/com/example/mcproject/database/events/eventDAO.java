package com.example.mcproject.database.events;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
//This is the events DAO with all the queries to be performed on the Database entity
@Dao
public interface eventDAO {
    @Query("SELECT * FROM event")
    List<event> getAll();

    @Query("SELECT * FROM event WHERE event_id IN (:eventids)")
    List<event> loadAllByIds(int[] eventids);

    @Query("SELECT * FROM event WHERE event_id = :eventids")
    event loadById(int eventids);

    @Query("SELECT * FROM event WHERE event_name LIKE :event_name")
    event findByName(String event_name);

    @Query("SELECT * FROM event WHERE event_Description LIKE :event_Description")
    event findByDescription(String event_Description);

    @Query("SELECT * FROM event WHERE event_time = :event_time")
    event findByTime(String event_time);

    @Query("SELECT * FROM event WHERE event_date = :event_date")
    event findByDate(String event_date);

    @Query("UPDATE event set isAttending = :attending_status where event_id = :event_id")
    int setAttendingStatus(int event_id,boolean attending_status);

    @Insert
    void insertAll(event... events);

    @Delete
    void delete(event events);
}
