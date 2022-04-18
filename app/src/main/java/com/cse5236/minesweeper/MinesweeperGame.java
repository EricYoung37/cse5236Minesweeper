package com.cse5236.minesweeper;

import java.util.ArrayList;
import java.util.List;

/* Track states in a game */
public class MinesweeperGame {
    private MineGrid mineGrid;
    private boolean clearMode;
    private boolean flagMode;
    private int flagNum;
    private boolean isGameOver;
    private int numOfBombs;

    public MinesweeperGame(int size, int numberOfBombs) {
        this.clearMode = true;
        this.isGameOver = false;
        this.numOfBombs = numberOfBombs;
        flagMode = false;
        flagNum = 0;
        mineGrid = new MineGrid(size);
        mineGrid.generateGrid(numberOfBombs);

    }

    public void handleCellClick(Cell cell){
        if(!gameOver() && !isGameWon()){
            if(clearMode){
                clear(cell);
            }else if(flagMode){
                flagging(cell);
            }
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
                if(c.isFlagged()) {
                    /* Reclaim a flag upon revealing a flagged safe cell */
                    flagNum--;
                    c.setFlagged(false); // This line is important.
                    /* Even if the revealed cell is not important to the game, its flag state
                    * will be tracked in the for loop of the method flagging(). Therefore, the
                    * flag state of a revealed cell must also be set properly. */
                }
            }
        }
      else if (cell.getValue() == Cell.BOMB) {
          isGameOver = true;
      }
    }
    public void flagging(Cell c){
        if(!c.isRevealed()){
            /* Prevent flagging if the number of used flags equals the number of bombs. */
            if((flagNum<numOfBombs) && !c.isFlagged()) {
                c.setFlagged(true);
            } else if(c.isFlagged()) {
                c.setFlagged(false);
            }

            /* Count flags in use so that the flag screen can display correctly. */
            int count = 0;
            for(Cell c2: getMineGrid().getCells()){
                if(c2.isFlagged()){
                    count++;
                }
            }
            flagNum = count;
        }
    }

    public void toggleMode(){
        clearMode = !clearMode;
        flagMode = !flagMode;
    }

    public boolean isFlagMode() {
        return flagMode;
    }

    public int getFlagNum() {
        return flagNum;
    }

    public int getNumOfBombs() {
        return numOfBombs;
    }

    public MineGrid getMineGrid() {
        return mineGrid;
    }

    public boolean gameOver(){
        return isGameOver;
    }
}
