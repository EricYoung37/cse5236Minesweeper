package com.cse5236.minesweeper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LeaderSpeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    ArrayList<Player> list = new ArrayList<>();
    public void setItems(ArrayList<Player> plist){
        list.addAll(plist);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_leaderboard, parent, false);
        return new PlayerVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PlayerVH viewholder = (PlayerVH) holder;
        Player p = list.get(position);
        viewholder.txt_name.setText(p.getName());
        viewholder.txt_time.setText(p.getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    /* Nested class player ViewHolder */
    public class PlayerVH extends RecyclerView.ViewHolder {
        public TextView txt_name, txt_time;

        public PlayerVH(@NonNull View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_time = itemView.findViewById(R.id.txt_time);
        }
    }
}
