package com.github.gv.ms.view;

import com.github.gv.ms.model.Board;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends JFrame {

    public MainScreen() {
        Board board = new Board(16,30, 50);

        add(new PanelBoard(board));

        setTitle("Mine Sweeper");
        setSize(690, 438);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {

        new MainScreen();
    }
}
