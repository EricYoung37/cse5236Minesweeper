package com.cse5236.minesweeper;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class LeaderEasyViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Player>> players;
    private LeaderEasyRepository playerRepo;

    public void init() {
        if(players != null) {
            return;
        }
        playerRepo = LeaderEasyRepository.getInstance(); // Repo instance
        players = playerRepo.getPlayers(); // Retrieve LiveData from the repo instance
    }

    public MutableLiveData<ArrayList<Player>> getPlayers() {
        return players;
    }
}
