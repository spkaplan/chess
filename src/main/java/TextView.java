package main.java;

import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by brand on 3/29/2016.
 */
public class TextView implements Observer 
{
	private final static Logger logger = LoggerFactory.getLogger(TextView.class);
	private final static String NEWLINE = "\n";
	private final static String SPACE = "   ";
	private final static String SMALLSPACE = "  ";

	/**
	 * Retrieves current state of the model and calls drawBoard.
	 */
	@Override
	public void update(Observable o, Object arg)
	{
		Model model = (Model) o;
		model.getExceptionThrown();
		drawHeader(model);
		drawBoard(model.getBoard());
	}
	
	/**	
	 * Takes current model and prints key, turn count and who's turn
	 * @param model
	 */
	void drawHeader(Model model){
		StringBuilder header = new StringBuilder("LOWERCASE: BLACK | UPPERCASE: WHITE");
		header.append(NEWLINE);
		header.append(model.getWhosTurn() + "'S Turn ");
		header.append(NEWLINE);
		header.append("Turn Number: " + model.getTurnCount());
		header.append(NEWLINE);
		System.out.println(header);
	}

	/**
	 * Iterates through board object printing to the console the correct
	 * abbreviation for a given piece and the outer frame of the board.
	 * 
	 * @param board Current board state.
	 */
	void drawBoard(Board board)
	{
		int rowCount = 1;
		System.out.println("     a   b   c   d   e   f   g   h    ");
		System.out.print(SPACE);
		
		for (int i =0; i < Board.GRID_SIZE; i++){
			System.out.print("----");
		}
		System.out.println(SMALLSPACE);
		for (int row = 0; row < Board.GRID_SIZE; row++)
		{
			for (int col = 0; col < Board.GRID_SIZE; col++)
			{
				Position currentPosition = null;
				try
				{
					currentPosition = new Position(row, col);
				} catch (InvalidPositionException ex)
				{
					logger.error(ex.getMessage());
					System.exit(1);
				}
				Piece currentPiece = board.gridLookup(currentPosition);
				String abbreviation = getAbbreviation(currentPiece);
				if (col == 0)
				{
					System.out.print(rowCount + " | " + abbreviation);
					rowCount++;
					
				} else if (col == 7)
				{
					System.out.println(abbreviation + " |");

				} else if (row == 0)
				{
					System.out.print(abbreviation);
					
				} else if (row == 7)
				{
					System.out.print(abbreviation);

				} else	
				{
					System.out.print(abbreviation);
					
				}
			}
		}
		System.out.print(SPACE);
		
		for (int i =0; i < board.GRID_SIZE; i++){
			System.out.print("¯¯¯¯");
		}
		System.out.println(NEWLINE);
	}

	/**
	 * Maps piece to string representation.
	 * 
	 * @param piece
	 * @return String representation of piece.
	 * @throws IllegalArgumentException For invalid piece type.
	 */
	String getAbbreviation(Piece piece)
	{
		PieceType pieceType = piece.getType();
		String pieceAbr ="";
		switch (pieceType)
		{
		case BISHOP:
			pieceAbr = " BI ";
			break;
		case KING:
			pieceAbr = " KI ";
			break;
		case KNIGHT:
			pieceAbr = " KN ";
			break;
		case PAWN:
			pieceAbr = " PA ";
			break;
		case QUEEN:
			pieceAbr = " QU ";
			break;
		case ROOK:
			pieceAbr = " RO ";
			break;
		case NO_PIECE:
			pieceAbr = " .. ";
			break;
		default:
			String msg = "Invalid piece";
			throw new IllegalArgumentException(msg);
		}	
		if (piece.getColor() == PieceColor.BLACK)
		{
			return pieceAbr.toLowerCase();
		}
		return pieceAbr;
	}
}


