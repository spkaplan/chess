package main.java;

import java.util.List;

public abstract class Piece
{
	private PieceColor color;

	private PieceType type;

	public Piece(PieceType type)
	{
		this.type = type;
	}

	public Piece(PieceColor color, PieceType type)
	{
		this.color = color;
		this.type = type;
	}

	public PieceColor getColor()
	{
		return this.color;
	}

	public PieceType getType()
	{
		return this.type;
	}

	/**
	 * Determine the valid positions that the chess piece can move RELATIVE to
	 * their current position.
	 * 
	 * @return A list of valid position offsets.
	 */
	abstract List<RelativePosition> getNewPositionOffsets();

}
