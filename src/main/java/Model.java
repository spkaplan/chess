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
    private String status;
    private List<Position> validmoves;

	public Model()
	{
		try
		{
			this.board = new Board();
		} catch (CannotPlacePieceException | IndexOutsideOfGridException ex)
		{
			//TODO: when doing feature-5 (logging) remove the try-catch
		}
		this.whosTurn = PieceColor.WHITE;
		this.turnCount = 1;
	}

    public String getStatus()
    {
        return this.status;
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
	public void movePiece(Position curPos, Position newPos)
    {
        setChanged();
		try {
			board.movePiece(curPos, newPos);
		} catch (CannotPlacePieceException | IndexOutsideOfGridException e) {
			status = e.getMessage();
		}
        notifyObservers();
	}

    /**
     * Collects a list of valid moves from the board and updates the valid moves variable. Then, it notifies the
     * observers triggering a view refresh.
     * @param position the position the piece the user requested valid moves for
     */
    public void getValidNewPositions(Position position)
    {
        setChanged();
        try {
            List<Position> validNewPositions = board.getValidNewPositions(position);
            validmoves = validNewPositions;
            status = null;
        } catch (IndexOutsideOfGridException e) {
            status = e.getMessage();
        }
        notifyObservers();
        validmoves = null;
    }
}
