package main.java;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece
{
	List<RelativePosition> positionOffsets;

	public Pawn(PieceColor color) throws IndexOutsideOfGridException
	{
		super(color, PieceType.PAWN);

		positionOffsets = new ArrayList<RelativePosition>();
		try
		{
			positionOffsets.add(new RelativePosition(0, 1, 1));
		} catch (IndexOutsideOfGridException ex)
		{
			throw ex;
		}
	}

	@Override
	List<RelativePosition> getNewPositionOffsets()
	{
		return this.positionOffsets;
	}
}
