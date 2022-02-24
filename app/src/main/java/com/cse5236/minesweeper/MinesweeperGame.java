package com.cse5236.minesweeper;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/* Track states in a game */
public class MinesweeperGame {
    private MineGrid mineGrid;
    private boolean clearMode;
    private boolean isGameOver;
    private int score;

    public MinesweeperGame(int size, int numberOfBombs) {
        this.clearMode = true;
        this.isGameOver = false;
        mineGrid = new MineGrid(size);
        mineGrid.generateGrid(numberOfBombs);

    }

    public void handleCellclick(Cell cell){
        if(clearMode){
            clear(cell);
        }
    }

    public boolean isGameWon(){
        for(Cell c: getMineGrid().getCells()){
            if(c.getValue() != Cell.BOMB && c.getValue() != Cell.BLANK && !c.isRevealed()){
                return false;
            }
        }
        return true;
    }

    public void clear(Cell cell){
        int index = getMineGrid().getCells().indexOf(cell);
        getMineGrid().getCells().get(index).setRevealed(true);

      if (cell.getValue() == Cell.BLANK) {
            List<Cell> toClear = new ArrayList<>();
            List<Cell> toCheckAdjacents = new ArrayList<>();

            toCheckAdjacents.add(cell);

            while (toCheckAdjacents.size() > 0) {
                Cell c = toCheckAdjacents.get(0);
                int cellIndex = getMineGrid().getCells().indexOf(c);
                int[] cellPos = getMineGrid().toXY(cellIndex);
                for (Cell adjacent: getMineGrid().adjacentCells(cellPos[0], cellPos[1])) {
                    if (adjacent.getValue() == Cell.BLANK) {
                        if (!toClear.contains(adjacent)) {
                            if (!toCheckAdjacents.contains(adjacent)) {
                                toCheckAdjacents.add(adjacent);
                            }
                        }
                    } else {
                        if (!toClear.contains(adjacent)) {
                            toClear.add(adjacent);
                        }
                    }
                }
                toCheckAdjacents.remove(c);
                toClear.add(c);
            }

            for (Cell c: toClear) {
                c.setRevealed(true);
            }
        }
      else if (cell.getValue() == Cell.BOMB) {
          isGameOver = true;
      }
    }
    public MineGrid getMineGrid() {
        return mineGrid;
    }

    public boolean gameOver(){
        return isGameOver;
    }
}
