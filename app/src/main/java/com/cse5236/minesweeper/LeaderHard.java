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
    LeaderHardAdapter leaderHardAdapter;
    private LeaderHardViewModel leaderHardViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_leader_hard, container, false);

        /* Create ViewModel */
        leaderHardViewModel = new ViewModelProvider(this).get(LeaderHardViewModel.class);
        leaderHardViewModel.init();

        /* Observe changes to this ViewModel */
        leaderHardViewModel.getPlayers().observe(getViewLifecycleOwner(), new Observer<ArrayList<Player>>() {
            @Override
            public void onChanged(ArrayList<Player> players) {
                leaderHardAdapter.notifyDataSetChanged();
            }
        });

        recyclerView = view.findViewById(R.id.leaderboard_list);
        leaderHardAdapter = new LeaderHardAdapter(getContext(), leaderHardViewModel.getPlayers().getValue());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(leaderHardAdapter);
        
        Log.d("LeaderHardFrag", "onCreateView called");
        return view;
    }

//    @Override
//    public void onDestroy()
//    {
//        super.onDestroy();
//        Log.d("LeaderHardFrag", "onDestroy called");
//    }
//
//    @Override
//    public void onResume()
//    {
//        super.onResume();
//        Log.d("LeaderHardFrag", "onResume called");
//    }
//
//    @Override
//    public void onStop()
//    {
//        super.onStop();
//        Log.d("LeaderHardFrag", "onStop called");
//    }
//
//    @Override
//    public void onPause()
//    {
//        super.onPause();
//        Log.d("LeaderHardFrag", "onPause called");
//    }
}