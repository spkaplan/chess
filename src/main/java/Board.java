package main.java;

import java.util.ArrayList;
import java.util.List;

public class Board
{
	static final int GRID_DIMENSION = 8;

	private Piece[][] grid;

	public Board()
	{
		this.grid = new Piece[GRID_DIMENSION][GRID_DIMENSION];

		try
		{
			placePiecesForNewGame();
		} catch (IndexOutsideOfGridException | CannotPlacePieceException ex)
		{
			//TODO: Decide how to react to this. This should never occur.
			//IndexOutsideOfGridException is thrown if we have wrongly placed a piece when initializing the game.
			//CannotPlacePieceException is thrown if you try to place a piece on top of another piece.
		}
	}

	/**
	 * Place new chess pieces at their respective places in the grid for the
	 * start of a game.
	 */
	private void placePiecesForNewGame() throws CannotPlacePieceException,
			IndexOutsideOfGridException
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
	private void placePiece(Piece piece, Position position) throws CannotPlacePieceException
	{
		Piece pieceAtDestination = gridLookup(position);

		if (pieceAtDestination.getType() == PieceType.NO_PIECE)
		{
			this.grid[position.getRow()][position.getColumn()] = piece;
		} else
		{
			String msg = "Position with row=" + position.getRow() + ", col=" + position.getColumn()
					+ " is not empty.";
			throw new CannotPlacePieceException(msg);
		}
	}

	/**
	 * Move the piece at the current position to the new position.
	 * 
	 * @param currPosition Location on the grid the piece is currently at.
	 * @param newPosition Location on the grid the piece is to be moved to.
	 */
	private void movePiece(Position currPosition, Position newPosition)
			throws CannotPlacePieceException
	{
		Piece pieceToMove = gridLookup(currPosition);
		Piece pieceAtDestination = gridLookup(newPosition);

		/*Make sure there is a piece to move*/
		if (pieceToMove.getType() == PieceType.NO_PIECE)
		{
			String msg = "Position with row=" + newPosition.getRow() + ", col="
					+ newPosition.getColumn() + " is empty.";
			throw new CannotPlacePieceException(msg);
		}

		/*Make sure there the piece in the destination is of the other color*/
		if (pieceAtDestination.getType() != PieceType.NO_PIECE
				&& pieceAtDestination.getColor() == pieceToMove.getColor())
		{
			String msg = "Position with row=" + newPosition.getRow() + ", col="
					+ newPosition.getColumn() + " is of the same color of the moving piece.";
			throw new CannotPlacePieceException(msg);
		}

		this.grid[newPosition.getRow()][newPosition.getColumn()] = pieceToMove;
		this.grid[currPosition.getRow()][currPosition.getColumn()] = new NoPiece();
	}

	/**
	 * Determine whether the given chess color (white/black) is in check.
	 * 
	 * @param color Indication of which color to verify if they're in check.
	 * @return True if given color is in check.
	 */
	boolean isCheck(PieceColor color)
	{
		//TODO: Fill this method
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
		//TODO: Fill this method
		return false;
	}

	/**
	 * Determine the positions that the piece at the given position can legally
	 * move to.
	 * 
	 * @param position Location at which the piece in questions is located.
	 * @return A list of the valid new positions.
	 */
	List<Position> getValidNewPositions(Position currPosition) throws IndexOutsideOfGridException
	{
		List<Position> validNewPositions = new ArrayList<Position>();

		Piece piece = gridLookup(currPosition);

		List<RelativePosition> positionOffsets = piece.getNewPositionOffsets();

		/*Add each position offset to the current position. Make sure new position is empty.*/
		for (RelativePosition offset : positionOffsets)
		{
			for (int step = 0; step < offset.getDistance(); step++)
			{
				int newRow = currPosition.getRow() + (offset.getRow() * step);
				int newColumn = currPosition.getColumn() + (offset.getColumn() * step);
				Position newPosition;
				try
				{
					newPosition = new Position(newRow, newColumn);
				} catch (IndexOutsideOfGridException ex)
				{
					throw ex;
				}

				if (gridLookup(newPosition).getType() == PieceType.NO_PIECE)
				{
					validNewPositions.add(newPosition);
				}
			}
		}

		return null;
	}

	/**
	 * Use the given location to get the piece on the grid at that location.
	 * 
	 * @param position Location at which to inspect the grid.
	 * @return The piece at the given location.
	 */
	Piece gridLookup(Position position)
	{
		return this.grid[position.getRow()][position.getColumn()];
	}
}
