package com.cse5236.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnCellClickListener {
    RecyclerView gridRecyclerView;
    MineGridRecyclerAdapter mineGridRecyclerAdapter;
    MinesweeperGame game;
    TextView scoreBoard;
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


        mineGridRecyclerAdapter = new MineGridRecyclerAdapter(game.getMineGrid().getCells(),this);
        gridRecyclerView.setAdapter(mineGridRecyclerAdapter);
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
        }
        if(game.isGameWon()){
            Toast.makeText(getApplicationContext(),"Game Won",Toast.LENGTH_SHORT).show();
            game.getMineGrid().revealAllBombs();

        }
        mineGridRecyclerAdapter.setCells(game.getMineGrid().getCells());
    }
}