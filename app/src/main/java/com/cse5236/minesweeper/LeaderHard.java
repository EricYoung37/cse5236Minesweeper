package com.cse5236.minesweeper;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class LeaderHard extends Fragment {

    View view;
    RecyclerView recyclerView;
    LeaderHardAdapter leaderSpeedAdapter;
//    DAOPlayer daoPlayer; // Version without VM & Repo
    private LeaderHardViewModel leaderHardViewModel; // Version with VM & Repo

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_leader_hard, container, false);

        /* Create ViewModel */
        // Version with VM & Repo
        leaderHardViewModel = new ViewModelProvider(this).get(LeaderHardViewModel.class);
        leaderHardViewModel.init();

        /* Observe changes to this ViewModel */
        // Version with VM & Repo
        leaderHardViewModel.getPlayers().observe(getViewLifecycleOwner(), new Observer<ArrayList<Player>>() {
            @Override
            public void onChanged(ArrayList<Player> players) {
                leaderSpeedAdapter.notifyDataSetChanged();
            }
        });

        recyclerView = view.findViewById(R.id.leaderboard_list);
        leaderSpeedAdapter = new LeaderHardAdapter(getContext(), leaderHardViewModel.getPlayers().getValue()); // Version with VM & Repo
//        leaderSpeedAdapter = new LeaderSpeedAdapter(getContext(), new ArrayList<Player>()); // Version without VM & Repo
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(leaderSpeedAdapter);

//        daoPlayer = new DAOPlayer(); // Version without VM & Repo
//        loadDateFromFirebase(); // Version without VM & Repo
        
        Log.d("LeaderSpeedFrag", "onCreateView called!!!");
        return view;
    }

//    // Version without VM & Repo
//    private void loadDateFromFirebase() {
//        daoPlayer.getAll().addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                ArrayList<Player> players = new ArrayList<Player>();
//                for(DataSnapshot data : snapshot.getChildren()){
//                    Player player = data.getValue(Player.class);
//                    players.add(player);
//                }
//                leaderSpeedAdapter.setItems(players);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }


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