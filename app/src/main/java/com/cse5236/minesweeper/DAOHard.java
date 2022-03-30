package com.cse5236.minesweeper;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class DAOHard {

    private DatabaseReference tableRef; // Reference for the entire 'Player' table

    public DAOHard()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        tableRef = db.getReference("LeaderBoard/Hard");
    }

    /* Create or Update a record depending on the existence of the player name */
    public void submit(Player p) {

        /* orderByChild("name") only means you get a copy of data ordered by name but not ordering the original data */
        tableRef.orderByChild("name").equalTo(p.getName()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    /* If name exists, update */
                    for (DataSnapshot child : snapshot.getChildren()) {
                        String key = child.getKey();
                        tableRef.child(key).setValue(p);
                    }
                } else {
                    /* If name does not exist, create a new node */
                    tableRef.push().setValue(p);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void delete(Player p) {

        /* orderByChild("name") only means you get a copy of data ordered by name but not ordering the original data */
        tableRef.orderByChild("name").equalTo(p.getName()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    /* If name exists, update */
                    for (DataSnapshot child : snapshot.getChildren()) {
                        String key = child.getKey();
                        tableRef.child(key).removeValue();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    /* Read all */
    public Query getAll(){
        return tableRef.orderByChild("time");
    }

}
