package main.java;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece
{

	public Pawn(PieceColor color)
	{
		super(color, PieceType.PAWN);
	}

	@Override
	List<RelativePosition> getNewPositionOffsets()
	{
		List<RelativePosition> positionOffsets = new ArrayList<RelativePosition>();
		positionOffsets.add(new RelativePosition(0, 1, 1));
		return positionOffsets;
	}
}
