package main.java;

import java.util.List;

public abstract class Piece
{
	protected PieceColor color;

	protected PieceType type;

	public Piece()
	{
	}

	public Piece(PieceColor color, PieceType type)
	{
		this.color = color;
		this.type = type;
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
	abstract List<RelativePosition> getNewPositionOffsets();

}
