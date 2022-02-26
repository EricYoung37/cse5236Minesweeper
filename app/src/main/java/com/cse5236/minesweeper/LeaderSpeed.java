package com.cse5236.minesweeper;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LeaderSpeed extends Fragment {

    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_leader_speed, container, false);
        Log.d("LeaderSpeedFrag", "onCreateView called!!!");
        return view;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d("LeaderSpeedFrag", "onDestroy called!!!");
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Log.d("LeaderSpeedFrag", "onResume called!!!");
    }

    @Override
    public void onStop()
    {
        super.onStop();
        Log.d("LeaderSpeedFrag", "onStop called!!!");
    }

    @Override
    public void onPause()
    {
        super.onPause();
        Log.d("LeaderSpeedFrag", "onPause called!!!");
    }
}