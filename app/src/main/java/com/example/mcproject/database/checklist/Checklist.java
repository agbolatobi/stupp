package com.example.mcproject.database.checklist;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
//Class is the CheckList entity with the setters and Getters
@Entity
public class Checklist {
    @PrimaryKey(autoGenerate = true)
    public int check_list_id;
    @ColumnInfo(name = "check_list_item")
    public String check_list_item;
    @ColumnInfo(name = "check_list_location")
    public String check_list_location;
    @ColumnInfo(name = "check_list_status")
    public String check_list_status;

    public void setCheck_list_id(int check_list_id) {
        this.check_list_id = check_list_id;
    }

    public String getCheck_list_item() {
        return check_list_item;
    }

    public int getCheck_list_id() {
        return check_list_id;
    }

    public String getCheck_list_location() {
        return check_list_location;
    }

    public String getCheck_list_status() {
        return check_list_status;
    }

    public void setCheck_list_item(String check_list_item) {
        this.check_list_item = check_list_item;
    }

    public void setCheck_list_location(String check_list_location) {
        this.check_list_location = check_list_location;
    }

    public void setCheck_list_status(String check_list_status) {
        this.check_list_status = check_list_status;
    }
}

