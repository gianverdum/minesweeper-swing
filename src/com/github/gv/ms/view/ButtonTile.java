package com.github.gv.ms.view;

import com.github.gv.ms.model.Tile;
import com.github.gv.ms.model.TileEvent;
import com.github.gv.ms.model.TileObserver;

import javax.swing.*;
import java.awt.*;

public class ButtonTile extends JButton implements TileObserver {

    private final Color BG_DEFAULT = new Color(184,184,184);
    private final Color BG_FLAG = new Color(8,179,247);
    private final Color BG_EXPLODE = new Color(189,66,68);
    private final Color TEXT_GREEN = new Color(0,100,0);

    private Tile tile;

    public ButtonTile(Tile tile) {
        this.tile = tile;
        setBackground(BG_DEFAULT);
        setBorder(BorderFactory.createBevelBorder(0));
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
    }
    private void applyFlagStyle() {
    }
    private void applyExplodeStyle() {
    }
    private void applyDefaultStyle() {
    }
}
