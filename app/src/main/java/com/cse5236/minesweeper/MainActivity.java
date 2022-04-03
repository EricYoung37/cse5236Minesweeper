package com.cse5236.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements OnCellClickListener, SubmitFragment.DialogListener {
    // RecyclerView
    RecyclerView gridRecyclerView;
    MineGridRecyclerAdapter mineGridRecyclerAdapter;
    // game
    MinesweeperGame game;
    // UI
    TextView timerText;
    TextView flag,flagsCount;
    Timer timer;
    TimerTask timerTask;
    Double time = 0.0;
    Button restartBtn;

    Difficulty difficulty;

    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        difficulty = Difficulty.getInstance();

        gridRecyclerView = findViewById(R.id.activity_main_grid);
        gridRecyclerView.setLayoutManager(new GridLayoutManager(this, difficulty.getSize()));
        game = new MinesweeperGame(difficulty.getSize(), difficulty.getBombNum());
        count = 0;

        flag  = (TextView)findViewById(R.id.activity_main_flag);
        flagsCount  = (TextView)findViewById(R.id.activity_main_flagsleft);

        flag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.toggleMode();
                if(game.isFlagMode()){
                    GradientDrawable border = new GradientDrawable();
                    border.setColor(0xFFFFFFFF);
                    border.setStroke(10, Color.parseColor("#bab3dc"));
                    flag.setBackground(border);

                }
                else{
                    GradientDrawable border = new GradientDrawable();
                    border.setColor(0xFFFFFFFF);
                    flag.setBackground(border);
                }
            }
        });

        timerText = findViewById(R.id.timer);
        timer = new Timer();

        mineGridRecyclerAdapter = new MineGridRecyclerAdapter(game.getMineGrid().getCells(),this, game);
        gridRecyclerView.setAdapter(mineGridRecyclerAdapter);

//        Log.d("MainActivity", "onCreate called");
        startTimer();
        flagsCount.setText(String.format("%03d", game.getNumOfBombs()-game.getFlagNum()));

        // Restart button
        restartBtn = findViewById(R.id.restart);
        restartBtn.setOnClickListener(v -> {
            // new game
            game = new MinesweeperGame(difficulty.getSize(), difficulty.getBombNum());

            // reset timer
            timer.cancel();
            timer = new Timer();
            time = 0.0;
            startTimer();

            // reset flag count
            flagsCount.setText(String.format("%03d", game.getNumOfBombs()-game.getFlagNum()));

            // view game
            mineGridRecyclerAdapter = new MineGridRecyclerAdapter(game.getMineGrid().getCells(),this, game);
            gridRecyclerView.setAdapter(mineGridRecyclerAdapter);
        });

    }

    @Override
    public void cellClick(Cell cell) {

        game.handleCellClick(cell);
        flagsCount.setText(String.format("%03d", game.getNumOfBombs()-game.getFlagNum()));
        if(game.gameOver()){
            Toast.makeText(getApplicationContext(),"Game Over",Toast.LENGTH_SHORT).show();
            game.getMineGrid().revealAllBombs();
            timerTask.cancel();
        }
        if(game.isGameWon()){
            Toast.makeText(getApplicationContext(),"Game Won",Toast.LENGTH_SHORT).show();
            game.getMineGrid().revealAllBombs();
            timerTask.cancel();
            openDialog();
        }
        mineGridRecyclerAdapter.setCells(game.getMineGrid().getCells());
    }

    public void startTimer(){
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        timerText.setText(getTimerText());
                        time++;
                    }
                });

            }
        };
        timer.scheduleAtFixedRate(timerTask,0,1000);

    }

    private String getTimerText() {
        int round = (int) Math.round(time);
        int seconds = ((round % 86400) % 3600) % 60;
        int minutes =   ((round % 86400) % 3600) / 60;

        return String.format("%02d", minutes) + " : " + String.format("%02d", seconds);
    }

    public void openDialog() {
        SubmitFragment submitFragment = new SubmitFragment();
        submitFragment.show(getSupportFragmentManager(), "Submit Fragment");
    }

    @Override
    public void applyPlayerName(String PlayerName) {
        // If player's name isn't empty, send it to database
        if(!PlayerName.equals("")) {
            Player p = new Player(PlayerName, timerText.getText().toString());
            if(difficulty.getDifficulty().equals("Hard")) {
                DAOHard daoHard = new DAOHard();
                daoHard.submit(p);
            } else if(difficulty.getDifficulty().equals("Easy")) {
                DAOEasy daoEasy = new DAOEasy();
                daoEasy.submit(p);
            }
        }
    }

//    @Override
//    protected void onDestroy()
//    {
//        super.onDestroy();
//        Log.d("MainActivity", "onDestroy called");
//    }
//
//    @Override
//    protected void onStart()
//    {
//        super.onStart();
//        Log.d("MainActivity", "onStart called");
//    }
//
//    @Override
//    protected void onResume()
//    {
//        super.onResume();
//        Log.d("MainActivity", "onResume called");
//    }
//
//    @Override
//    protected void onStop()
//    {
//        super.onStop();
//        Log.d("MainActivity", "onStop called");
//    }
//
//    @Override
//    protected void onPause()
//    {
//        super.onPause();
//        Log.d("MainActivity", "onPause called");
//    }
}