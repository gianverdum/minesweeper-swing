package com.github.gv.ms.view;

import com.github.gv.ms.model.Board;

public class Temp {

    public static void main(String[] args) {

        Board board = new Board(3, 3, 9);

        board.registerObserver(result -> {
            if(result) {
                System.out.println("Ganhou!");
            } else {
                System.out.println("Nao ganhou!");
            }
        });

        board.open(0,0);
    }
}
