package com.example.simpletodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditActivity extends AppCompatActivity {

    EditText etItem;
    Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etItem = findViewById(R.id.etItem);
        btnSave = findViewById(R.id.btnSave);

        getSupportActionBar().setTitle("Edit Item");

        // populate etItem text to have the string from MainActivity
        etItem.setText( getIntent().getStringExtra(MainActivity.KEY_ITEM_TEXT) );
        // click listener for save button:
        // When user is done editing, save button is clicked
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // when save is clicked, go back to MainActivity page
                // create intent that contains the result of editing
                Intent i = new Intent();
                // pass data (result of editing)
                i.putExtra(MainActivity.KEY_ITEM_TEXT, etItem.getText().toString());
                i.putExtra(MainActivity.KEY_ITEM_POS, getIntent().getExtras().getInt(MainActivity.KEY_ITEM_POS));
                // set result of intent
                setResult(RESULT_OK, i);
                // finish activity, close EditActivity screen and go back
                finish();
            }
        });
    }
}