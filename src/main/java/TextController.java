package main.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by brandon on 3/29/2016 at 4:44 PM as part of the chess project.
 */

public class TextController
{
    private Model model;
    private TextView view;

    private String validCoords = "abcdefh";

    private enum validCommands {
        move, validmoves, help, exit, quit, castle, refresh
    }

    Map<Character, Integer> charToIntMap;

    public TextController()
    {
        buildCharToIntMap();
    }

    public void setModel(Model model)
    {
        this.model = model;
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
     * Processes the input of the user and tells model how to react/update. Also
     * queues the model to refresh the view.
     * 
     * @param input User input.
     */
    void processInput(String input)
    {
        input = input.toLowerCase();
        String[] inputArray = input.split("\\s+");
        String arg1, arg2;
        switch (inputArray[0])
        {
        case "move":
            move(inputArray);
            break;

        case "validmoves":
            validmoves(inputArray);
            break;

        case "castle":
            castle(inputArray);
            break;

        case "quit":
        case "exit":
            if (exit(inputArray)) break;

        case "refresh":
            this.view.refresh();
            break;

        case "help":
            this.view.help();
            break;

        default:
            String message = "Unrecognized Command: " + input;
            model.setExceptionThrown(new IllegalArgumentException(message));
            break;
        }
    }

    /**
     * Checks to be sure that "exit" was the only thing passed as input and then ends the program
     * @param inputArray
     * @return
     */
    private boolean exit(String[] inputArray) {
        if (inputArray.length != 1)
        {
            String message = "The quit/exit commands do not take any arguments.";
            model.setExceptionThrown(new IllegalArgumentException(message));
            return true;
        }
        System.exit(0);
        return false;
    }

    /**
     * Validate input for castle, load the Position objects, and pass the request on to model
     * @param inputArray an array of two positions, one being a rook, and the other being a king
     */
    private void castle(String[] inputArray) {
        String arg1, arg2;
        boolean valid = true;
        if (inputArray.length != 3)
        {
            String message = "The castle command requires two grid positions (e.g. castle a1 a2)";
            model.setExceptionThrown(new IllegalArgumentException(message));
            return;
        }
        arg1 = inputArray[1];
        arg2 = inputArray[2];
        if(!validCoords.contains(String.valueOf(arg1.charAt(0)))) {
            String message = "invalid coordinate: " + String.valueOf(arg1.charAt(0));
            model.setExceptionThrown(new IllegalArgumentException(message));
            valid = false;
        }
        if(!validCoords.contains(String.valueOf(arg2.charAt(0)))) {
            String message = "invalid coordinate: " + String.valueOf(arg2.charAt(0));
            model.setExceptionThrown(new IllegalArgumentException(message));
            valid = false;
        }
        if(valid) {
            try
            {
                if(charToIntMap.get(arg1.charAt(0)) != null && charToIntMap.get(arg2.charAt(0)) != null) {
                    Position position1 = new Position(8 - Character.getNumericValue(arg1.charAt(1)), charToIntMap.get(arg1.charAt(0)));
                    Position position2 = new Position(8 - Character.getNumericValue(arg2.charAt(1)), charToIntMap.get(arg2.charAt(0)));
                    model.castle(position1, position2);

                    this.model.incrementTurnCount();
                    this.model.switchWhosTurn();
                }
            } catch (InvalidPositionException e)
            {
                model.setExceptionThrown(e);
            }
        }
    }

    /**
     * Request list of valid moves from the model and return them to the view
     * @param inputArray an array containing the single position string passed by the user
     */
    private void validmoves(String[] inputArray) {
        String arg1;
        if (inputArray.length != 2)
        {
            String message = "The validmoves command requires one grid position (e.g. validmoves a1)";
            model.setExceptionThrown(new IllegalArgumentException(message));
            return;
        }
        arg1 = inputArray[1];
        try
        {
            Position position = new Position(8 - Character.getNumericValue(arg1.charAt(1)), charToIntMap.get(arg1.charAt(0)));
            model.getValidNewPositions(position);
        } catch (InvalidPositionException e)
        {
            model.setExceptionThrown(e);
        }
    }

    /**
     * Validates string input, loads into position objects, and moves pieces
     * @param inputArray the two strings input by the user (old pos, new pos)
     */
    private void move(String[] inputArray) {
        String arg1;
        String arg2;
        boolean valid = true;
        if (inputArray.length != 3)
        {
            String message = "The move command requires two grid positions separated by a space (e.g. move a1 a2)";
            model.setExceptionThrown(new IllegalArgumentException(message));
            return;
        }
        arg1 = inputArray[1];
        arg2 = inputArray[2];
        if(!validCoords.contains(String.valueOf(arg1.charAt(0)))) {
            String message = "invalid coordinate: " + String.valueOf(arg1.charAt(0));
            model.setExceptionThrown(new IllegalArgumentException(message));
            valid = false;
        }
        if(!validCoords.contains(String.valueOf(arg2.charAt(0)))) {
            String message = "invalid coordinate: " + String.valueOf(arg2.charAt(0));
            model.setExceptionThrown(new IllegalArgumentException(message));
            valid = false;
        }
        if(valid) {
            try
            {
                Position curPos = new Position(8 - Character.getNumericValue(arg1.charAt(1)), charToIntMap.get(arg1.charAt(0)));
                Position newPos = new Position(8 - Character.getNumericValue(arg2.charAt(1)), charToIntMap.get(arg2.charAt(0)));
                model.movePiece(curPos, newPos);

                this.model.incrementTurnCount();
                this.model.switchWhosTurn();
            } catch (InvalidPositionException | IllegalArgumentException e)
            {
                model.setExceptionThrown(e);
            }
        }
    }

    public void setView(TextView view) {
        this.view = view;
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
            processInput(input);
            this.model.notifyObservers();
        }
    }
}
