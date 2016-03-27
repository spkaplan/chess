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
	List<RelativePosition> getNewPositionOffsets()
	{
		List<RelativePosition> positionOffsets = new ArrayList<RelativePosition>();
		positionOffsets.add(new RelativePosition(1, 2, Board.GRID_DIMENSION));
		positionOffsets.add(new RelativePosition(2, 1, Board.GRID_DIMENSION));
		positionOffsets.add(new RelativePosition(1, -2, Board.GRID_DIMENSION));
		positionOffsets.add(new RelativePosition(2, -1, Board.GRID_DIMENSION));
		positionOffsets.add(new RelativePosition(-1, 2, Board.GRID_DIMENSION));
		positionOffsets.add(new RelativePosition(-2, 1, Board.GRID_DIMENSION));
		positionOffsets.add(new RelativePosition(-1, -2, Board.GRID_DIMENSION));
		positionOffsets.add(new RelativePosition(-2, -1, Board.GRID_DIMENSION));
		return positionOffsets;
	}
}
