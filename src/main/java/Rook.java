package main.java;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece implements Serializable
{
    List<RelativePosition> possibleMoves;

    public Rook(PieceColor color)
    {
        super(color, PieceType.ROOK);
        possibleMoves = new ArrayList<RelativePosition>();

        possibleMoves.add(new RelativePosition(0, 1, Board.GRID_SIZE));
        possibleMoves.add(new RelativePosition(1, 0, Board.GRID_SIZE));
        possibleMoves.add(new RelativePosition(-1, 0, Board.GRID_SIZE));
        possibleMoves.add(new RelativePosition(0, -1, Board.GRID_SIZE));
    }

    @Override
    List<RelativePosition> getNewPossibleMoves()
    {
        return this.possibleMoves;
    }
}
