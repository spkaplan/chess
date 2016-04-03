package main.java;

import java.util.Observable;

/**
 * Created by brandon on 3/29/2016 at 4:44 PM as part of the chess project.
 */
class Model extends Observable
{
	private Board board;
	private PieceColor whosTurn;
	private int turnCount;
    private String status;

	Model()
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

	Board getBoard()
	{
		return this.board;
	}

	PieceColor getWhosTurn()
	{
		return this.whosTurn;
	}

	int getTurnCount()
	{
		return this.turnCount;
	}

	void movePiece(Position curPos, Position newPos) {
        setChanged();
		try {
			board.movePiece(curPos, newPos);
		} catch (CannotPlacePieceException | IndexOutsideOfGridException e) {
			status = e.getMessage();
		}
        notifyObservers();
	}

    void getValidNewPositions(Position position) {
        try {
            status = "Valid positions for " + board.getValidNewPositions(position).toString();
        } catch (IndexOutsideOfGridException e) {
            status = e.getMessage();
        }
    }
}
