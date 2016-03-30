package main.java;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece
{
	List<RelativePosition> positionOffsets;

	public Bishop(PieceColor color)
	{
		super(color, PieceType.BISHOP);
		positionOffsets = new ArrayList<RelativePosition>();

		positionOffsets.add(new RelativePosition(1, 1, Board.GRID_DIMENSION));
		positionOffsets.add(new RelativePosition(-1, 1, Board.GRID_DIMENSION));
		positionOffsets.add(new RelativePosition(1, -1, Board.GRID_DIMENSION));
		positionOffsets.add(new RelativePosition(-1, -1, Board.GRID_DIMENSION));
	}

	@Override
	List<RelativePosition> getNewPositionOffsets()
	{
		return this.positionOffsets;
	}
}
