package com.example.mcproject.database.toDoList;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
//Class is the Do_to_list entity with the setters and Getters
@Entity
public class to_do_list {
    @PrimaryKey(autoGenerate = true)
    public int to_do_list_id;
    public String to_do_list_item;
    public String to_do_list_source_type;
    public int to_do_list_source_id;

    public int getTo_do_list_id() {
        return to_do_list_id;
    }

    public int getTo_do_list_source_id() {
        return to_do_list_source_id;
    }

    public String getTo_do_list_item() {
        return to_do_list_item;
    }

    public String getTo_do_list_source_type() {
        return to_do_list_source_type;
    }

    public void setTo_do_list_id(int to_do_list_id) {
        this.to_do_list_id = to_do_list_id;
    }

    public void setTo_do_list_item(String to_do_list_item) {
        this.to_do_list_item = to_do_list_item;
    }

    public void setTo_do_list_source_id(int to_do_list_source_id) {
        this.to_do_list_source_id = to_do_list_source_id;
    }

    public void setTo_do_list_source_type(String to_do_list_source_type) {
        this.to_do_list_source_type = to_do_list_source_type;
    }
}
