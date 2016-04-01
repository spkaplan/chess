package main.java;

public class RelativePosition
{
	private int row;
	private int column;
	private int distance;

	RelativePosition(int row, int column, int distance)
	{
		this.row = row;
		this.column = column;
		this.distance = distance;
	}

	public int getRow()
	{
		return this.row;
	}

	public int getColumn()
	{
		return this.column;
	}

	int getDistance()
	{
		return this.distance;
	}
}
