package com.example.simpletodo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

// Responsible for displaying data from the model into a row in recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {

    List<String> items;

    public ItemsAdapter(List<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    // responsible for creating each view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

    }

    @Override
    // take data at a position and put it into viewholder
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    // number of available items in viewholder
    public int getItemCount() {
        return 0;
    }

    /*
     * Container to provide easy access to views that represent each row of the list
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
