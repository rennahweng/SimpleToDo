package com.example.simpletodo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        // use layout inflator to inflate a view
        // 1. grab context from viewholder(parent)
        // 2. inflate it as android's simple-list-item
        View todoView = LayoutInflater.from( viewGroup.getContext() )
                .inflate( android.R.layout.simple_list_item_1, viewGroup, false );
        // wrap it inside a view holder and return
        return new ViewHolder(todoView);
    }

    @Override
    // responsible for binding data to a particular viewholder
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        // Grab item at the position
        String item = items.get(position);
        // bind item into specified viewholder
        viewHolder.bind(item);
    }

    @Override
    // Return number of available items in list
    public int getItemCount() {
        return items.size();
    }

    /*
     * Container to provide easy access to views that represent each row of the list
     */
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        // update the view inside of the viewholder with the item parameter
        public void bind(String item) {
            tvItem.setText(item);
        }
    }
}
