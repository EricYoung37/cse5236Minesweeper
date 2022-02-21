package com.cse5236.minesweeper;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/* Class for RecyclerView Adapter, responsible for recycling the view of a single cell (item_cell.xml)
* since a grid has so many cells */
public class MineGridRecyclerAdapter extends RecyclerView.Adapter<MineGridRecyclerAdapter.MineTileViewHolder> {
    private List<Cell> cells; /* Each spot in the list will be bound to the view of a cell by the Adapter/ViewHolder */
    private OnCellClickListener listener;

    /* RecyclerView Adapter Constructor */
    public MineGridRecyclerAdapter(List<Cell> cells, OnCellClickListener listener) {
        this.cells = cells;
        this.listener = listener;
    }

    @NonNull /* Means never return null */

    /* ViewHolder holds the view for a cell (item_cell.xml) */
    @Override
    public MineTileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cell, parent, false);
        return new MineTileViewHolder(itemView);
    }

    /* Bind a spot in list to a cell so that item_view knows what to display for this single cell */
    @Override
    public void onBindViewHolder(@NonNull MineTileViewHolder holder, int position) {
        holder.bind(cells.get(position)); /* bind() in MineTileViewHolder is called */
        holder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return cells.size();
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
        notifyDataSetChanged();
    }

    /* Inner class for ViewHolder */
    class MineTileViewHolder extends RecyclerView.ViewHolder {
        TextView valueTextView;

        public MineTileViewHolder(@NonNull View itemView) {
            super(itemView);

            valueTextView = itemView.findViewById(R.id.item_cell_value);
        }

        public void bind(final Cell cell) {
            itemView.setBackgroundColor(Color.GRAY);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.cellClick(cell);
                }
            });

            if (cell.getValue() == Cell.BOMB) {
                valueTextView.setText(R.string.bomb);

            } else if (cell.getValue() == Cell.BLANK) {
                valueTextView.setText("");
                itemView.setBackgroundColor(Color.parseColor("#d3d3d3"));

            } else {
                valueTextView.setText(String.valueOf(cell.getValue()));
                if (cell.getValue() == 1) {
                    valueTextView.setTextColor(Color.BLUE);
                } else if (cell.getValue() == 2) {
                    valueTextView.setTextColor(Color.GREEN);
                } else if (cell.getValue() == 3) {
                    valueTextView.setTextColor(Color.RED);
                }
            }
        }
    }
}