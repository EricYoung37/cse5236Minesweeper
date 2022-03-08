package com.cse5236.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.widget.TextView;

import android.os.Bundle;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements OnCellClickListener {
    RecyclerView gridRecyclerView;
    MineGridRecyclerAdapter mineGridRecyclerAdapter;
    MinesweeperGame game;
    TextView scoreBoard;
    TextView timerText;
    Timer timer;
    TimerTask timerTask;
    Double time = 0.0;
    int score;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gridRecyclerView = findViewById(R.id.activity_main_grid);
        gridRecyclerView.setLayoutManager(new GridLayoutManager(this, 10)); /* spanCount: number of column */

        game = new MinesweeperGame(10, 10);
        score = 0;
        count = 0;
        scoreBoard = findViewById(R.id.score);
        timerText = (TextView)findViewById(R.id.timer);
        timer = new Timer();
        mineGridRecyclerAdapter = new MineGridRecyclerAdapter(game.getMineGrid().getCells(),this);
        gridRecyclerView.setAdapter(mineGridRecyclerAdapter);

        Log.d("MainActivity", "onCreate called!!!");
        startTimer();
    }

    @Override
    public void cellClick(Cell cell) {
        count++;
        if(count == 3){
            score++;
            scoreBoard.setText(String.format("%s","Score: " + score));
            count = 0;
        }
        game.handleCellClick(cell);

        if(game.gameOver()){
            Toast.makeText(getApplicationContext(),"Game Over",Toast.LENGTH_SHORT).show();
            game.getMineGrid().revealAllBombs();
            timerTask.cancel();
        }
        if(game.isGameWon()){
            Toast.makeText(getApplicationContext(),"Game Won",Toast.LENGTH_SHORT).show();
            game.getMineGrid().revealAllBombs();
            timerTask.cancel();

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
                        time++;
                        timerText.setText(getTimerText());
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

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        Log.d("MainActivity", "onDestroy called!!!");
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Log.d("MainActivity", "onStart called!!!");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        Log.d("MainActivity", "onResume called!!!");
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        Log.d("MainActivity", "onStop called!!!");
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        Log.d("MainActivity", "onPause called!!!");
    }
}