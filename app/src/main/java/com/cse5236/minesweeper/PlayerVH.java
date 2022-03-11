package com.cse5236.minesweeper;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PlayerVH extends RecyclerView.ViewHolder {
    public TextView txt_name, txt_time;

    public PlayerVH(@NonNull View itemView) {
        super(itemView);
        txt_name = itemView.findViewById(R.id.txt_name);
        txt_time = itemView.findViewById(R.id.txt_time);
    }
}
