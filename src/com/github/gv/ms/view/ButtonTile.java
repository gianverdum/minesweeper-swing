package com.github.gv.ms.view;

import com.github.gv.ms.model.Tile;
import com.github.gv.ms.model.TileEvent;
import com.github.gv.ms.model.TileObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

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
        setOpaque(true);
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

        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        if(tile.hasMine()) {
            setBackground(BG_EXPLODE);
            setIcon(loadScaledIcon("/img/mine.png",16,16));
            return;
        }

        setBackground(BG_DEFAULT);

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
        setBackground(BG_FLAG);
        setIcon(loadScaledIcon("/img/flag.jpg",16,16));
    }
    private void applyExplodeStyle() {
        setBackground(BG_EXPLODE);
        setIcon(loadScaledIcon("/img/mine.png",16,16));
    }
    private void applyDefaultStyle() {
        setBackground(BG_DEFAULT);
        setBorder(BorderFactory.createBevelBorder(0));
        setText("");
        setIcon(null);
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

    private ImageIcon loadScaledIcon(String path, int width, int height) {
        URL iconURL = getClass().getResource(path);
        if (iconURL == null) {
            System.err.println("Image not found: " + path);
            return null;
        }
        ImageIcon icon = new ImageIcon(iconURL);
        Image scaledImage = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }
}
