package com.github.gv.ms.model;

import java.util.ArrayList;
import java.util.List;

public class Tile {

    private final int row;
    private final int col;

    private boolean openStatus;
    private boolean mineStatus;
    private boolean flagStatus;

    private List<Tile> neighbours = new ArrayList<Tile>();

    Tile(int row, int col) {
        this.row = row;
        this.col = col;
    }

    boolean addNeighbour(Tile neighbour) {
        boolean differentRow = row != neighbour.row;
        boolean differentCol = col != neighbour.col;
        boolean diagonal = differentRow && differentCol;

        int deltaRow = Math.abs(row - neighbour.row);
        int deltaCol = Math.abs(col - neighbour.col);
        int delta = deltaRow + deltaCol;

        if(delta == 1 && !diagonal) {
            neighbours.add(neighbour);
            return true;
        } else if(delta == 2 && diagonal) {
            neighbours.add(neighbour);
            return true;
        } else {
            return false;
        }

    }

    void changeFlag() {
        if(!openStatus) {
            flagStatus = !flagStatus;
        }
    }

    boolean open() {
        if(!openStatus && !flagStatus) {
            openStatus = true;

            if(mineStatus) {
                // TODO implementar nova versão
            }

            if(safeNeighbours()) {
                neighbours.forEach(Tile::open);
            }
            return true;
        } else {
            return false;
        }
    }

    boolean safeNeighbours() {
        return neighbours.stream().noneMatch(n -> n.mineStatus);
    }

    void setMine() {
        mineStatus = true;
    }

    void setOpen(boolean open) {
        openStatus = open;
    }

    public boolean isOpen() {
        return openStatus;
    }

    public boolean hasFlag() {
        return flagStatus;
    }

    public boolean hasMine() {
        return mineStatus;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    boolean goalAchieved() {
        boolean revealed = !mineStatus && openStatus;
        boolean safe = mineStatus && flagStatus;
        return revealed || safe;
    }

    long minesOnNeighbourhood() {
        return neighbours.stream().filter(n -> n.mineStatus).count();
    }

    void reset() {
        openStatus = false;
        mineStatus = false;
        flagStatus = false;
    }

}
