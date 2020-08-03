package com.example.simpletodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Define a list of strings as our model items
    List<String> items;

    // Create handlers to link with UI
    Button addBtn;
    EditText editItem;
    RecyclerView viewItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Link main activity with UI view
        addBtn = findViewById(R.id.addBtn);
        editItem = findViewById(R.id.editItem);
        viewItems = findViewById(R.id.viewItems);

        /*
         * Each view has different functions
         */
        // EditText object:
        editItem.setText("Doing this from java!");

        // RecyclerView object:
        /*
         * .Adapter - handle data collection and bind to view
         * LayoutManager - positioning items
         * ItemAnimator - animating items for common operations like add/remove
         */




        // Define a model class to use as data source for recyclerview
        items = new ArrayList<>();
        items.add("Finish Codepath pre-work for android class");
        items.add("Revise resume for virtual career fair");
        items.add("Watch AWS practitioner videos on udemy");
        items.add("Update Linkedin profile");
        items.add("Thanks Codepath for this amazing opportunity to jumpstart my career!");
    }
}