package main.java;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece
{

	public Knight(PieceColor color)
	{
		super(color, PieceType.KNIGHT);
	}

	@Override
	List<RelativePosition> getNewPositionOffsets() throws IndexOutsideOfGridException
	{
		List<RelativePosition> positionOffsets = new ArrayList<RelativePosition>();

		try
		{
			positionOffsets.add(new RelativePosition(1, 2, 1));
			positionOffsets.add(new RelativePosition(2, 1, 1));
			positionOffsets.add(new RelativePosition(1, -2, 1));
			positionOffsets.add(new RelativePosition(2, -1, 1));
			positionOffsets.add(new RelativePosition(-1, 2, 1));
			positionOffsets.add(new RelativePosition(-2, 1, 1));
			positionOffsets.add(new RelativePosition(-1, -2, 1));
			positionOffsets.add(new RelativePosition(-2, -1, 1));
		} catch (IndexOutsideOfGridException ex)
		{
			throw ex;
		}

		return positionOffsets;
	}
}
