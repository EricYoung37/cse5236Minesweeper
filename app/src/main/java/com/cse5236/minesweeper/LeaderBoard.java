package com.cse5236.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LeaderBoard extends AppCompatActivity {

    private int mode;
    Button leaderEasyBtn, leaderHardBtn;
    LeaderBoardViewModel leaderBoardViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);

        leaderBoardViewModel = new ViewModelProvider(this).get(LeaderBoardViewModel.class);

        mode = leaderBoardViewModel.getMode();
        if(mode == 0)
            replaceFragment(new LeaderEasy()); // Default fragment for this activity
        else
            replaceFragment(new LeaderHard());

        leaderEasyBtn = findViewById(R.id.leader_easy);
        leaderHardBtn = findViewById(R.id.leader_hard);

        leaderEasyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                leaderBoardViewModel.saveMode(0);
                replaceFragment(new LeaderEasy());

            }
        });

        leaderHardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                leaderBoardViewModel.saveMode(1);
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