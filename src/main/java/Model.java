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
	}

    public List<Position> getValidMoves()
    {
        return this.validMoves;
    }

    public Exception getExceptionThrown() {
        return this.exceptionThrown;
    }

    /**
     * Notify the view and clear the temporary state variables that have been set.
     */
    public void notifyObservers() {
        super.notifyObservers();
        clearTempStates();
    }

    /**
     * Clear any temporary state variables that have developed since the last call to refresh view.
     */
    public void clearTempStates() {
        this.exceptionThrown = null;
        this.validMoves = null;
    }

    public void setExceptionThrown(Exception exceptionThrown) {
        this.exceptionThrown = exceptionThrown;
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
     * Moves a piece on the board by interacting with the board. Also, notifies the observers and triggers a view refresh
     * @param curPos the position of the piece you want to move
     * @param newPos the position you are attempting to move the piece to
     */
	void movePiece(Position curPos, Position newPos)
    {
        try {
            board.movePiece(curPos, newPos);
        } catch (InvalidPositionException e) {
            exceptionThrown = e;
        }
        setChanged();
    }

    /**
     * Collects a list of valid moves from the board and updates the valid moves variable. Then, it notifies the
     * observers triggering a view refresh. Finally, clearing validmoves so that it's null next time the model
     * is observed.
     * @param position the position the piece the user requested valid moves for
     */
    void getValidNewPositions(Position position)
    {
        List<Position> validNewPositions = board.getValidNewPositions(position);
        validMoves = validNewPositions;
        setChanged();
    }
}
