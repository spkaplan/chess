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
    private boolean isCheck;
    private boolean isCheckmate;

    public Model()
    {
        this.board = new Board();
        this.whosTurn = PieceColor.WHITE;
        this.turnCount = 1;
        setChanged();
    }

    public boolean getIsCheck()
    {
        return this.isCheck;
    }

    public boolean getIsCheckmate()
    {
        return this.isCheckmate;
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
        this.isCheck = false;
        this.isCheckmate = false;
    }

    public void setIsCheck(boolean isCheck)
    {
        this.isCheck = isCheck;
        setChanged();
    }

    public void setIsCheckmate(boolean isCheckmate)
    {
        this.isCheckmate = isCheckmate;
        setChanged();
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
     * Moves the piece at the current location to the new location.
     * 
     * @param curPos The position of the piece you want to move.
     * @param newPos The position you are attempting to move the piece to.
     * @throws InvalidPositionException
     */
    public void movePiece(Position curPos, Position newPos) throws IllegalArgumentException, InvalidPositionException
    {
        Piece pieceToMove = board.gridLookup(curPos);

        if (pieceToMove.getType() == PieceType.NO_PIECE)
        {
            String msg = "Cannot move a piece from an empty board position.";
            throw new IllegalArgumentException(msg);
        }
        if (pieceToMove.getColor() != this.whosTurn)
        {
            String msg = "Cannot move piece of the other color.";
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
     * @return A list of the valid positions for the given piece to move to.
     */
    List<Position> getValidNewPositions(Position position)
    {
        return this.board.getValidNewPositions(position);
    }

    /**
     * Perform the move castling move.
     * 
     * @param position1 A position of either the king or rook.
     * @param position2 A position of either the king or rook.
     * @throws InvalidPositionException
     */
    void castle(Position position1, Position position2) throws InvalidPositionException
    {
        this.board.castle(position1, position2);

        setChanged();
    }

    boolean isCheck()
    {
        return this.board.isCheck(this.whosTurn);
    }

    boolean isCheckmate()
    {
        return this.board.isCheckmate(this.whosTurn);
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
