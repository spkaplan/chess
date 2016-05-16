package main.java;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by brandon on 3/29/2016 at 4:44 PM as part of the chess project.
 */

public class TextController
{
    private static final Set<Character> validColumns = new HashSet<Character>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'));
    private static Map<Character, Integer> charToIntMap;
    private Model model;
    private TextView view;

    public TextController()
    {
        buildCharToIntMap();
    }

    public void setModel(Model model)
    {
        this.model = model;
    }

    public void setView(TextView view)
    {
        this.view = view;
    }

    void buildCharToIntMap()
    {
        charToIntMap = new HashMap<>();
        charToIntMap.put('a', 0);
        charToIntMap.put('b', 1);
        charToIntMap.put('c', 2);
        charToIntMap.put('d', 3);
        charToIntMap.put('e', 4);
        charToIntMap.put('f', 5);
        charToIntMap.put('g', 6);
        charToIntMap.put('h', 7);
    }

    /**
     * 
     * @param input The user input. Hopefully in the form
     *            <command> <arg> <arg> ...
     * @param numExpectedArgs The number of arguments that are expected to
     *            follow the command.
     * @return A list of the arguments passed after the command.
     */
    private String[] getArgs(String[] input, int numExpectedArgs)
    {
        if (input.length != numExpectedArgs + 1)
        {
            String msg = "Incorrect number of arguments. " + numExpectedArgs + " args expected, " + (input.length - 1) + " args given.";
            throw new IllegalArgumentException(msg);
        }
        return Arrays.copyOfRange(input, 1, input.length);
    }

    /**
     * Processes the input of the user and call the corresponding method for the
     * command.
     * 
     * @param input User input.
     * @throws InvalidPositionException The user specifies a position that falls
     *             outside of the board limits.
     * @throws IllegalArgumentException Any arbitrary problem with the user
     *             input.
     */
    void processInput(String input) throws IllegalArgumentException, InvalidPositionException
    {
        if (input.length() == 0)
        {
            String message = "Command must contain one or more characters";
            throw new IllegalArgumentException(message);
        }
        input = input.toLowerCase();
        String[] inputArray = input.split("\\s+");

        if (inputArray.length == 0)
        {
            String message = "Command must contain one or more non-whitespace characters";
            throw new IllegalArgumentException(message);
        }

        switch (inputArray[0])
        {
        case "move":
            move(getArgs(inputArray, 2));
            break;

        case "validmoves":
            validmoves(getArgs(inputArray, 1));
            break;

        case "castle":
            castle(getArgs(inputArray, 2));
            break;

        case "quit":
        case "exit":
            exit(getArgs(inputArray, 0));

        case "refresh":
            this.view.refresh();
            break;

        case "help":
            this.view.help();
            break;

        default:
            String message = "Unrecognized Command: " + inputArray[0];
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * Terminate the program.
     * 
     * @param commandArgs The arguments that were passed with the command.
     */
    private void exit(String[] commandArgs)
    {
        System.exit(0);
    }

    /**
     * Validate input for castle, load the Position objects, and tell the model
     * to castle.
     * 
     * @param commandArgs Two positions, one being a rook, and the other a king.
     * @throws InvalidPositionException
     * @throws IllegalArgumentException
     */
    private void castle(String[] commandArgs) throws IllegalArgumentException, InvalidPositionException
    {
        String position1Arg = commandArgs[0];
        char position1ArgColumn = position1Arg.charAt(0);
        char position1ArgRow = position1Arg.charAt(1);

        String position2Arg = commandArgs[1];
        char position2ArgColumn = position2Arg.charAt(0);
        char position2ArgRow = position2Arg.charAt(1);

        if (!validColumns.contains(position1ArgColumn))
        {
            String message = "Invalid column label: " + String.valueOf(position1ArgColumn);
            throw new IllegalArgumentException(message);
        }
        if (!validColumns.contains(position1ArgColumn))
        {
            String message = "Invalid column label: " + String.valueOf(position1ArgColumn);
            throw new IllegalArgumentException(message);
        }
        Position position1 = new Position(8 - Character.getNumericValue(position1ArgRow), charToIntMap.get(position1ArgColumn));
        Position position2 = new Position(8 - Character.getNumericValue(position2ArgRow), charToIntMap.get(position2ArgColumn));
        model.castle(position1, position2);

        this.model.incrementTurnCount();
        this.model.switchWhosTurn();
    }

    /**
     * Request list of valid moves for a given piece from the model and return
     * them to the view.
     * 
     * @param commandArgs The position of the piece to get valid moves for.
     * @throws InvalidPositionException
     */
    private void validmoves(String[] commandArgs) throws InvalidPositionException
    {
        String positionArg = commandArgs[0];
        char positionArgColumn = positionArg.charAt(0);
        char positionArgRow = positionArg.charAt(1);

        if (!validColumns.contains(positionArgColumn))
        {
            String message = "Invalid column label: " + String.valueOf(positionArgColumn);
            throw new IllegalArgumentException(message);
        }
        Position position = new Position(8 - Character.getNumericValue(positionArgRow), charToIntMap.get(positionArgColumn));

        model.getValidNewPositions(position);
    }

    /**
     * Validates string input, loads into position objects, and moves pieces
     * 
     * @param commandArgs Two positions. Where the piece is, where to move it.
     * @throws InvalidPositionException
     * @throws IllegalArgumentException
     */
    private void move(String[] commandArgs) throws IllegalArgumentException, InvalidPositionException
    {
        String startPosition = commandArgs[0];
        char startPositionColumn = startPosition.charAt(0);
        char startPositionRow = startPosition.charAt(1);

        String endPosition = commandArgs[1];
        char endPositionColumn = endPosition.charAt(0);
        char endPositionRow = endPosition.charAt(1);

        if (!validColumns.contains(startPositionColumn))
        {
            String message = "Invalid column label: " + String.valueOf(startPositionColumn);
            throw new IllegalArgumentException(message);
        }
        if (!validColumns.contains(endPositionColumn))
        {
            String message = "Invalid column label: " + String.valueOf(endPositionColumn);
            throw new IllegalArgumentException(message);
        }
        Position curPos = new Position(8 - Character.getNumericValue(startPositionRow), charToIntMap.get(startPositionColumn));
        Position newPos = new Position(8 - Character.getNumericValue(endPositionRow), charToIntMap.get(endPositionColumn));
        model.movePiece(curPos, newPos);

        this.model.incrementTurnCount();
        this.model.switchWhosTurn();
    }

    /**
     * Loop indefinitely, awaiting commands from the user.
     */
    public void run()
    {
        Scanner reader = new Scanner(System.in);

        /*Cause the view to be displayed immediately*/
        this.model.notifyObservers();

        while (true)
        {
            if (this.model.isCheckmate())
            {
                this.model.setIsCheckmate(true);
                this.model.notifyObservers();
                System.exit(0);
            } else if (this.model.isCheck())
            {
                this.model.setIsCheck(true);
                this.model.notifyObservers();
            }
            System.out.print(">>");
            String input = reader.nextLine();
            try
            {
                processInput(input);
            } catch (IllegalArgumentException | InvalidPositionException ex)
            {
                model.setExceptionThrown(ex);
            }
            this.model.notifyObservers();
        }
    }
}