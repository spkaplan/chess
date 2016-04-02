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

	void incomingAction(String action)
	{
		System.out.println(action);
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
}
