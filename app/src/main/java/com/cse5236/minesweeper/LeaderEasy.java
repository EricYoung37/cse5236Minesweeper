package com.cse5236.minesweeper;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LeaderEasy extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_leader_easy, container, false);
        Log.d("LeaderScoreFrag", "onCreateView called!!!");
        return view;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d("LeaderScoreFrag", "onDestroy called!!!");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.d("LeaderScoreFrag", "onResume called!!!");
    }

    @Override
    public void onStop()
    {
        super.onStop();
        Log.d("LeaderScoreFrag", "onStop called!!!");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d("LeaderScoreFrag", "onPause called!!!");
    }
}