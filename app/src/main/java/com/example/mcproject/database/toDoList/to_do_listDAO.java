package com.example.mcproject.database.toDoList;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
//This is the to do list DAO with all the queries to be performed on the Database entity
@Dao
public interface to_do_listDAO {
    @Query("SELECT * FROM to_do_list")
    List<to_do_list> getAll();

    @Query("SELECT * FROM to_do_list WHERE to_do_list_id IN (:placeids)")
    List<to_do_list> loadAllByIds(int[] placeids);

    @Query("SELECT * FROM to_do_list WHERE to_do_list_item LIKE :to_do_list_item")
    to_do_list findByitem(String to_do_list_item);

    @Query("SELECT * FROM to_do_list WHERE to_do_list_source_type LIKE :source_type")
    to_do_list findByType(String source_type);

    @Query("SELECT * FROM to_do_list WHERE to_do_list_source_id LIKE :source_id")
    to_do_list findByRatings(String source_id);

    @Insert
    void insertAll(to_do_list... ToDoLists);

    @Delete
    void delete(to_do_list ToDoList);
}
