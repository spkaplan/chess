package main.java;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece implements Serializable
{
    List<RelativePosition> possibleMoves;

    public Bishop(PieceColor color)
    {
        super(color, PieceType.BISHOP);
        possibleMoves = new ArrayList<RelativePosition>();

        possibleMoves.add(new RelativePosition(1, 1, Board.GRID_SIZE));
        possibleMoves.add(new RelativePosition(-1, 1, Board.GRID_SIZE));
        possibleMoves.add(new RelativePosition(1, -1, Board.GRID_SIZE));
        possibleMoves.add(new RelativePosition(-1, -1, Board.GRID_SIZE));
    }

    @Override
    List<RelativePosition> getNewPossibleMoves()
    {
        return this.possibleMoves;
    }
}
