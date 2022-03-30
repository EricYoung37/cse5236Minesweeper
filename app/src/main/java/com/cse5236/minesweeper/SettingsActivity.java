package com.cse5236.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    private Button changeDifficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        changeDifficulty = (Button) findViewById(R.id.difficulty_btn);
        changeDifficulty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                configureDifficulty();
            }
        });
    }

    public void configureDifficulty() {
        Intent intent = new Intent(this, DifficultyActivity.class);
        startActivity(intent);
    }
}