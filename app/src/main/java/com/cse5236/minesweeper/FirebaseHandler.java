package com.cse5236.minesweeper;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHandler extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        /* Enable Firebase disk persistence.
        * Application class FirebaseHandler also added to the manifest. */
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        /* setPersistenceEnabled is to be called only once (before any other instances of
        * FirebaseDatabase are made). Without this class, an error will occur. */
    }
}
