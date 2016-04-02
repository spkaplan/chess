package main.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

/**
 * Created by brandon on 3/29/2016 at 4:44 PM as part of the chess project.
 */
class Model
{
	private Board board;
	private PieceColor whosTurn;
	private List<Observer> observers;
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
		this.observers = new ArrayList<>();
		this.turnCount = 1;
	}

	void addObserver(Observer observer)
	{
		observers.add(observer);
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

	Model getState()
	{
		return this;
	}
}
