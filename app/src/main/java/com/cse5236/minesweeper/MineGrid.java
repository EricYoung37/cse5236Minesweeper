package com.cse5236.minesweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MineGrid {
    private List<Cell> cells;
    private int size;

    /* Create an empty grid */
    public MineGrid(int size) {
        this.size = size;
        this.cells = new ArrayList<>();

        for (int i = 0; i < size * size; i++) {
            cells.add(new Cell(Cell.BLANK));
        }
    }

    /* Pack the empty grid with bombs and texts on cells */
    public void generateGrid(int numberOfBombs) {
        /* Randomly generate bombs */
        int bombsPlaced = 0;
        while (bombsPlaced < numberOfBombs) {
            int x = new Random().nextInt(size);
            int y = new Random().nextInt(size);

            if (cellAt(x, y).getValue() == Cell.BLANK) {
                cells.set(x + (y*size), new Cell(Cell.BOMB));
                bombsPlaced++;
            }
        }

        /* For non-bomb cells */
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                /* Count bombs adjacent to a non-bomb cell */
                if (cellAt(x, y).getValue() != Cell.BOMB) {
                    List<Cell> adjacentCells = adjacentCells(x, y);
                    int countBombs = 0;
                    for (Cell cell: adjacentCells) {
                        if (cell.getValue() == Cell.BOMB) {
                            countBombs++;
                        }
                    }

                    /* Assign the number of adjacent bombs to a cell */
                    if (countBombs > 0) {
                        cells.set(x + (y*size), new Cell(countBombs));
                    }
                }
            }
        }
    }

    /* Retrieve a cell instance (not coordinate) */
    public Cell cellAt(int x, int y) {
        if (x < 0 || x >= size || y < 0 || y >= size) {
            return null;
        }
        return cells.get(x + (y*size));
    }

    /* Retrieve the adjacent cells of a cell */
    public List<Cell> adjacentCells(int x, int y) {
        List<Cell> adjacentCells = new ArrayList<>();

        List<Cell> cellsList = new ArrayList<>();
        cellsList.add(cellAt(x-1, y));
        cellsList.add(cellAt(x+1, y));
        cellsList.add(cellAt(x-1, y-1));
        cellsList.add(cellAt(x, y-1));
        cellsList.add(cellAt(x+1, y-1));
        cellsList.add(cellAt(x-1, y+1));
        cellsList.add(cellAt(x, y+1));
        cellsList.add(cellAt(x+1, y+1));

        /* Corner cells and edge cells have no adjacent cells in some directions */
        for (Cell cell: cellsList) {
            if (cell != null) {
                adjacentCells.add(cell);
            }
        }

        return adjacentCells;
    }

    /* Convert coordinate to list index
    x: column, y: row */
    public int toIndex(int x, int y) {
        return x + (y*size);
    }

    /* List index to coordinate */
    public int[] toXY(int index) {
        int y = index / size;
        int x = index - (y*size);
        return new int[]{x, y};
    }

    public List<Cell> getCells() {
        return cells;
    }
}
