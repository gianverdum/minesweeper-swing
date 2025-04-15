package com.github.gv.ms.view;

import com.github.gv.ms.model.Board;

import javax.swing.*;
import java.awt.*;

public class PanelBoard extends JPanel {
    public PanelBoard(Board board) {

        setLayout(new GridLayout(board.getRows(),board.getCols()));

        board.forEachTile(tile -> add(new ButtonTile(tile)));
        board.registerObserver(e -> {
            SwingUtilities.invokeLater(() -> {
                Component parent = SwingUtilities.getWindowAncestor(this);
                if(e) {
                    JOptionPane.showMessageDialog(parent, "You win!");
                } else {
                    JOptionPane.showMessageDialog(parent, "You lose!");
                }

                board.reset();
            });
        });

    }
}
