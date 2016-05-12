package main.java;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece implements Serializable
{
    List<RelativePosition> possibleMoves;

    public Knight(PieceColor color)
    {
        super(color, PieceType.KNIGHT);
        possibleMoves = new ArrayList<RelativePosition>();

        possibleMoves.add(new RelativePosition(1, 2, 1));
        possibleMoves.add(new RelativePosition(2, 1, 1));
        possibleMoves.add(new RelativePosition(1, -2, 1));
        possibleMoves.add(new RelativePosition(2, -1, 1));
        possibleMoves.add(new RelativePosition(-1, 2, 1));
        possibleMoves.add(new RelativePosition(-2, 1, 1));
        possibleMoves.add(new RelativePosition(-1, -2, 1));
        possibleMoves.add(new RelativePosition(-2, -1, 1));
    }

    @Override
    List<RelativePosition> getNewPossibleMoves()
    {
        return this.possibleMoves;
    }
}
