package main.java;

public class Position
{
	private int row;

	private int column;

	public Position(int row, int column) throws IndexOutsideOfGridException
	{
		/*Check row is in valid range*/
		if (isValidCoordinate(row))
		{
			this.row = row;
		} else
		{
			String msg = "Row value=" + row + " is out of bounds.";
			throw new IndexOutsideOfGridException(msg);
		}

		/*Check column is in valid range*/
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
		return (0 <= coordinate && coordinate < Board.GRID_DIMENSION);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (!(obj instanceof Position))
		{
			return false;
		}
		Position other = (Position) obj;
		if (this.column != other.column)
		{
			return false;
		}
		if (this.row != other.row)
		{
			return false;
		}
		return true;
	}
}
