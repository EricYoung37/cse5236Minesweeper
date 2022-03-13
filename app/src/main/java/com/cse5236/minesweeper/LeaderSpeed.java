package com.cse5236.minesweeper;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LeaderSpeed extends Fragment {

    View view;
    RecyclerView recyclerView;
    LeaderSpeedAdapter leaderSpeedAdapter;
    DAOPlayer daoPlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_leader_speed, container, false);

        recyclerView = view.findViewById(R.id.leaderboard_list);
        leaderSpeedAdapter = new LeaderSpeedAdapter(new ArrayList<Player>());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(leaderSpeedAdapter);
        daoPlayer = new DAOPlayer();

        loadDateFromFirebase();

        Log.d("LeaderSpeedFrag", "onCreateView called!!!");
        return view;
    }

    private void loadDateFromFirebase() {
        daoPlayer.getAll().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<Player> players = new ArrayList<Player>();
                for(DataSnapshot data : snapshot.getChildren()){
                    Player player = data.getValue(Player.class);
                    players.add(player);
                }
                leaderSpeedAdapter.setItems(players);
                leaderSpeedAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

//    @Override
//    public void onDestroy()
//    {
//        super.onDestroy();
//        Log.d("LeaderSpeedFrag", "onDestroy called!!!");
//    }
//
//    @Override
//    public void onResume()
//    {
//        super.onResume();
//        Log.d("LeaderSpeedFrag", "onResume called!!!");
//    }
//
//    @Override
//    public void onStop()
//    {
//        super.onStop();
//        Log.d("LeaderSpeedFrag", "onStop called!!!");
//    }
//
//    @Override
//    public void onPause()
//    {
//        super.onPause();
//        Log.d("LeaderSpeedFrag", "onPause called!!!");
//    }
}