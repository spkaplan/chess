package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import main.java.Board;
import main.java.CannotPlacePieceException;
import main.java.IndexOutsideOfGridException;
import main.java.Piece;
import main.java.PieceColor;
import main.java.PieceType;
import main.java.Position;

import org.junit.Test;

public class BoardTest
{
	@Test
	public void testDimensionsOfGrid()
	{
		Board board = getBoard();

		assertEquals(board.getGrid().length, 8);
		assertEquals(board.getGrid()[0].length, 8);
		assertEquals(board.getGrid()[1].length, 8);
		assertEquals(board.getGrid()[2].length, 8);
		assertEquals(board.getGrid()[3].length, 8);
		assertEquals(board.getGrid()[4].length, 8);
		assertEquals(board.getGrid()[5].length, 8);
		assertEquals(board.getGrid()[6].length, 8);
		assertEquals(board.getGrid()[7].length, 8);
	}

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

	@Test()
	public void testCorrectPiecesAtCorrectLocationsAtBeginningOfGame()
	{
		Board board = getBoard();
		Piece[][] grid = board.getGrid();

		/*Top row. Black royalty.*/
		assertEquals(grid[0][0].getType(), PieceType.ROOK);
		assertEquals(grid[0][0].getColor(), PieceColor.BLACK);
		assertEquals(grid[0][1].getType(), PieceType.KNIGHT);
		assertEquals(grid[0][1].getColor(), PieceColor.BLACK);
		assertEquals(grid[0][2].getType(), PieceType.BISHOP);
		assertEquals(grid[0][2].getColor(), PieceColor.BLACK);
		assertEquals(grid[0][3].getType(), PieceType.QUEEN);
		assertEquals(grid[0][3].getColor(), PieceColor.BLACK);
		assertEquals(grid[0][4].getType(), PieceType.KING);
		assertEquals(grid[0][4].getColor(), PieceColor.BLACK);
		assertEquals(grid[0][5].getType(), PieceType.BISHOP);
		assertEquals(grid[0][5].getColor(), PieceColor.BLACK);
		assertEquals(grid[0][6].getType(), PieceType.KNIGHT);
		assertEquals(grid[0][6].getColor(), PieceColor.BLACK);
		assertEquals(grid[0][7].getType(), PieceType.ROOK);
		assertEquals(grid[0][7].getColor(), PieceColor.BLACK);

		/*Second to top row. Black pawns.*/
		for (int column = 0; column < 8; column++)
		{
			assertEquals(grid[1][column].getType(), PieceType.PAWN);
			assertEquals(grid[1][column].getColor(), PieceColor.BLACK);
		}

		/*Second to bottom row. White pawns.*/
		for (int column = 0; column < 8; column++)
		{
			assertEquals(grid[6][column].getType(), PieceType.PAWN);
			assertEquals(grid[6][column].getColor(), PieceColor.WHITE);
		}

		/*Bottom row. White royalty.*/
		assertEquals(grid[7][0].getType(), PieceType.ROOK);
		assertEquals(grid[7][0].getColor(), PieceColor.WHITE);
		assertEquals(grid[7][1].getType(), PieceType.KNIGHT);
		assertEquals(grid[7][1].getColor(), PieceColor.WHITE);
		assertEquals(grid[7][2].getType(), PieceType.BISHOP);
		assertEquals(grid[7][2].getColor(), PieceColor.WHITE);
		assertEquals(grid[7][3].getType(), PieceType.QUEEN);
		assertEquals(grid[7][3].getColor(), PieceColor.WHITE);
		assertEquals(grid[7][4].getType(), PieceType.KING);
		assertEquals(grid[7][4].getColor(), PieceColor.WHITE);
		assertEquals(grid[7][5].getType(), PieceType.BISHOP);
		assertEquals(grid[7][5].getColor(), PieceColor.WHITE);
		assertEquals(grid[7][6].getType(), PieceType.KNIGHT);
		assertEquals(grid[7][6].getColor(), PieceColor.WHITE);
		assertEquals(grid[7][7].getType(), PieceType.ROOK);
		assertEquals(grid[7][7].getColor(), PieceColor.WHITE);
	}

	@Test
	public void testMovePawnsLegally() throws IndexOutsideOfGridException
	{
		Board board1 = getBoard();

		/*ONE(1) space, then ONE(1) space again*/
		for (int column = 0; column < 8; column++)
		{
			/*Black pawns*/
			/*First move*/
			try
			{
				board1.movePiece(new Position(1, column), new Position(2, column));
			} catch (CannotPlacePieceException ex)
			{
				fail(ex.getLocalizedMessage());
			}
			/*Second move*/
			try
			{
				board1.movePiece(new Position(2, column), new Position(3, column));
			} catch (CannotPlacePieceException ex)
			{
				fail(ex.getLocalizedMessage());
			}

			/*White pawns*/
			/*First move*/
			try
			{
				board1.movePiece(new Position(6, column), new Position(5, column));
			} catch (CannotPlacePieceException ex)
			{
				fail(ex.getLocalizedMessage());
			}
			/*Second move*/
			try
			{
				board1.movePiece(new Position(5, column), new Position(4, column));
			} catch (CannotPlacePieceException ex)
			{
				fail(ex.getLocalizedMessage());
			}
		}

		/*New simulation. Generate new board.*/
		Board board2 = getBoard();

		/*TWO(2) spaces, then ONE(1) space*/
		for (int column = 0; column < 8; column++)
		{
			/*Black pawns*/
			/*First move*/
			try
			{
				board2.movePiece(new Position(1, column), new Position(3, column));
			} catch (CannotPlacePieceException ex)
			{
				fail(ex.getLocalizedMessage());
			}
			/*Second move*/
			try
			{
				board2.movePiece(new Position(3, column), new Position(4, column));
			} catch (CannotPlacePieceException ex)
			{
				fail(ex.getLocalizedMessage());
			}

			/*White pawns*/
			/*First move*/
			try
			{
				board2.movePiece(new Position(6, column), new Position(4, column));
			} catch (CannotPlacePieceException ex)
			{
				fail(ex.getLocalizedMessage());
			}
			/*Second move*/
			try
			{
				board2.movePiece(new Position(4, column), new Position(3, column));
			} catch (CannotPlacePieceException ex)
			{
				fail(ex.getLocalizedMessage());
			}
		}
	}

	@Test
	public void testMovePawnsIllegally() throws IndexOutsideOfGridException
	{
		Board board = getBoard();
		int numErrors = 0;

		/*ONE(1) space, then TWO(2) spaces*/
		for (int column = 0; column < 8; column++)
		{
			/*Black pawns*/
			/*First move*/
			try
			{
				board.movePiece(new Position(1, column), new Position(2, column));
			} catch (CannotPlacePieceException ex)
			{
				numErrors += 1;
			}
			/*Second move*/
			try
			{
				board.movePiece(new Position(2, column), new Position(4, column));
			} catch (CannotPlacePieceException ex)
			{
				numErrors += 1;
			}

			/*White pawns*/
			/*First move*/
			try
			{
				board.movePiece(new Position(6, column), new Position(5, column));
			} catch (CannotPlacePieceException ex)
			{
				numErrors += 1;
			}
			/*Second move*/
			try
			{
				board.movePiece(new Position(5, column), new Position(3, column));
			} catch (CannotPlacePieceException ex)
			{
				numErrors += 1;
			}
		}
		/*16 b/c there are 16 pawns that get moved illegally*/
		assertEquals(numErrors, 16);
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
