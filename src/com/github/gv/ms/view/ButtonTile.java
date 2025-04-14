package com.github.gv.ms.view;

import com.github.gv.ms.model.Tile;
import com.github.gv.ms.model.TileEvent;
import com.github.gv.ms.model.TileObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ButtonTile extends
        JButton implements TileObserver, MouseListener {

    private final Color BG_DEFAULT = new Color(184,184,184);
    private final Color BG_FLAG = new Color(8,179,247);
    private final Color BG_EXPLODE = new Color(189,66,68);
    private final Color TEXT_GREEN = new Color(0,100,0);

    private Tile tile;

    public ButtonTile(Tile tile) {
        this.tile = tile;
        setBackground(BG_DEFAULT);
        setBorder(BorderFactory.createBevelBorder(0));

        addMouseListener(this);
        tile.registerObserver(this);
    }

    @Override
    public void eventOccurred(Tile tile, TileEvent event) {
        switch(event) {
            case OPEN:
                applyOpenStyle();
                break;
            case FLAG:
                applyFlagStyle();
                break;
            case EXPLODE:
                applyExplodeStyle();
                break;
            default:
                applyDefaultStyle();
        }
    }

    private void applyOpenStyle() {
        setBackground(BG_DEFAULT);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        switch (tile.minesOnNeighbourhood()) {
            case 1:
                setForeground(TEXT_GREEN);
                break;
            case 2:
                setForeground(Color.BLUE);
                break;
            case 3:
                setForeground(Color.YELLOW);
                break;
            case 4:
            case 5:
            case 6:
                setForeground(Color.RED);
                break;
            default:
                setForeground(Color.PINK);
        }
        String value = !tile.safeNeighbours() ?
                tile.minesOnNeighbourhood() + "" : "";
        setText(value);
    }
    private void applyFlagStyle() {
    }
    private void applyExplodeStyle() {
    }
    private void applyDefaultStyle() {
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (mouseEvent.getButton() == 1) {
            tile.open();
        } else {
            tile.changeFlag();
        }
    }

    public void mouseReleased(MouseEvent mouseEvent) {}
    public void mouseEntered(MouseEvent mouseEvent) {}
    public void mouseExited(MouseEvent mouseEvent) {}
}
