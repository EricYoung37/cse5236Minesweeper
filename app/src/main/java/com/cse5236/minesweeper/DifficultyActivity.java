package com.cse5236.minesweeper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DifficultyActivity extends AppCompatActivity {

    public Difficulty difficulty;
    private Button easyBtn, hardBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        difficulty = Difficulty.getInstance();

        easyBtn = (Button) findViewById(R.id.easy_btn);
        hardBtn = (Button) findViewById(R.id.hard_btn);

        easyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String difficultyText = easyBtn.getText().toString();
                difficulty.setDifficulty(difficultyText);
                Toast.makeText(getApplicationContext(), "Difficulty set to EASY", Toast.LENGTH_SHORT).show();
                Log.d("DifficultyActivity",difficulty.getDifficulty());
            }
        });

        hardBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String difficultyText = hardBtn.getText().toString();
                difficulty.setDifficulty(difficultyText);
                Toast.makeText(getApplicationContext(), "Difficulty set to HARD", Toast.LENGTH_SHORT).show();
                Log.d("DifficultyActivity",difficulty.getDifficulty());
            }
        });
    }
}