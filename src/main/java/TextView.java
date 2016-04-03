package main.java;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by brand on 3/29/2016.
 */
class TextView implements Observer
{

	/**
	 * Retrieves current state of the model and calls drawBoard.
	 */
	@Override
	public void update(Observable o, Object arg)
	{
		Model model = (Model) o;
		try
		{
			drawBoard(model.getBoard());
			System.out.println("CURRENT STATUS MESSAGE: " + model.getStatus());
		} catch (IndexOutsideOfGridException ex)
		{
			//TODO: in feature-5 (logging) remove this try/catch statement
		}

		System.out.println("The view has called refresh");
	}

	/**
	 * Iterates through board object printing to the console the correct
	 * abbreviation for a given piece and the outer frame of the board.
	 * 
	 * @param board Current board state.
	 * @throws IndexOutsideOfGridException
	 */
	void drawBoard(Board board) throws IndexOutsideOfGridException
	{
		for (int row = 0; row < board.GRID_SIZE; row++)
		{
			for (int col = 0; col < board.GRID_SIZE; col++)
			{
				Position currentPosition = new Position(row, col);
				Piece currentPiece = board.gridLookup(currentPosition);
				PieceColor currentPieceColor = currentPiece.getColor();
				String abbreviation = getAbbreviation(currentPiece);
				if (col == 0)
				{
					if (currentPieceColor == PieceColor.BLACK)
					{
						System.out.print("| " + (abbreviation.toLowerCase()));
					} else
					{
						System.out.print("| " + abbreviation);
					}
				} else if (col == 7)
				{
					if (currentPieceColor == PieceColor.BLACK)
					{
						System.out.print(abbreviation + " |");
					} else
					{
						System.out.print(abbreviation + " |");
					}

				} else if (row == 0)
				{
					System.out.println("_");

					if (currentPieceColor == PieceColor.BLACK)
					{
						System.out.print(abbreviation.toLowerCase());
					} else
					{
						System.out.print(abbreviation);
					}
				} else if (row == 7)
				{
					if (currentPieceColor == PieceColor.BLACK)
					{
						System.out.print(abbreviation.toLowerCase());
					} else
					{
						System.out.print(abbreviation);
					}
					System.out.println("_");
				} else
				{
					if (currentPieceColor == PieceColor.BLACK)
					{
						System.out.print(abbreviation.toLowerCase());
					} else
					{
						System.out.print(abbreviation);
					}
				}
			}
		}
	}

	/**
	 * Maps piece to string representation.
	 * 
	 * @param piece
	 * @return String representation of piece.
	 * @throws IllegalArgumentException For invalid piece type.
	 */
	String getAbbreviation(Piece piece) throws IllegalArgumentException
	{
		PieceType pieceType = piece.getType();
		switch (pieceType)
		{
		case BISHOP:
			return " BI ";
		case KING:
			return " KI ";
		case KNIGHT:
			return " KN ";
		case PAWN:
			return " PA ";
		case QUEEN:
			return " QU ";
		case ROOK:
			return " RO ";
		case NO_PIECE:
			return " .. ";
		default:
			throw new IllegalArgumentException("Invalid piece type.");

		}
	}
}
