package com.example.simpletodo;

import android.content.ClipData;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    // define key for edit activity

    public static final String KEY_ITEM_TEXT = "item_text";
    public static final String KEY_ITEM_POS = "item_pos";
    public static final int EDIT_TEXT_CODE = 20;

    // Define a list of strings as our model items
    List<String> items;

    // Create handlers to link with UI
    Button addBtn;
    EditText editItem;
    RecyclerView rvItems;

    ItemsAdapter itemsAdapter;

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
        loadItems(); // load from file system to into items


        // Mock items:
        /*
        // items = new ArrayList<>();
        items.add("Finish Codepath android pre-work");
        items.add("Revise resume for virtual career fair");
        items.add("Watch AWS practitioner videos on udemy");
        items.add("Update Linkedin profile");
        items.add("Thank Codepaths for this amazing opportunity to jumpstart my career!");
        */

        /*
         * Implement REMOVE actions: remove items using onLongClickListener
         */
        ItemsAdapter.OnLongClickListener longClickListener = new ItemsAdapter.OnLongClickListener() {
            @Override
            public void onItemLongClicked(int position) {
                // delete item from the model
                items.remove(position);
                // notify the adapter so it also deletes from rv
                itemsAdapter.notifyItemRemoved(position);
                // inform user using a pop-up
                Toast.makeText(getApplicationContext(),
                        "An item was removed!",
                        Toast.LENGTH_SHORT).show(); // length_short = short duration

                // update change in items in file system
                saveItems();
            }
        };

        /*
         * Implement UPDATE actions: update item using onClickListener
         */
        ItemsAdapter.OnClickListener onClickListener = new ItemsAdapter.OnClickListener() {
            private Object MainActivity;

            @Override
            public void onItemClicked(int position) {
                Log.d("MainActivity", "Single click at position " + position);
                // create the new activity
                Intent i = new Intent(MainActivity.this, EditActivity.class);
                // pass data being edited
                i.putExtra(KEY_ITEM_TEXT, items.get(position));
                i.putExtra(KEY_ITEM_POS, position);
                // display activity
                startActivityForResult(i, EDIT_TEXT_CODE);
            }
        };

        // Adapter - handle data collection and bind to view
        itemsAdapter = new ItemsAdapter(items, longClickListener, onClickListener);
        rvItems.setAdapter(itemsAdapter);
        // LayoutManager - positioning items
        rvItems.setLayoutManager(new LinearLayoutManager(this));
        // ItemAnimator - animating items for common operations like add/remove

        /*
         * Implement ADD actions: add items using add button
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

                // Add a pop-up(Toast) to let user know the item is added successfully
                Toast.makeText(getApplicationContext(),
                        "New item added successfully!",
                        Toast.LENGTH_SHORT).show();

                // save all added items in file system
                saveItems();
            }
        });
    }


    /*
     * Implement persistence: store model changes to file system
     */
    private File getDataFile() {
        return new File( getFilesDir(), "todoItems.txt" );
        // getFilesDir() - returns directory of this app
    }

    /*
     * Load items by reading every line of the todoItems file
     */
    private void loadItems() {
        try {
            items = new ArrayList<>( FileUtils.readLines(getDataFile(), Charset.defaultCharset()) );
        } catch (IOException e) {
            // e.printStackTrace();
            Log.e("MainActivity", "Error reading items in loadItems()");
            items = new ArrayList<>();
        }
    }

    /*
     * Save items by writing into the todoItems file
     */
    private void saveItems() {
        try {
            FileUtils.writeLines(getDataFile(), items);
        } catch (IOException e) {
            // e.printStackTrace();
            Log.e("MainActivity", "Error writing items in saveItems()");
        }
    }

    /*
     * Implement Update: handle the result of the intent from EditActivity
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // check if result code is OK & if request code matches with the edit text code we set up
        if (resultCode == RESULT_OK && requestCode == EDIT_TEXT_CODE) {
            // retrieve the updated text
            String itemText = data.getStringExtra(KEY_ITEM_TEXT);
            // extract the original position of the edited item from position keys
            int position = data.getExtras().getInt(KEY_ITEM_POS);
            // update the model with edited item text
            items.set(position, itemText);
            // notify adapter
            itemsAdapter.notifyItemChanged(position);
            // persist the changes
            saveItems();
            // pop-up to inform user
            Toast.makeText(getApplicationContext(), "Item updated successfully!", Toast.LENGTH_SHORT).show();
        } else {
            // log a warning if either request code or result code doesn't match
            Log.w("MainActivity", "Unknown call to onActivityResult");
        }
    }
}