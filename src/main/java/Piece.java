package main.java;

import java.util.List;

public abstract class Piece
{
	private PieceColor color;

	private PieceType type;

	public Piece()
	{

	}

	void setColor()
	{

	}

	void setType()
	{

	}

	PieceColor getColor()
	{
		return this.color;
	}

	PieceType getType()
	{
		return this.type;
	}

	/**
	 * Determine the valid positions that the chess piece can move RELATIVE to
	 * their current position.
	 * 
	 * @return A list of these valid position offsets.
	 */
	abstract List<Position> getNewPositionOffsets();
}
