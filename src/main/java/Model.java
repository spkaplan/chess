package main.java;

import java.util.List;
import java.util.Observable;

/**
 * Created by brandon on 3/29/2016 at 4:44 PM as part of the chess project.
 */
public class Model extends Observable
{
    private Board board;
    private PieceColor whosTurn;
    private int turnCount;
    private List<Position> validMoves;
    private Exception exceptionThrown;

    public Model()
    {
        this.board = new Board();
        this.whosTurn = PieceColor.WHITE;
        this.turnCount = 1;
        setChanged();
    }

    public List<Position> getValidMoves()
    {
        return this.validMoves;
    }

    public Exception getExceptionThrown()
    {
        return this.exceptionThrown;
    }

    public Board getBoard()
    {
        return this.board;
    }

    public PieceColor getWhosTurn()
    {
        return this.whosTurn;
    }

    public int getTurnCount()
    {
        return this.turnCount;
    }

    /**
     * Notify the view and clear the temporary state variables that have been
     * set.
     */
    public void notifyObservers()
    {
        super.notifyObservers();
        clearTempStates();
    }

    /**
     * Clear any temporary state variables that have developed since the last
     * call to refresh view.
     */
    public void clearTempStates()
    {
        this.exceptionThrown = null;
        this.validMoves = null;
    }

    public void setExceptionThrown(Exception exceptionThrown)
    {
        this.exceptionThrown = exceptionThrown;
        setChanged();
    }

    /**
     * Moves the piece at the current location to the new location.
     * 
     * @param curPos The position of the piece you want to move.
     * @param newPos The position you are attempting to move the piece to.
     * @throws InvalidPositionException
     */
    public void movePiece(Position curPos, Position newPos) throws IllegalArgumentException, InvalidPositionException
    {
        Piece pieceToMove = board.gridLookup(curPos);

        if (pieceToMove.getColor() != this.whosTurn)
        {
            String msg = "Cannot move piece of the other color";
            throw new IllegalArgumentException(msg);
        }
        board.movePiece(curPos, newPos);

        setChanged();
    }

    /**
     * Collects a list of valid moves from the board and updates the valid moves
     * variable. Then, it notifies the observers triggering a view refresh.
     * Finally, clearing validmoves so that it's null next time the model is
     * observed.
     * 
     * @param position the position of the piece the user requested valid moves
     *            for
     */
    void getValidNewPositions(Position position)
    {
        List<Position> validNewPositions = this.board.getValidNewPositions(position);
        this.validMoves = validNewPositions;
        setChanged();
    }

    /**
     * Perform the move castling move.
     * 
     * @param position1 A position of either the king or rook.
     * @param position2 A position of either the king or rook.
     */
    void castle(Position position1, Position position2) throws IllegalArgumentException
    {
        this.board.castle(position1, position2);

        setChanged();
    }

    void incrementTurnCount()
    {
        this.turnCount = this.turnCount + 1;
    }

    /**
     * Change who's turn it is.
     */
    void switchWhosTurn()
    {
        if (this.whosTurn == PieceColor.WHITE)
        {
            this.whosTurn = PieceColor.BLACK;
        } else
        {
            this.whosTurn = PieceColor.WHITE;
        }
    }
}
