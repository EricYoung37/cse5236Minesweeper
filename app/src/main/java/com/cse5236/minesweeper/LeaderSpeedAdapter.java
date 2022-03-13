package com.cse5236.minesweeper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LeaderSpeedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    ArrayList<Player> list;

    public LeaderSpeedAdapter(ArrayList<Player> plist){
        this.list = plist;
    }

    public void setItems(ArrayList<Player> plist){
        this.list = plist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_leaderboard, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        PlayerViewHolder playerViewHolder = (PlayerViewHolder) holder;
        playerViewHolder.bind(list, position);
        playerViewHolder.setIsRecyclable(false);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    /* Nested class player ViewHolder */
    public class PlayerViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtTime;
        Button deleteBtn;
        DAOPlayer dao = new DAOPlayer();

        public PlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txt_name);
            txtTime = itemView.findViewById(R.id.txt_time);
            deleteBtn = itemView.findViewById(R.id.delete_btn);
        }

        public void bind(ArrayList<Player> list, int position) {
            Player player = list.get(position);

            txtName.setText(player.getName());
            txtTime.setText(player.getTime());

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dao.delete(player);
                    list.remove(position);
                    notifyItemRemoved(position);
                }
            });
        }

    }
}
