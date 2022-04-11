package com.cse5236.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingsActivity extends AppCompatActivity {

    private Button changeDifficulty, changeTheme;

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

        changeTheme = (Button) findViewById(R.id.theme_btn);
        changeTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                configureTheme();
            }
        });
    }

    public void configureDifficulty() {
        Intent intent = new Intent(this, DifficultyActivity.class);
        startActivity(intent);
    }

    public void configureTheme() {
        Intent intent = new Intent(this, ThemeActivity.class);
        startActivity(intent);
    }
}