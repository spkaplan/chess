package test.java;

import static org.junit.Assert.fail;
import main.java.Bishop;
import main.java.Board;
import main.java.CannotPlacePieceException;
import main.java.IndexOutsideOfGridException;
import main.java.King;
import main.java.Knight;
import main.java.Pawn;
import main.java.Piece;
import main.java.PieceColor;
import main.java.PieceType;
import main.java.Queen;
import main.java.Rook;

import org.junit.Test;

public class BoardTest
{

	@Test
	public void testNumPiecesOnBoardAtBeginningOfGame()
	{
		int numPieces = 0;
		Board board = getBoard();

		Piece[][] grid = board.getGrid();

		for (int row = 0; row < 8; row++)
		{
			for (int column = 0; column < 8; column++)
			{
				if (!grid[0][0].equals(PieceType.NO_PIECE))
				{
					numPieces += 1;
				}
			}
		}
		assert (numPieces == 32);
	}

	@Test
	public void testCorrectPiecesAtCorrectLocationsAtBeginningOfGame()
	{
		int numPieces = 0;
		Board board = getBoard();

		Piece[][] grid = board.getGrid();

		/*Top row. Black royalty.*/
		assert (grid[0][0].equals(new Rook(PieceColor.BLACK)));
		assert (grid[0][1].equals(new Knight(PieceColor.BLACK)));
		assert (grid[0][2].equals(new Bishop(PieceColor.BLACK)));
		assert (grid[0][3].equals(new Queen(PieceColor.BLACK)));
		assert (grid[0][4].equals(new King(PieceColor.BLACK)));
		assert (grid[0][5].equals(new Bishop(PieceColor.BLACK)));
		assert (grid[0][6].equals(new Knight(PieceColor.BLACK)));
		assert (grid[0][7].equals(new Rook(PieceColor.BLACK)));

		/*Second to top row. Black pawns.*/
		assert (grid[1][0].equals(new Pawn(PieceColor.BLACK)));
		assert (grid[1][1].equals(new Pawn(PieceColor.BLACK)));
		assert (grid[1][2].equals(new Pawn(PieceColor.BLACK)));
		assert (grid[1][3].equals(new Pawn(PieceColor.BLACK)));
		assert (grid[1][4].equals(new Pawn(PieceColor.BLACK)));
		assert (grid[1][5].equals(new Pawn(PieceColor.BLACK)));
		assert (grid[1][6].equals(new Pawn(PieceColor.BLACK)));
		assert (grid[1][7].equals(new Pawn(PieceColor.BLACK)));

		/*Second to bottom row. White pawns.*/
		assert (grid[6][0].equals(new Pawn(PieceColor.WHITE)));
		assert (grid[6][1].equals(new Pawn(PieceColor.WHITE)));
		assert (grid[6][2].equals(new Pawn(PieceColor.WHITE)));
		assert (grid[6][3].equals(new Pawn(PieceColor.WHITE)));
		assert (grid[6][4].equals(new Pawn(PieceColor.WHITE)));
		assert (grid[6][5].equals(new Pawn(PieceColor.WHITE)));
		assert (grid[6][6].equals(new Pawn(PieceColor.WHITE)));
		assert (grid[6][7].equals(new Pawn(PieceColor.WHITE)));

		/*Bottom row. White royalty.*/
		assert (grid[7][0].equals(new Rook(PieceColor.WHITE)));
		assert (grid[7][1].equals(new Knight(PieceColor.WHITE)));
		assert (grid[7][2].equals(new Bishop(PieceColor.WHITE)));
		assert (grid[7][3].equals(new Queen(PieceColor.WHITE)));
		assert (grid[7][4].equals(new King(PieceColor.WHITE)));
		assert (grid[7][5].equals(new Bishop(PieceColor.WHITE)));
		assert (grid[7][6].equals(new Knight(PieceColor.WHITE)));
		assert (grid[7][7].equals(new Rook(PieceColor.WHITE)));
	}

	@Test
	public void testDimensionsOfGrid()
	{
		int numPieces = 0;
		Board board = getBoard();

		assert (board.getGrid().length == 8);
		assert (board.getGrid()[0].length == 8);
		assert (board.getGrid()[1].length == 8);
		assert (board.getGrid()[2].length == 8);
		assert (board.getGrid()[3].length == 8);
		assert (board.getGrid()[4].length == 8);
		assert (board.getGrid()[5].length == 8);
		assert (board.getGrid()[6].length == 8);
		assert (board.getGrid()[7].length == 8);
	}

	private Board getBoard()
	{
		Board board = null;

		try
		{
			board = new Board();
		} catch (CannotPlacePieceException | IndexOutsideOfGridException ex)
		{
			String msg = "Grid failed to be created properly.";
			fail(msg);
		}
		return board;
	}
}
