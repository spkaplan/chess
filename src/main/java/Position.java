package main.java;

public class Position
{
	private int row;

	private int column;

	Position(int row, int column)
	{
		if (isValidCoordinate(row) && isValidCoordinate(column))
		{
			this.row = row;
			this.column = column;
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
		return (0 < row && row < Board.GRID_DIMENSION);
	}

	//TODO: Create IndexOutsideOfGridException, and throw it if isValidCoordinate returns false
}
