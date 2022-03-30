package com.cse5236.minesweeper;

import android.util.Log;

public class Difficulty {
    private static Difficulty instance;

    /* Difficulty = Easy | Hard
             size = 5*5 | 10*10
          bombNum = 5 | 10
     */
    private String difficulty = "Easy"; // Default
    private int span = 5; // size = span*span
    private int bombNum = 5;

    /* Singleton pattern */
    public static Difficulty getInstance() {
        if(instance == null) {
            instance = new Difficulty();
        }
        return instance;
    }

    /* The parameter difficultyText will come from DifficultyActivity.
    In DifficultyActivity, there would be something like button.getText().toString().
    The return value of this chain of method calls is a string of difficulty and can
    be passed to setDifficulty() here.
     */
    public void setDifficulty(String difficultyText) {
        if (difficultyText.equals("Easy")) {
            difficulty = "Easy";
            span = 5;
            bombNum = 5;
            Log.d("Difficulty", Integer.toString(span));
        } else if (difficultyText.equals("Hard")) {
            difficulty = "Hard";
            span = 10;
            bombNum = 10;
            Log.d("Difficulty", Integer.toString(span));
        }
    }

    /* In MainActivity, use this to replace the size/span argument in these lines
    gridRecyclerView.setLayoutManager(new GridLayoutManager(this, 10));
    game = new MinesweeperGame(10, 10);
     */
    public int getSize() {
        return span;
    }

    /* In MainActivity, use this to replace the bomb number argument in this line
    game = new MinesweeperGame(10, 10);
     */
    public int getBombNum() {
        return bombNum;
    }

    /* This will be used in DAOPlayer to find the appropriate data table, e.g., in submit()
    something like tableRef.child("Hard").push() will push the data to the "Hard" table, and
    something like tableRef.child("Easy").push() will push the data to the "Easy" table.
    Similarly for Read and Delete.
     */
    public String getDifficulty() {
        return difficulty;
    }

}
