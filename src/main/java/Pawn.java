package main.java;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece implements Serializable
{
    List<RelativePosition> possibleMovesInitial;
    List<RelativePosition> possibleMovesRegular;

    public Pawn(PieceColor color)
    {
        super(color, PieceType.PAWN);
        possibleMovesInitial = new ArrayList<RelativePosition>();
        possibleMovesRegular = new ArrayList<RelativePosition>();

        /*Pawns move different directions depending on color*/
        if (this.getColor() == PieceColor.WHITE)
        {
            possibleMovesInitial.add(new RelativePosition(-1, 0, 2));
            possibleMovesInitial.add(new RelativePosition(-1, 1, 1));
            possibleMovesInitial.add(new RelativePosition(-1, -1, 1));

            possibleMovesRegular.add(new RelativePosition(-1, 0, 1));
            possibleMovesRegular.add(new RelativePosition(-1, 1, 1));
            possibleMovesRegular.add(new RelativePosition(-1, -1, 1));
        } else
        {
            possibleMovesInitial.add(new RelativePosition(1, 0, 2));
            possibleMovesInitial.add(new RelativePosition(1, 1, 1));
            possibleMovesInitial.add(new RelativePosition(1, -1, 1));

            possibleMovesRegular.add(new RelativePosition(1, 0, 1));
            possibleMovesRegular.add(new RelativePosition(1, 1, 1));
            possibleMovesRegular.add(new RelativePosition(1, -1, 1));
        }
    }

    /**
     * Return the list of possible moves that corresponds to whether the pawn
     * has been moved before or not.
     * 
     * {@inheritDoc}
     */
    @Override
    List<RelativePosition> getNewPossibleMoves()
    {
        if (this.getHasBeenMoved())
        {
            return this.possibleMovesRegular;
        }

        return this.possibleMovesInitial;
    }
}
