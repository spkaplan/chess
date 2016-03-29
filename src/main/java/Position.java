package main.java;

public class Position
{
	private int row;

	private int column;

	Position(int row, int column) throws IndexOutsideOfGridException
	{
		if (isValidCoordinate(row))
		{
			this.row = row;
		} else
		{
			String msg = "Row value=" + row + " is out of bounds.";
			throw new IndexOutsideOfGridException(msg);
		}

		if (isValidCoordinate(column))
		{
			this.column = column;
		} else
		{
			String msg = "Column value=" + column + " is out of bounds.";
			throw new IndexOutsideOfGridException(msg);
		}
	}

	int getRow()
	{
		return this.row;
	}

	int getColumn()
	{
		return this.column;
	}

	/**
	 * Determine if the coordinate is within the bounds of the grid dimensions.
	 * 
	 * @param coordinate Row or column used to place a piece on the board.
	 * @return True if the coordinate is within the bounds.
	 */
	protected boolean isValidCoordinate(int coordinate)
	{
		return (0 <= row && row < Board.GRID_DIMENSION);
	}

	//TODO: Create IndexOutsideOfGridException, and throw it if isValidCoordinate returns false
}
