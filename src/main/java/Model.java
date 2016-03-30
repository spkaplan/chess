package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * Created by brand on 3/29/2016.
 */
class Model {
    private Board board;
    private PieceColor whosTurn;
    private List<Observer> observers;
    private int turnCount;

    Model() {
        this.board = new Board();
        this.whosTurn = PieceColor.WHITE;
        this.observers = new ArrayList<>();
        this.turnCount = 1;
    }

    void incomingAction(String action) {
        System.out.println(action);
    }

    int getTurnCount() {
        return turnCount;
    }

    Model getState() {
        return this;
    }
}
