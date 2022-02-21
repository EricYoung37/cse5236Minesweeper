package com.cse5236.minesweeper;

/* Track states in a game */
public class MinesweeperGame {
    private MineGrid mineGrid;

    public MinesweeperGame(int size, int numberOfBombs) {
        mineGrid = new MineGrid(size);
        mineGrid.generateGrid(numberOfBombs);
    }

    public MineGrid getMineGrid() {
        return mineGrid;
    }
}
