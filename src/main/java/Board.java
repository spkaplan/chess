package main.java;

import java.util.ArrayList;
import java.util.List;

//TODO: Create crash file. Use this file to write errors that are not from user input, such as creating a position that is invalid due to error in our code.
//This will get rid of throwing exceptions that the user did not cause

public class Board
{
	static final int GRID_DIMENSION = 8;
	private Piece[][] grid;

	public Board() throws IndexOutsideOfGridException, CannotPlacePieceException
	{
		this.grid = new Piece[GRID_DIMENSION][GRID_DIMENSION];
		placePiecesForNewGame();
	}

	public Piece[][] getGrid()
	{
		return this.grid;
	}

	/**
	 * Place new chess pieces at their respective places in the grid for the
	 * start of a game.
	 * 
	 * @throws CannotPlacePieceException If there is a piece already in the
	 *             destination which prevents the piece from being moved.
	 * @throws IndexOutsideOfGridException If the piece tries to create a new
	 *             position that is off the grid.
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

		/*Second to bottom row.*/
		for (int column = 0; column < GRID_DIMENSION; column++)
		{
			placePiece(new Pawn(PieceColor.WHITE), new Position(6, column));
		}

		/*Bottom Row*/
		placePiece(new Rook(PieceColor.WHITE), new Position(7, 0));
		placePiece(new Knight(PieceColor.WHITE), new Position(7, 1));
		placePiece(new Bishop(PieceColor.WHITE), new Position(7, 2));
		placePiece(new Queen(PieceColor.WHITE), new Position(7, 3));
		placePiece(new King(PieceColor.WHITE), new Position(7, 4));
		placePiece(new Bishop(PieceColor.WHITE), new Position(7, 5));
		placePiece(new Knight(PieceColor.WHITE), new Position(7, 6));
		placePiece(new Rook(PieceColor.WHITE), new Position(7, 7));
	}

	/**
	 * Place a single piece at a given position on the grid.
	 * 
	 * @param piece Piece object that will be placed on the grid.
	 * @param position Position on the grid that the piece will be put.
	 * @throws CannotPlacePieceException If there is a piece already in the
	 *             destination which prevents the piece from being moved.
	 */
	private void placePiece(Piece piece, Position position) throws CannotPlacePieceException
	{
		Piece pieceAtDestination = gridLookup(position);

		if (pieceAtDestination == null || pieceAtDestination.getType() == PieceType.NO_PIECE)
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
	 * @throws CannotPlacePieceException If there is a piece already in the
	 *             destination or something else which prevents the piece from
	 *             being moved to the destination.
	 */
	public void movePiece(Position currPosition, Position newPosition)
			throws CannotPlacePieceException
	{
		Piece pieceToMove = gridLookup(currPosition);

		if (isMoveValid(currPosition, newPosition))
		{
			this.grid[newPosition.getRow()][newPosition.getColumn()] = pieceToMove;
			this.grid[currPosition.getRow()][currPosition.getColumn()] = new NoPiece();

			if (pieceToMove.getType() == PieceType.PAWN)
			{
				Pawn pawn = (Pawn) pieceToMove;
				pawn.setHasBeenMoved(true);
			}
		} else
		{
			String msg = "Unable to move piece from row=" + currPosition.getRow() + ", col="
					+ currPosition.getColumn() + " to " + newPosition.getRow() + ", col="
					+ newPosition.getColumn();
			throw new CannotPlacePieceException(msg);
		}
	}

	/**
	 * Determine whether a piece can move from a position to another.
	 * 
	 * @param currPosition Location on the grid the piece is currently at.
	 * @param newPosition Location on the grid the piece is to be moved to.
	 * @return True if the piece is allowed to be moved.
	 */
	boolean isMoveValid(Position currPosition, Position destinationPosition)
	{
		return getValidNewPositions(currPosition).contains(destinationPosition);
	}

	/**
	 * Determine whether the given chess color (white/black) is in check.
	 * 
	 * @param color Indication of which color to verify if they're in check.
	 * @throws IndexOutsideOfGridException
	 * @return True if given color is in check.
	 */
	boolean isCheck(PieceColor color) throws IndexOutsideOfGridException
	{
		Position kingPosition = getKingPosition(color);

		for (int row = 0; row < Board.GRID_DIMENSION; row++)
		{
			for (int column = 0; column < Board.GRID_DIMENSION; column++)
			{
				Piece piece = gridLookup(new Position(row, column));

				if (piece.getColor() != color
						&& getValidNewPositions(new Position(row, column)).contains(kingPosition))
				{
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * Determine whether the given chess color (white/black) is in checkmate.
	 * 
	 * @param color Indication of which color to verify if they're in checkmate.
	 * @throws IndexOutsideOfGridException
	 * @return True if given color is in checkmate.
	 */
	boolean isCheckmate(PieceColor color) throws IndexOutsideOfGridException
	{
		Position kingPosition = getKingPosition(color);

		if (isCheck(color) && getValidNewPositions(kingPosition).size() == 0)
		{
			return true;
		}

		return false;
	}

	/**
	 * Find the location on the board of the king of a given color.
	 * 
	 * @param color The color of the king to be located.
	 * @throws IndexOutsideOfGridException
	 * @return The position of the king.
	 */
	Position getKingPosition(PieceColor color) throws IndexOutsideOfGridException
	{
		for (int row = 0; row < Board.GRID_DIMENSION; row++)
		{
			for (int column = 0; column < Board.GRID_DIMENSION; column++)
			{
				Piece piece = gridLookup(new Position(row, column));
				if (piece.getColor() == color && piece.getType() == PieceType.KING)
				{
					return new Position(row, column);
				}
			}
		}
		//This should never be reached, because there will always be a king on the board.
		return null;
	}

	/**
	 * Determine the positions that the piece at the given position can legally
	 * move to.
	 * 
	 * @param position Location at which the piece in questions is located.
	 * @return A list of the valid new positions.
	 */
	List<Position> getValidNewPositions(Position currPosition)
	{
		List<Position> validNewPositions = new ArrayList<Position>();
		Piece pieceToMove = gridLookup(currPosition);
		List<RelativePosition> positionOffsets = pieceToMove.getNewPositionOffsets();

		/*Add each position offset to the current position. Determine if the combination of the two is a valid place to move to*/
		for (RelativePosition offset : positionOffsets)
		{
			for (int step = 1; step <= offset.getDistance(); step++)
			{
				int newRow = currPosition.getRow() + (offset.getRow() * step);
				int newColumn = currPosition.getColumn() + (offset.getColumn() * step);

				/*Confirm the position is within the bounds of the grid*/
				Position candidatePosition;
				try
				{
					candidatePosition = new Position(newRow, newColumn);
				} catch (IndexOutsideOfGridException ex)
				{
					break;
				}

				/*Position has piece of same color*/
				if (gridLookup(candidatePosition).getType() != PieceType.NO_PIECE
						&& pieceToMove.getColor() == gridLookup(candidatePosition).getColor())
				{
					break;
				}

				/*Position has a king of the opposite color*/
				if (gridLookup(candidatePosition).getType() != PieceType.NO_PIECE
						&& pieceToMove.getColor() != gridLookup(candidatePosition).getColor()
						&& gridLookup(candidatePosition).getType() == PieceType.KING)
				{
					break;
				}

				/*Position has piece of opposite color*/
				if (gridLookup(candidatePosition).getType() != PieceType.NO_PIECE
						&& pieceToMove.getColor() != gridLookup(candidatePosition).getColor())
				{
					/*The moving piece can advance to the position, but no further.*/
					validNewPositions.add(candidatePosition);
					break;
				}
				validNewPositions.add(candidatePosition);
			}
		}
		return validNewPositions;
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
