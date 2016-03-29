package main.java;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece
{
	List<RelativePosition> positionOffsets;

	public Knight(PieceColor color) throws IndexOutsideOfGridException
	{
		super(color, PieceType.KNIGHT);
		positionOffsets = new ArrayList<RelativePosition>();

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
	}

	@Override
	List<RelativePosition> getNewPositionOffsets()
	{
		return this.positionOffsets;
	}
}
