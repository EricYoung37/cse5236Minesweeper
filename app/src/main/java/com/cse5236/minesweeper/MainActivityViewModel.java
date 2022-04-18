package com.cse5236.minesweeper;

import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<MinesweeperGame> minesweeperGameMutableLiveData = new MutableLiveData<MinesweeperGame>();
    private TimerTask timerTask;
    private Double time = 0.0;


    public void saveGame(MinesweeperGame game) {
        minesweeperGameMutableLiveData.setValue(game);
    }

    public boolean savedGameIsEmpty() {
        if (minesweeperGameMutableLiveData.getValue() == null)
            return true;
        return false;
    }

    public MinesweeperGame getCurrentGame() {
        return minesweeperGameMutableLiveData.getValue();
    }


    public void saveTime(Double t) {
        time = t;
    }

    public Double getTime() {
        return time;
    }

}
