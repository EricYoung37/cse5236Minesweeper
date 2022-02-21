package com.cse5236.minesweeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnCellClickListener {
    RecyclerView gridRecyclerView;
    MineGridRecyclerAdapter mineGridRecyclerAdapter;
    MinesweeperGame game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridRecyclerView = findViewById(R.id.activity_main_grid);
        gridRecyclerView.setLayoutManager(new GridLayoutManager(this, 10)); /* spanCount: number of column */

        game = new MinesweeperGame(10, 10);

        mineGridRecyclerAdapter = new MineGridRecyclerAdapter(game.getMineGrid().getCells(),this);
        gridRecyclerView.setAdapter(mineGridRecyclerAdapter);
    }

    @Override
    public void cellClick(Cell cell) {
        Toast.makeText(getApplicationContext(), "Cell Clicked", Toast.LENGTH_SHORT).show();
    }
}