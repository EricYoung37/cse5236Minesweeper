package com.cse5236.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements OnCellClickListener {
    //recycler
    RecyclerView gridRecyclerView;
    MineGridRecyclerAdapter mineGridRecyclerAdapter;
    //game
    MinesweeperGame game;
    //UI
    TextView scoreBoard;
    TextView timerText;
    TextView flag,flagsCount;
    EditText playerName;
    Timer timer;
    TimerTask timerTask;
    Double time = 0.0;
    Button submitBtn;

    Difficulty difficulty;

    int score;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        difficulty = Difficulty.getInstance();

        gridRecyclerView = findViewById(R.id.activity_main_grid);
//        gridRecyclerView.setLayoutManager(new GridLayoutManager(this, 10)); // spanCount: number of column/row
//        game = new MinesweeperGame(10, 10);
        gridRecyclerView.setLayoutManager(new GridLayoutManager(this, difficulty.getSize()));
        game = new MinesweeperGame(difficulty.getSize(), difficulty.getBombNum());
        score = 0;
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
                    border.setStroke(1,0xFF000000);
                    flag.setBackground(border);

                }else{
                    GradientDrawable border = new GradientDrawable();
                    border.setColor(0xFFFFFFFF);
                    flag.setBackground(border);
                }
            }
        });

       // scoreBoard = findViewById(R.id.score);
        timerText = findViewById(R.id.timer);
        timer = new Timer();

        DAOPlayer dao = new DAOPlayer();
        playerName = findViewById(R.id.name);

        mineGridRecyclerAdapter = new MineGridRecyclerAdapter(game.getMineGrid().getCells(),this);
        gridRecyclerView.setAdapter(mineGridRecyclerAdapter);

//        Log.d("MainActivity", "onCreate called!!!");
        startTimer();

        submitBtn = findViewById(R.id.submit);
        submitBtn.setOnClickListener(v-> {
            Player p = new Player(playerName.getText().toString(), timerText.getText().toString());
            dao.submit(p);
        });
        flagsCount.setText(String.format("%03d", game.getNumOfBombs()-game.getFlagNum()));
    }

    @Override
    public void cellClick(Cell cell) {

//        count++;
//        if(count == 3){
//            score++;
//            scoreBoard.setText(String.format("%s","        " + score));
//            count = 0;
//        }

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

//    @Override
//    protected void onDestroy()
//    {
//        super.onDestroy();
//        Log.d("MainActivity", "onDestroy called!!!");
//    }
//
//    @Override
//    protected void onStart()
//    {
//        super.onStart();
//        Log.d("MainActivity", "onStart called!!!");
//    }
//
//    @Override
//    protected void onResume()
//    {
//        super.onResume();
//        Log.d("MainActivity", "onResume called!!!");
//    }
//
//    @Override
//    protected void onStop()
//    {
//        super.onStop();
//        Log.d("MainActivity", "onStop called!!!");
//    }
//
//    @Override
//    protected void onPause()
//    {
//        super.onPause();
//        Log.d("MainActivity", "onPause called!!!");
//    }
}