package main.java;

import java.io.Serializable;
import java.util.List;

public abstract class Piece implements Serializable
{
    private PieceColor color;

    private PieceType type;

    private boolean hasBeenMoved = false;

    public Piece(PieceType type)
    {
        this.type = type;
    }

    public Piece(PieceColor color, PieceType type)
    {
        this.color = color;
        this.type = type;
    }

    public PieceColor getColor()
    {
        return this.color;
    }

    public PieceType getType()
    {
        return this.type;
    }

    public boolean getHasBeenMoved()
    {
        return this.hasBeenMoved;
    }

    public void setHasBeenMoved(boolean hasBeenMoved)
    {
        this.hasBeenMoved = hasBeenMoved;
    }

    /**
     * Determine the valid positions that the chess piece can move RELATIVE to
     * their current position.
     * 
     * @return A list of valid possible moves.
     */
    abstract List<RelativePosition> getNewPossibleMoves();
}
