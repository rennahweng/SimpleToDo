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

    // Implement an interface for updating item in rv
    public interface OnClickListener {
        void onItemClicked(int position);
    }

    // Implement an interface for removing item from rv
    public interface OnLongClickListener {
        void onItemLongClicked(int position);
    }

    List<String> items;
    OnLongClickListener longClickListener;
    OnClickListener clickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener longClickListener, OnClickListener clickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    // responsible for creating each view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        // use layout inflator to inflate a view
        // 1. grab context from viewholder(parent)
        // 2. inflate it as android's simple-list-item
        View todoView = LayoutInflater.from( viewGroup.getContext() )
                .inflate( android.R.layout.select_dialog_multichoice, viewGroup, false );
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
            // update feature:
            tvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClicked( getAdapterPosition() );
                }
            });
            tvItem.setOnLongClickListener( new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    // Remove the item from recycler view:
                    // we need to communicate an item to the adapter behind the rv
                    // item should be passed from main activity to adapter

                    // notify the listener which position was long pressed
                    longClickListener.onItemLongClicked( getAdapterPosition() );
                    return true;
                }
            });
        }
    }
}
