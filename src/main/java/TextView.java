package main.java;

/**
 * Created by brand on 3/29/2016.
 */
class TextView {
    private Model model;

    TextView() {
        model = new Model(); //TODO: do we create the model or "get" it?
    }

    void refresh() {
    	model.getState();
    	drawBoard(model.getBoard());
    	
        System.out.println("The view has called refresh");
    }
    
    void drawBoard(Board board) throws IndexOutsideOfGridException{
    	for (int row = 0; row < board.GRID_DIMENSION; row++)
    	{
    		for(int col = 0; col < board.GRID_DIMENSION; col++)
    		{
    			Position currentPosition = new Position(row, col);
    			Piece currentPiece = board.gridLookup(currentPosition);
    			System.out.print(getAbbriviation(currentPiece));
    		}
    	}
    }
    
    String getAbbriviation(Piece piece){
    	PieceType pieceType = piece.getType();
    	switch(pieceType){
    		case BISHOP: 
    			return " BI ";
    		case KING: 
    			return " KI ";
    		case KNIGHT: 
    			return " KN ";
    		case PAWN: 
    			return " PA ";
    		case QUEEN: 
    			return " QU ";
    		case ROOK: 
    			return " RO ";
    		case NO_PIECE: 
    			return " .. ";
    		default: 
    			return " ";
    		
    	}
    }
}
