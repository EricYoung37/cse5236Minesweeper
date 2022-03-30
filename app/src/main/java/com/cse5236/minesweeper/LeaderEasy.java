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

public class LeaderEasy extends Fragment {

    View view;
    RecyclerView recyclerView;
    LeaderEasyAdapter leaderEasyAdapter;
    private LeaderEasyViewModel leaderEasyViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_leader_easy, container, false);

        leaderEasyViewModel = new ViewModelProvider(this).get(LeaderEasyViewModel.class);
        leaderEasyViewModel.init();

        leaderEasyViewModel.getPlayers().observe(getViewLifecycleOwner(), new Observer<ArrayList<Player>>() {
            @Override
            public void onChanged(ArrayList<Player> players) {
                leaderEasyAdapter.notifyDataSetChanged();
            }
        });

        recyclerView = view.findViewById(R.id.leaderboard_list);
        leaderEasyAdapter = new LeaderEasyAdapter(getContext(), leaderEasyViewModel.getPlayers().getValue());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(leaderEasyAdapter);

        Log.d("LeaderEasyFrag", "onCreateView called");
        return view;
    }

//    @Override
//    public void onDestroy()
//    {
//        super.onDestroy();
//        Log.d("LeaderEasyFrag", "onDestroy called");
//    }
//
//    @Override
//    public void onResume()
//    {
//        super.onResume();
//        Log.d("LeaderEasyFrag", "onResume called");
//    }
//
//    @Override
//    public void onStop()
//    {
//        super.onStop();
//        Log.d("LeaderEasyFrag", "onStop called");
//    }
//
//    @Override
//    public void onPause()
//    {
//        super.onPause();
//        Log.d("LeaderEasyFrag", "onPause called");
//    }
}