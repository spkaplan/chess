package main.java;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece
{
	List<RelativePosition> positionOffsets;

	public Pawn(PieceColor color)
	{
		super(color, PieceType.PAWN);
		positionOffsets = new ArrayList<RelativePosition>();

		if (this.getColor() == PieceColor.WHITE)
		{
			/*On the first turn, pawns can move two spaces.*/
			if (Model.getTurnCount() == 1)
			{
				positionOffsets.add(new RelativePosition(0, -1, 2));
			} else
			{
				positionOffsets.add(new RelativePosition(0, -1, 1));
			}
		} else
		{
			/*On the second turn (Black's first turn), pawns can move two spaces.*/
			if (Model.getTurnCount() == 2)
			{
				positionOffsets.add(new RelativePosition(0, 1, 2));
			} else
			{
				positionOffsets.add(new RelativePosition(0, 1, 1));
			}
		}
	}

	@Override
	List<RelativePosition> getNewPositionOffsets()
	{
		return this.positionOffsets;
	}
}
