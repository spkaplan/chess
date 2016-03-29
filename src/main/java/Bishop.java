package main.java;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece
{
	List<RelativePosition> positionOffsets;

	public Bishop(PieceColor color) throws IndexOutsideOfGridException
	{
		super(color, PieceType.BISHOP);
		positionOffsets = new ArrayList<RelativePosition>();

		try
		{
			positionOffsets.add(new RelativePosition(1, 1, Board.GRID_DIMENSION));
			positionOffsets.add(new RelativePosition(-1, 1, Board.GRID_DIMENSION));
			positionOffsets.add(new RelativePosition(1, -1, Board.GRID_DIMENSION));
			positionOffsets.add(new RelativePosition(-1, -1, Board.GRID_DIMENSION));
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
