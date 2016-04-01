package main.java;

import java.util.ArrayList;
import java.util.List;

public class NoPiece extends Piece
{

	public NoPiece()
	{
		super(PieceType.NO_PIECE);
	}

	@Override
	List<RelativePosition> getNewPossibleMoves()
	{
		return new ArrayList<RelativePosition>(); //Empty list, because it cannot move anywhere
	}
}
