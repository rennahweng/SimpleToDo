package com.example.simpletodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // Define a list of strings as our model items
    List<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        items = new ArrayList<>();
        items.add("Finish Codepath pre-work for android class");
        items.add("Revise resume for virtual career fair");
        items.add("Watch AWS practitioner videos on udemy");
        items.add("Update Linkedin profile");
        items.add("Thanks Codepath for this amazing opportunity to jumpstart my career!");
    }
}