package com.cse5236.minesweeper;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LeaderHardRepository {
//    public String TAG = "PlayerRepository";

    private static LeaderHardRepository instance;
    private ArrayList<Player> dataSet = new ArrayList<>();
    private DAOHard daoHard = new DAOHard();

    /* Singleton Pattern */
    public static LeaderHardRepository getInstance() {
        if(instance == null) {
            instance = new LeaderHardRepository();
        }
        return instance;
    }

    /* Retrieve remote data and put it in dataSet */
    public void setPlayers(MutableLiveData<ArrayList<Player>> data) {
        daoHard.getAll().addListenerForSingleValueEvent(new ValueEventListener() {
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
                data.setValue(dataSet);
                /* Calling setValue on LiveData so that active observers are notified
                * of the change of the data set.
                * Without this call the main thread may run RecyclerView adapter before
                * the Firebase query. This results in empty RecyclerView because no
                * data has been loaded to the RecyclerView yet.
                * In short, the call here ensures the change of the asynchronous Firebase
                * query can be captured by the main thread. */
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    /* Get data from the database and prepare it for the ViewModel */
    public MutableLiveData<ArrayList<Player>> getPlayers() {
        MutableLiveData<ArrayList<Player>> data = new MutableLiveData<>();

        setPlayers(data);
        /* data.setValue(dataSet) is called again to counter the concurrency problem caused by
        * the asynchronous Firebase query.
        * The Firebase query is slower than the main thread. Even if data has been set with
        * dataSet in onDataChange, it is not returned by the time the getItemsCount is called
        * in the RecyclerView adapter. This means getItemCount sees a null data, while the value
        * is still on its way. Therefore, to synchronize the size of the data set, setValue has
        * to be called again on data.
        * I know this might not be a good approach, but I could not find any other solution
        * even if I spent an entire day trying different suggestions I found online. This solution
        * was an original one by myself . */
        data.setValue(dataSet);
        return data;
    }



}
