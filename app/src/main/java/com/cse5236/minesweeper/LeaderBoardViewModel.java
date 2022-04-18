package com.cse5236.minesweeper;

import androidx.lifecycle.ViewModel;

public class LeaderBoardViewModel extends ViewModel {
    private int mode = 0; // Current leaderboard mode: 0 -> easy , 1 -> hard

    public void saveMode(int m) {
        mode = m;
    }

    public int getMode() {
        return mode;
    }
}
