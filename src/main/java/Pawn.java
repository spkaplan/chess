package main.java;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece
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
            possibleMovesRegular.add(new RelativePosition(-1, 0, 1));
        } else
        {
            possibleMovesInitial.add(new RelativePosition(1, 0, 2));
            possibleMovesRegular.add(new RelativePosition(1, 0, 1));
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
