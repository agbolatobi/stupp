package com.example.mcproject.database.checklist;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import java.util.List;
//This is the checklist DAO with all the queries to be performed on the Database entity
@Dao
public interface ChecklistDAO {
    @Query("SELECT * FROM Checklist")
    List<Checklist> getAll();

    @Query("SELECT * FROM Checklist WHERE check_list_id IN (:checklistids)")
    List<Checklist> loadAllByIds(int[] checklistids);

    @Query("SELECT * FROM Checklist WHERE check_list_item LIKE :check_list_item")
    Checklist findByItem(String check_list_item);

    @Insert
    void insertAll(Checklist... check_list_items);

    @Query("UPDATE Checklist set check_list_status = 'Done' where check_list_id = :check_list_id")
    int setItemAsDone(int check_list_id);

    @Query("UPDATE Checklist set check_list_status = 'Not Done' where check_list_id = :check_list_id")
    int setItemAsNotDone(int check_list_id);

    @Delete
    void delete(Checklist user);
}
