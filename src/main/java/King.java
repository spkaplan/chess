package main.java;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece
{

	public King(PieceColor color)
	{
		super(color, PieceType.KING);
	}

	@Override
	List<RelativePosition> getNewPositionOffsets() throws IndexOutsideOfGridException
	{
		List<RelativePosition> positionOffsets = new ArrayList<RelativePosition>();

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

		return positionOffsets;
	}
}
