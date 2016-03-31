package main.java;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece
{
	List<RelativePosition> positionOffsetsInitial;
	List<RelativePosition> positionOffsetsRegular;
	boolean hasBeenMoved = false;

	public Pawn(PieceColor color)
	{
		super(color, PieceType.PAWN);
		positionOffsetsInitial = new ArrayList<RelativePosition>();
		positionOffsetsRegular = new ArrayList<RelativePosition>();

		/*Pawns move different directions depending on color*/
		if (this.getColor() == PieceColor.WHITE)
		{
			positionOffsetsInitial.add(new RelativePosition(-1, 0, 2));
			positionOffsetsRegular.add(new RelativePosition(-1, 0, 1));
		} else
		{
			positionOffsetsInitial.add(new RelativePosition(1, 0, 2));
			positionOffsetsRegular.add(new RelativePosition(1, 0, 1));
		}
	}

	/**
	 * Return the position offsets list that corresponds to whether the pawn has
	 * been moved before or not.
	 * 
	 * {@inheritDoc}
	 */
	@Override
	List<RelativePosition> getNewPositionOffsets()
	{
		if (this.hasBeenMoved)
		{
			return this.positionOffsetsRegular;
		}

		return this.positionOffsetsInitial;
	}

	public void setHasBeenMoved(boolean hasBeenMoved)
	{
		this.hasBeenMoved = hasBeenMoved;
	}
}
