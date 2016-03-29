package main.java;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece
{
	List<RelativePosition> positionOffsets;

	public King(PieceColor color) throws IndexOutsideOfGridException
	{
		super(color, PieceType.KING);
		positionOffsets = new ArrayList<RelativePosition>();

		try
		{
			positionOffsets.add(new RelativePosition(1, 1, 1));
			positionOffsets.add(new RelativePosition(0, 1, 1));
			positionOffsets.add(new RelativePosition(1, 0, 1));
			positionOffsets.add(new RelativePosition(1, -1, 1));
			positionOffsets.add(new RelativePosition(-1, 0, 1));
			positionOffsets.add(new RelativePosition(-1, -1, 1));
			positionOffsets.add(new RelativePosition(0, -1, 1));
			positionOffsets.add(new RelativePosition(-1, 1, 1));
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
