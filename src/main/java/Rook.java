package main.java;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece
{

	public Rook(PieceColor color)
	{
		super(color, PieceType.ROOK);
	}

	@Override
	List<RelativePosition> getNewPositionOffsets() throws IndexOutsideOfGridException
	{
		List<RelativePosition> positionOffsets = new ArrayList<RelativePosition>();

		try
		{
			positionOffsets.add(new RelativePosition(0, 1, Board.GRID_DIMENSION));
			positionOffsets.add(new RelativePosition(1, 0, Board.GRID_DIMENSION));
			positionOffsets.add(new RelativePosition(-1, 0, Board.GRID_DIMENSION));
			positionOffsets.add(new RelativePosition(0, -1, Board.GRID_DIMENSION));
		} catch (IndexOutsideOfGridException ex)
		{
			throw ex;
		}

		return positionOffsets;
	}
}
