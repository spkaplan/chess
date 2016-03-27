package main.java;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece
{

	public Bishop(PieceColor color)
	{
		super(color, PieceType.BISHOP);
	}

	@Override
	List<RelativePosition> getNewPositionOffsets()
	{
		List<RelativePosition> positionOffsets = new ArrayList<RelativePosition>();
		positionOffsets.add(new RelativePosition(1, 1, Board.GRID_DIMENSION));
		positionOffsets.add(new RelativePosition(-1, 1, Board.GRID_DIMENSION));
		positionOffsets.add(new RelativePosition(1, -1, Board.GRID_DIMENSION));
		positionOffsets.add(new RelativePosition(-1, -1, Board.GRID_DIMENSION));
		return positionOffsets;
	}
}
