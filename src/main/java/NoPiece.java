package main.java;

import java.util.ArrayList;
import java.util.List;

public class NoPiece extends Piece
{

	@Override
	List<RelativePosition> getNewPositionOffsets()
	{
		return new ArrayList<RelativePosition>(); //Empty list, because it cannot move anywhere
	}
}
