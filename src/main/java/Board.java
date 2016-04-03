package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Board
{
	final Logger logger = LoggerFactory.getLogger(Board.class);
	static final int GRID_SIZE = 8;
	private Piece[][] grid;

	public Board()
	{
		this.grid = new Piece[GRID_SIZE][GRID_SIZE];

		for (Piece[] row : this.grid)
		{
			Arrays.fill(row, new NoPiece());
		}

		placePiecesForNewGame();
	}

	public Piece[][] getGrid()
	{
		return this.grid;
	}

	/**
	 * Place new chess pieces at their respective places in the grid for the
	 * start of a game.
	 */
	private void placePiecesForNewGame()
	{
		try
		{
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
			for (int column = 0; column < GRID_SIZE; column++)
			{
				placePiece(new Pawn(PieceColor.BLACK), new Position(1, column));
			}

			/*Second to bottom row.*/
			for (int column = 0; column < GRID_SIZE; column++)
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
		} catch (InvalidPositionException ex)
		{
			logger.error(ex.getMessage());
			System.exit(1);
		}
	}

	/**
	 * Place a single piece at a given position on the grid.
	 * 
	 * @param piece Piece object that will be placed on the grid.
	 * @param position Position on the grid that the piece will be put.
	 */
	private void placePiece(Piece piece, Position position)
	{
		this.grid[position.getRow()][position.getColumn()] = piece;
	}

	/**
	 * Move the piece at the current position to the new position.
	 * 
	 * @param currPosition Location on the grid the piece is currently at.
	 * @param newPosition Location on the grid the piece is to be moved to.
	 * @throws InvalidPositionException If there is a piece already in the
	 *             destination or something else which prevents the piece from
	 *             being moved to the destination.
	 */
	public void movePiece(Position currPosition, Position newPosition)
			throws InvalidPositionException
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
					+ currPosition.getColumn() + " to " + "row=" + newPosition.getRow() + ", col="
					+ newPosition.getColumn();
			throw new InvalidPositionException(msg);
		}
	}

	/**
	 * Determine whether a piece can move from a position to another.
	 * 
	 * @param currPosition Location on the grid the piece is currently at.
	 * @param destinationPosition Location on the grid the piece is moving to.
	 * @return True if the piece is allowed to be moved.
	 */
	boolean isMoveValid(Position currPosition, Position destinationPosition)
	{
		return getValidNewPositions(currPosition).contains(destinationPosition);
	}

	/**
	 * Find the location of the king, and call isCheck.
	 * 
	 * @param color Indication of which color to verify if they're in check.
	 * @return True if given color's king is in check.
	 */
	boolean isCheck(PieceColor color)
	{
		return isCheck(getKingPosition(color));
	}

	/**
	 * Determine if the given color's king is in check.
	 * 
	 * @param kingPosition
	 * @return True if the given color's king is in check.
	 */
	boolean isCheck(Position kingPosition)
	{
		PieceColor color = gridLookup(kingPosition).getColor();

		for (int row = 0; row < Board.GRID_SIZE; row++)
		{
			for (int column = 0; column < Board.GRID_SIZE; column++)
			{
				Position piecePosition = null;
				try
				{
					piecePosition = new Position(row, column);
				} catch (InvalidPositionException ex)
				{
					logger.error(ex.getMessage());
					System.exit(1);
				}
				Piece piece = gridLookup(piecePosition);

				if (piece.getColor() != color
						&& getValidNewPositions(piecePosition).contains(kingPosition))
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
	 * @return True if given color is in checkmate.
	 */
	boolean isCheckmate(PieceColor color)
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
	 * @return The position of the king.
	 */
	Position getKingPosition(PieceColor color)
	{
		for (int row = 0; row < Board.GRID_SIZE; row++)
		{
			for (int column = 0; column < Board.GRID_SIZE; column++)
			{
				Position position = null;
				try
				{
					position = new Position(row, column);
				} catch (InvalidPositionException ex)
				{
					logger.error(ex.getMessage());
					System.exit(1);
				}
				Piece piece = gridLookup(position);

				if (piece.getColor() == color && piece.getType() == PieceType.KING)
				{
					return position;
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
	 * @param currPosition Location at which the piece in questions is located.
	 * @return A list of the valid new positions.
	 */
	List<Position> getValidNewPositions(Position currPosition)
	{
		List<Position> validNewPositions = new ArrayList<Position>();
		Piece pieceToMove = gridLookup(currPosition);
		List<RelativePosition> possibleMoves = pieceToMove.getNewPossibleMoves();

		/*Add each position possible move to the current position. Determine if the combination of the two is a valid place to move to*/
		for (RelativePosition move : possibleMoves)
		{
			for (int step = 1; step <= move.getDistance(); step++)
			{
				int newRow = currPosition.getRow() + (move.getRow() * step);
				int newColumn = currPosition.getColumn() + (move.getColumn() * step);

				/*Confirm the position is within the bounds of the grid*/
				Position candidatePosition;
				try
				{
					candidatePosition = new Position(newRow, newColumn);
				} catch (InvalidPositionException ex)
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

				/*King moves into check*/
				if (pieceToMove.getType() == PieceType.KING && isCheck(candidatePosition))
				{
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
