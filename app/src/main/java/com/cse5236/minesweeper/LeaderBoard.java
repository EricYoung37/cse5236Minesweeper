package com.cse5236.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LeaderBoard extends AppCompatActivity {

    Button leaderEasyBtn, leaderHardBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        replaceFragment(new LeaderHard()); // Default fragment for this activity

        leaderEasyBtn = findViewById(R.id.leader_easy);
        leaderHardBtn = findViewById(R.id.leader_hard);

        leaderEasyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                replaceFragment(new LeaderEasy());

            }
        });

        leaderHardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                replaceFragment(new LeaderHard());

            }
        });


    }

    private void replaceFragment(Fragment newFrag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.leader_board_frag, newFrag);
        fragmentTransaction.commit();
    }
}