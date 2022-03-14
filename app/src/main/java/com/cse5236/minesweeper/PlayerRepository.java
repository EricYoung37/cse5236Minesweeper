package com.cse5236.minesweeper;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PlayerRepository {

    private static PlayerRepository instance;
    private ArrayList<Player> dataSet = new ArrayList<>();
    private DAOPlayer daoPlayer = new DAOPlayer();

    /* Singleton Pattern */
    public static PlayerRepository getInstance() {
        if(instance == null) {
            instance = new PlayerRepository();
        }
        return instance;
    }

    /* Get data from the database and prepare it for the ViewModel */
    public MutableLiveData<ArrayList<Player>> getPlayers() {
        setPlayers();

        MutableLiveData<ArrayList<Player>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    /* Retrieve remote data and put it in dataSet */
    public void setPlayers() {
        daoPlayer.getAll().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                /* This clear() method is used to solve the problem
                * that the items in RecyclerView are duplicated every time
                * the fragment for the RecyclerView is loaded.
                * I know this is probably not a very good approach, but
                * I'm afraid this is the only solution I can find now. */
                dataSet.clear();
                for(DataSnapshot data : snapshot.getChildren()){
                    Player player = data.getValue(Player.class);
                    dataSet.add(player);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
