package main.java;

public class RelativePosition extends Position
{
	private int distance;

	RelativePosition(int row, int column, int distance)
	{
		super(row, column);
		this.distance = distance;
	}

	int getDistance()
	{
		return this.distance;
	}
}
