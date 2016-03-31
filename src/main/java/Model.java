package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * Created by brandon on 3/29/2016 at 4:44 PM as part of the chess project.
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

    void addObserver(Observer observer) {
        observers.add(observer);
    }

    void incomingAction(String action) {
        System.out.println(action);
    }

    Board getBoard() {
        return this.board;
    }

    PieceColor getWhosTurn() {
        return this.whosTurn;
    }

    int getTurnCount() {
        return turnCount;
    }

    Model getState() {
        return this;
    }
    
}
