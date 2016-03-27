package main.java;

import java.util.ArrayList;
import java.util.List;

public class Board
{
	static final int GRID_DIMENSION = 8;

	Piece[][] grid;

	public Board()
	{
		this.grid = new Piece[GRID_DIMENSION][GRID_DIMENSION];

		placePiecesForNewGame();
	}

	/**
	 * Place new chess pieces at their respective places in the grid for the
	 * start of a game.
	 */
	void placePiecesForNewGame()
	{
		for (int row = 0; row < GRID_DIMENSION; row++)
		{
			for (int column = 0; column < GRID_DIMENSION; column++)
			{
				placePiece(new NoPiece(), new Position(row, column));
			}
		}

		/*Top row*/
		placePiece(new Rook(PieceColor.BLACK), new Position(0, 0));
		placePiece(new Knight(PieceColor.BLACK), new Position(0, 1));
		placePiece(new Bishop(PieceColor.BLACK), new Position(0, 2));
		placePiece(new Queen(PieceColor.BLACK), new Position(0, 3));
		placePiece(new King(PieceColor.BLACK), new Position(0, 4));
		placePiece(new Bishop(PieceColor.BLACK), new Position(0, 5));
		placePiece(new Knight(PieceColor.BLACK), new Position(0, 6));
		placePiece(new Rook(PieceColor.BLACK), new Position(0, 7));

		/*Second to top row*/
		for (int column = 0; column < GRID_DIMENSION; column++)
		{
			placePiece(new Pawn(PieceColor.BLACK), new Position(1, column));
		}

		/*Second to bottom row*/
		for (int column = 0; column < GRID_DIMENSION; column++)
		{
			placePiece(new Pawn(PieceColor.BLACK), new Position(6, column));
		}

		/*Bottom Row*/
		placePiece(new Rook(PieceColor.BLACK), new Position(7, 0));
		placePiece(new Knight(PieceColor.BLACK), new Position(7, 1));
		placePiece(new Bishop(PieceColor.BLACK), new Position(7, 2));
		placePiece(new Queen(PieceColor.BLACK), new Position(7, 3));
		placePiece(new King(PieceColor.BLACK), new Position(7, 4));
		placePiece(new Bishop(PieceColor.BLACK), new Position(7, 5));
		placePiece(new Knight(PieceColor.BLACK), new Position(7, 6));
		placePiece(new Rook(PieceColor.BLACK), new Position(7, 7));
	}

	/**
	 * Place a single piece at a given position on the grid.
	 * 
	 * @param piece Piece object that will be placed on the grid.
	 * @param position Position on the grid that the piece will be put.
	 */
	private void placePiece(Piece piece, Position position)
	{
		Piece pieceAtDestination = gridLookup(position);

		if (pieceAtDestination.type == PieceType.NO_PIECE)
		{
			this.grid[position.getRow()][position.getColumn()] = piece;
		} else
		{
			//TODO: throw piece already at this location exception
		}
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

	Piece gridLookup(Position position)
	{
		//TODO: Throw index outside of grid exception if the array is indexed out of bounds
		return this.grid[position.getRow()][position.getColumn()];
	}

	//TODO: Create index outside of grid exception

	//TODO: Create piece already at this location exception
}
