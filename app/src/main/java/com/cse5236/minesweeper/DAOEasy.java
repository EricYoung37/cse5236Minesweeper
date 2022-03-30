package com.cse5236.minesweeper;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class DAOEasy {

    private DatabaseReference easyTable; // Reference for the 'Easy' table

    public DAOEasy()
    {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        easyTable = db.getReference("LeaderBoard/Easy");
    }

    /* Create or Update a record depending on the existence of the player name */
    public void submit(Player p) {

        /* orderByChild("name") only means you get a copy of data ordered by name but not ordering the original data */
        easyTable.orderByChild("name").equalTo(p.getName()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    /* If name exists, update */
                    for (DataSnapshot child : snapshot.getChildren()) {
                        String key = child.getKey();
                        easyTable.child(key).setValue(p);
                    }
                } else {
                    /* If name does not exist, create a new node */
                    easyTable.push().setValue(p);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    public void delete(Player p) {

        /* orderByChild("name") only means you get a copy of data ordered by name but not ordering the original data */
        easyTable.orderByChild("name").equalTo(p.getName()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {
                    /* If name exists, update */
                    for (DataSnapshot child : snapshot.getChildren()) {
                        String key = child.getKey();
                        easyTable.child(key).removeValue();
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
        return easyTable.orderByChild("time");
    }

}
