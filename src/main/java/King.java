package main.java;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece
{
	List<RelativePosition> possibleMoves;

	public King(PieceColor color)
	{
		super(color, PieceType.KING);
		possibleMoves = new ArrayList<RelativePosition>();

		possibleMoves.add(new RelativePosition(1, 1, 1));
		possibleMoves.add(new RelativePosition(0, 1, 1));
		possibleMoves.add(new RelativePosition(1, 0, 1));
		possibleMoves.add(new RelativePosition(1, -1, 1));
		possibleMoves.add(new RelativePosition(-1, 0, 1));
		possibleMoves.add(new RelativePosition(-1, -1, 1));
		possibleMoves.add(new RelativePosition(0, -1, 1));
		possibleMoves.add(new RelativePosition(-1, 1, 1));
	}

	@Override
	List<RelativePosition> getNewPossibleMoves()
	{
		return this.possibleMoves;
	}
}
