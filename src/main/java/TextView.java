package main.java;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by brand on 3/29/2016.
 */
public class TextView implements Observer
{
    private final static Logger logger = LoggerFactory.getLogger(TextView.class);
    private final static String NEWLINE = "\n";
    private final static String TWO_SPACES = "  ";
    private final static String VERTICAL_BAR = " | ";
    private static Map<Integer, Character> intToChar;
    private static Map<Integer, Integer> intRowConversion;
    private Model model;
    String helpList = buildHelpString();

    public TextView()
    {
        buildIntToCharMap();
        buildIntConversionMap();
    }

    /**
     * Build map to convert column to a given character.
     */
    void buildIntToCharMap()
    {
        intToChar = new HashMap<>();
        intToChar.put(0, 'a');
        intToChar.put(1, 'b');
        intToChar.put(2, 'c');
        intToChar.put(3, 'd');
        intToChar.put(4, 'e');
        intToChar.put(5, 'f');
        intToChar.put(6, 'g');
        intToChar.put(7, 'h');
    }

    /**
     * Build map to convert a row number.
     */
    void buildIntConversionMap()
    {
        intRowConversion = new HashMap<>();
        intRowConversion.put(0, 8);
        intRowConversion.put(1, 7);
        intRowConversion.put(2, 6);
        intRowConversion.put(3, 5);
        intRowConversion.put(4, 4);
        intRowConversion.put(5, 3);
        intRowConversion.put(6, 2);
        intRowConversion.put(7, 1);
    }

    String buildHelpString()
    {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("move [pos 1] [pos 2] : moves piece at pos 1 to pos 2" + "\n");
        stringBuilder.append("validmoves [pos 1] : displays the valid moves for piece at pos 1" + "\n");
        stringBuilder.append("quit : exits the game" + "\n");
        stringBuilder.append("exit : exits the game" + "\n");
        stringBuilder.append("refresh : redisplays the view of the board" + "\n");
        stringBuilder.append("help : displays a list of the available commands" + "\n");
        return stringBuilder.toString();
    }

    /**
     * Updates the console to display info from the current model.
     */
    @Override
    public void update(Observable o, Object arg)
    {
        this.model = (Model) o;

        if (model.getIsCheck() == true)
        {
            System.out.println(model.getWhosTurn() + " is in check.");
        } else if (model.getIsCheckmate() == true)
        {
            System.out.println(model.getWhosTurn() + " is in checkmate. Game over.");
        } else
        {
            drawHeader(model);
            drawBoard(model.getBoard());
        }
    }

    /**
     * Redisplays the view using the last model that was passed to the view
     */
    void refresh()
    {
        drawHeader(model);
        drawBoard(model.getBoard());
    }

    /**
     * displays a list of available commands to the user
     */
    void help()
    {
        System.out.println(helpList);
    }

    void showMessage(String message)
    {
        System.out.println(message);
    }

    /**
     * Displays valid moves of a given piece.
     * 
     * @param validPositions Positions on the board the piece can move to.
     * 
     */
    void showValidMoves(List<Position> validPositions)
    {
        StringBuilder validMsg = new StringBuilder();
        validMsg.append("Valid Moves: ");
        for (Position currValidPosition : validPositions)
        {
            validMsg.append(intToChar.get(currValidPosition.getColumn()));
            validMsg.append(intRowConversion.get(currValidPosition.getRow()));
            validMsg.append(TWO_SPACES);
        }
        System.out.println(validMsg);
    }

    /**
     * Displays high level game info.
     * 
     * @param model
     */
    void drawHeader(Model model)
    {
        String horizontalBar = "+----------------------------------------------------------------------+";
        StringBuilder header = new StringBuilder();

        header.append(horizontalBar);
        header.append(NEWLINE);

        header.append(TWO_SPACES);
        header.append(model.getWhosTurn() + "'S Turn");
        header.append(VERTICAL_BAR);
        header.append("Turn #: " + model.getTurnCount());
        header.append(VERTICAL_BAR);
        header.append("WHITE --> UPPERCASE");
        header.append(VERTICAL_BAR);
        header.append("black --> lowercase");
        header.append(NEWLINE);

        header.append(horizontalBar);

        System.out.println(header);
    }

    /**
     * Iterates through board object printing to the console the correct
     * abbreviation for a given piece and the outer frame of the board.
     * 
     * @param board Current board state.
     */
    void drawBoard(Board board)
    {
        String columnLabels = "     a    b    c    d    e    f    g    h    ";
        String horizontalBar = "+----+----+----+----+----+----+----+----+";

        StringBuilder boardString = new StringBuilder();

        boardString.append(columnLabels);
        boardString.append(NEWLINE);
        boardString.append(TWO_SPACES);
        boardString.append(horizontalBar);
        boardString.append(NEWLINE);

        for (int row = 0; row < Board.GRID_SIZE; row++)
        {
            int rowLabel = Board.GRID_SIZE - row;

            for (int col = 0; col < Board.GRID_SIZE; col++)
            {
                Position currentPosition = null;
                try
                {
                    currentPosition = new Position(row, col);
                } catch (InvalidPositionException ex)
                {
                    logger.error(ex.getMessage());
                    System.exit(1);
                }
                Piece currentPiece = board.gridLookup(currentPosition);
                String abbreviation = getAbbreviation(currentPiece);
                if (col == 0)
                {
                    boardString.append(rowLabel);
                    boardString.append(" | ");
                    boardString.append(abbreviation);
                } else if (col == 7)
                {
                    boardString.append(" | ");
                    boardString.append(abbreviation);
                    boardString.append(" | ");
                    boardString.append(NEWLINE);
                } else
                {
                    boardString.append(" | ");
                    boardString.append(abbreviation);
                }
            }
            boardString.append(TWO_SPACES);
            boardString.append(horizontalBar);
            boardString.append(NEWLINE);
        }
        boardString.append(NEWLINE);
        System.out.print(boardString);
    }

    /**
     * Maps piece to string representation.
     * 
     * @param piece
     * @return String representation of piece.
     * @throws IllegalArgumentException For invalid piece type.
     */
    String getAbbreviation(Piece piece)
    {
        PieceType pieceType = piece.getType();
        String pieceAbr = "";
        switch (pieceType)
        {
        case BISHOP:
            pieceAbr = "BI";
            break;
        case KING:
            pieceAbr = "KI";
            break;
        case KNIGHT:
            pieceAbr = "KN";
            break;
        case PAWN:
            pieceAbr = "PA";
            break;
        case QUEEN:
            pieceAbr = "QU";
            break;
        case ROOK:
            pieceAbr = "RO";
            break;
        case NO_PIECE:
            pieceAbr = "  ";
            break;
        default:
            String msg = "Piece type isn't accounted for.";
            logger.error(msg);
        }
        if (piece.getColor() == PieceColor.BLACK)
        {
            return pieceAbr.toLowerCase();
        }
        return pieceAbr;
    }
}