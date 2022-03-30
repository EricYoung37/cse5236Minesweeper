package com.cse5236.minesweeper;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class LeaderHardViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Player>> players;
    private PlayerRepository playerRepo;

    public void init() {
        if(players != null) {
            return;
        }
        playerRepo = PlayerRepository.getInstance(); // Repo instance
        players = playerRepo.getPlayers(); // Retrieve LiveData from the repo instance
    }

    public MutableLiveData<ArrayList<Player>> getPlayers() {
        return players;
    }
}
