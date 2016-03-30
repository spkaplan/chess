package main.java;

import java.util.ArrayList;
import java.util.List;

public class Rook extends Piece
{
	List<RelativePosition> positionOffsets;

	public Rook(PieceColor color) throws IndexOutsideOfGridException
	{
		super(color, PieceType.ROOK);
		positionOffsets = new ArrayList<RelativePosition>();

		positionOffsets.add(new RelativePosition(0, 1, Board.GRID_DIMENSION));
		positionOffsets.add(new RelativePosition(1, 0, Board.GRID_DIMENSION));
		positionOffsets.add(new RelativePosition(-1, 0, Board.GRID_DIMENSION));
		positionOffsets.add(new RelativePosition(0, -1, Board.GRID_DIMENSION));
	}

	@Override
	List<RelativePosition> getNewPositionOffsets()
	{
		return this.positionOffsets;
	}
}
