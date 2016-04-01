package main.java;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece
{
	List<RelativePosition> possibleMoves;

	public Queen(PieceColor color)
	{
		super(color, PieceType.QUEEN);
		possibleMoves = new ArrayList<RelativePosition>();

		possibleMoves.add(new RelativePosition(1, 1, Board.GRID_SIZE));
		possibleMoves.add(new RelativePosition(0, 1, Board.GRID_SIZE));
		possibleMoves.add(new RelativePosition(1, 0, Board.GRID_SIZE));
		possibleMoves.add(new RelativePosition(1, -1, Board.GRID_SIZE));
		possibleMoves.add(new RelativePosition(-1, 0, Board.GRID_SIZE));
		possibleMoves.add(new RelativePosition(-1, -1, Board.GRID_SIZE));
		possibleMoves.add(new RelativePosition(0, -1, Board.GRID_SIZE));
		possibleMoves.add(new RelativePosition(-1, 1, Board.GRID_SIZE));
	}

	@Override
	List<RelativePosition> getNewPossibleMoves()
	{
		return this.possibleMoves;
	}
}
