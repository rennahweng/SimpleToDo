package com.example.simpletodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Define a list of strings as our model items
    List<String> items;

    // Create handlers to link with UI
    Button addBtn;
    EditText editItem;
    RecyclerView rvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link main activity with UI view
        addBtn = findViewById(R.id.addBtn);
        editItem = findViewById(R.id.editItem);
        rvItems = findViewById(R.id.rvItems);

        /*
         * Each view has different functions
         */
        // EditText object:
        // editItem.setText("Doing this from java!");

        // RecyclerView object:
        /*
         * Rendering items into a recyclerview list
         */

        // Define a model class to use as data source for recyclerview
        items = new ArrayList<>();
        items.add("Finish Codepath pre-work for android class");
        items.add("Revise resume for virtual career fair");
        items.add("Watch AWS practitioner videos on udemy");
        items.add("Update Linkedin profile");
        items.add("Thank Codepaths for this amazing opportunity to jumpstart my career!");

        // Adapter - handle data collection and bind to view
        final ItemsAdapter itemsAdapter = new ItemsAdapter(items);
        rvItems.setAdapter(itemsAdapter);
        // LayoutManager - positioning items
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        // ItemAnimator - animating items for common operations like add/remove

        /*
         * Implement mutation actions: add items using add button
         */
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            // Do something when user clicks on the view
            public void onClick(View view) {
                String todoItem = editItem.getText().toString();
                // add item to model
                items.add(todoItem);
                // modify adapter that an item is inserted
                itemsAdapter.notifyItemInserted(items.size() - 1);
                // clear the edit text once the new item is added
                editItem.setText("");
            }
        });
    }
}