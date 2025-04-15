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

    private List<TileObserver> observers = new ArrayList<>();
//    private List<BiConsumer<Tile, TileEvent>> observers2 = new ArrayList<>(); // Alternative implementation with Method Interface

    public void registerObserver(TileObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers(TileEvent event) {
        observers.stream()
                .forEach(o -> o.eventOccurred(this, event));
    }

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

    public void changeFlag() {
        if(!openStatus) {
            flagStatus = !flagStatus;

            if(flagStatus) {
                notifyObservers(TileEvent.FLAG);
            } else {
                notifyObservers(TileEvent.UNFLAG);
            }
        }
    }

    public boolean open() {
        if(!openStatus && !flagStatus) {

            if(mineStatus) {
                notifyObservers(TileEvent.EXPLODE);
                return true;
            }
            setOpen(true);

            if(safeNeighbours()) {
                neighbours.forEach(Tile::open);
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean safeNeighbours() {
        return neighbours.stream().noneMatch(n -> n.mineStatus);
    }

    void setMine() {
        mineStatus = true;
    }

    void setOpen(boolean open) {
        this.openStatus = open;

        if (open) {
            notifyObservers(TileEvent.OPEN);
        }
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

    public int minesOnNeighbourhood() {
        return (int) neighbours.stream().filter(n -> n.mineStatus).count();
    }

    void reset() {
        openStatus = false;
        mineStatus = false;
        flagStatus = false;
        notifyObservers(TileEvent.RESET);
    }

}
