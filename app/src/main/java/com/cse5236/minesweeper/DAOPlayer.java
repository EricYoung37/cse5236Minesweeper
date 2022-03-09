package com.cse5236.minesweeper;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOPlayer {

    private DatabaseReference databaseReference;
    public DAOPlayer()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Player.class.getSimpleName());
    }

    public Task<Void> add(Player p){

        return databaseReference.push().setPriority(p);
    }
}
