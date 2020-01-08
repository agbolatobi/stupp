package com.example.mcproject;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mcproject.database.database;
import com.example.mcproject.database.toDoList.to_do_list;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ToDoListActivity extends AppCompatActivity {
    public RecyclerView recyclerView;
    public RecyclerView.Adapter mAdapter;
    public RecyclerView.LayoutManager layoutManager;

    //this method initializes all the variable and call the on create methods for this activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.to_do_list_activity);
        getSupportActionBar().setTitle("STUDENT TO DO LIST");

        List<to_do_list> Data = database.getAppDatabase(getApplicationContext()).to_do_listDAO().getAll();
        Log.d("Data_list_COUNT",Data.size()+"");
        recyclerView = (RecyclerView) findViewById(R.id.to_do_list_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ToDoListAdapter(Data);
        recyclerView.setAdapter(mAdapter);

        ImageView insert = findViewById(R.id.insert_to_do_list);
        final EditText textView= findViewById(R.id.to_do_list_edit);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(v.getContext(), "Empty Text Input", Toast.LENGTH_SHORT);
                if(textView !=  null){
                    String input = textView.getText().toString();
                    if(input.length() != 0){
                        to_do_list item = new to_do_list();
                        item.setTo_do_list_item(input);
                        item.setTo_do_list_source_id(0);
                        item.setTo_do_list_source_type("User Generated");
                        database.getAppDatabase(v.getContext()).to_do_listDAO().insertAll(item);
                        textView.setText("");
                        Toast success_toast = Toast.makeText(v.getContext(), "Insertion Complete", Toast.LENGTH_SHORT);
                        success_toast.show();
                        finish();
                        startActivity(getIntent());
                    }else{

                        toast.show();

                    }
                }else{
                    toast.show();
                }

            }
        });
    }
}
