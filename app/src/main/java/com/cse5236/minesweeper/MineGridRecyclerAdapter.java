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
    private MinesweeperGame game;

    /* RecyclerView Adapter Constructor */
    public MineGridRecyclerAdapter(List<Cell> cells, OnCellClickListener listener, MinesweeperGame game) {
        this.cells = cells;
        this.listener = listener;
        this.game = game;
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
                    if(!(cell.isFlagged() && !(game.isFlagMode()))) {
                        /* In the mode for opening cells, clicking a flagged cell is not allowed. */
                        listener.cellClick(cell);
                    }
                }
            });

            if(cell.isRevealed()){
                if (cell.getValue() == Cell.BOMB) {
                    valueTextView.setText(R.string.bomb);
                    if(game.isGameWon()) {
                        /* The background color for bombs revealed upon game victory. */
                        itemView.setBackgroundColor(Color.GRAY);
                    } else {
                        /* The background color for bombs revealed upon game failure. */
                        itemView.setBackgroundColor(0xffeb3434);
                    }

                } else if (cell.getValue() == Cell.BLANK) {
                    valueTextView.setText("");
                    itemView.setBackgroundColor(0xffd3d3d3);

                } else {
                    valueTextView.setText(String.valueOf(cell.getValue()));
                    /* Use modulo so that the number can still be colored even if the value is
                    large. For example, even the number is 5, then the color will be GREEN. */
                    if ((cell.getValue() % 3) == 1) {
                        valueTextView.setTextColor(Color.BLUE);
                    } else if ((cell.getValue() % 3) == 2) {
                        valueTextView.setTextColor(Color.GREEN);
                    } else {
                        valueTextView.setTextColor(Color.RED);
                    }
                    itemView.setBackgroundColor(0xffd3d3d3);
                }
            }else if(cell.isFlagged()){
                valueTextView.setText(R.string.flag);
            }

        }
    }
}