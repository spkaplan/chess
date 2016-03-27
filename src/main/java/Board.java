package main.java;

import java.util.ArrayList;
import java.util.List;

public class Board
{
	private static final int GRID_DIMENSION = 8;

	Piece[][] grid;

	public Board()
	{
		grid = new Piece[GRID_DIMENSION][GRID_DIMENSION];
	}

	/**
	 * Place new chess pieces at their respective places in the grid for the
	 * start of a game.
	 */
	void placePiecesForNewGame()
	{

	}

	/**
	 * Place a single piece at a given position on the grid.
	 * 
	 * @param piece Piece object that will be placed on the grid.
	 * @param position Position on the grid that the piece will be put.
	 */
	private void placePiece(Piece piece, Position position)
	{

	}

	/**
	 * Move the piece at the current position to the new position.
	 * 
	 * @param currPosition
	 * @param newPosition
	 */
	void movePiece(Position currPosition, Position newPosition)
	{

	}

	/**
	 * Determine whether the given chess color (white/black) is in check.
	 * 
	 * @param color Indication of which color to verify if they're in check.
	 * @return True if given color is in check.
	 */
	boolean isCheck(PieceColor color)
	{
		return false;
	}

	/**
	 * Determine whether the given chess color (white/black) is in checkmate.
	 * 
	 * @param color Indication of which color to verify if they're in checkmate.
	 * @return True if given color is in checkmate.
	 */
	boolean isCheckmate(PieceColor color)
	{
		return false;
	}

	/**
	 * Determine the positions that the piece at the given position can move to.
	 * 
	 * @param position
	 * @return A list of the valid new positions.
	 */
	List<Position> getValidNewPositions(Position position)
	{
		return new ArrayList<Position>();
	}
}
