package com.github.gv.ms.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Board implements TileObserver {

    private final int rows;
    private final int cols;
    private final int mines;

    private final List<Tile> tiles = new ArrayList<Tile>();
    private final List<Consumer<Boolean>> observers =
            new ArrayList<>();

    public Board(int rows, int cols, int mines) {
        this.rows = rows;
        this.cols = cols;
        this.mines = mines;

        generateTiles();
        assignNeighbours();
        sortMines();
    }

    public void forEachTile(Consumer<Tile> function) {
        tiles.forEach(function);
    }

    public void registerObserver(Consumer<Boolean> observer) {
        observers.add(observer);
    }

    public void notifyObservers(boolean result) {
        observers.stream()
                .forEach(o -> o.accept(result));
    }

    public void open(int row, int col) {
        tiles.parallelStream()
                .filter(t -> t.getRow() == row && t.getCol() == col)
                .findFirst()
                .ifPresent(t -> t.open());
    }

    public void flag(int row, int col) {
        tiles.parallelStream()
                .filter(t -> t.getRow() == row && t.getCol() == col)
                .findFirst()
                .ifPresent(t -> t.changeFlag());
    }

    private void generateTiles() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Tile tile = new Tile(r, c);
                tile.registerObserver(this);
                tiles.add(tile);
            }
        }
    }

    private void assignNeighbours() {
        for (Tile tile1 : tiles) {
            for (Tile tile2 : tiles) {
                tile1.addNeighbour(tile2);
            }
        }
    }

    private void sortMines() {
        long armedMines = 0;
        Predicate<Tile> hasMine = t -> t.hasMine();

        do {
            int random = (int) (Math.random() * tiles.size());
            tiles.get(random).setMine();
            armedMines = tiles.stream().filter(hasMine).count();
        } while (armedMines < mines);
    }

    public boolean goalAchieved() {
        return tiles.stream().allMatch(Tile::goalAchieved);
    }

    public void reset() {
        tiles.stream().forEach(Tile::reset);
        sortMines();
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    public int getMines() {
        return mines;
    }

    @Override
    public void eventOccurred(Tile tile, TileEvent event) {
        if(event == TileEvent.EXPLODE) {
            showMines();
            notifyObservers(false);
        } else if(goalAchieved()) {
            notifyObservers(true);
        }
    }

    private void showMines() {
        tiles.stream()
                .filter(t -> t.hasMine())
                .filter(t -> !t.hasFlag())
                .forEach(t -> t.setOpen(true));
    }

}
