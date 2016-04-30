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
        String[] inputArray = input.split(" ");
        String arg1, arg2;
        switch (inputArray[0])
        {
        case "move":
            if (inputArray.length != 3)
            {
                String message = "The move command requires two grid positions separated by a space (e.g. move a1 a2)";
                model.setExceptionThrown(new IllegalArgumentException(message));
                break;
            }
            arg1 = inputArray[1];
            arg2 = inputArray[2];
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
            break;
        case "validmoves":
            if (inputArray.length != 2)
            {
                String message = "The validmoves command requires one grid position (e.g. validmoves a1)";
                model.setExceptionThrown(new IllegalArgumentException(message));
                break;
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
            break;
        case "castle":
            if (inputArray.length != 3)
            {
                String message = "The castle command requires two grid positions (e.g. castle a1 a2)";
                model.setExceptionThrown(new IllegalArgumentException(message));
                break;
            }
            arg1 = inputArray[1];
            arg2 = inputArray[2];
            try
            {
                Position position1 = new Position(8 - Character.getNumericValue(arg1.charAt(1)), charToIntMap.get(arg1.charAt(0)));
                Position position2 = new Position(8 - Character.getNumericValue(arg2.charAt(1)), charToIntMap.get(arg2.charAt(0)));
                model.castle(position1, position2);

                this.model.incrementTurnCount();
                this.model.switchWhosTurn();
            } catch (InvalidPositionException e)
            {
                model.setExceptionThrown(e);
            }
            break;
        case "quit":
        case "exit":
            if (inputArray.length != 1)
            {
                String message = "The quit/exit commands do not take any arguments.";
                model.setExceptionThrown(new IllegalArgumentException(message));
                break;
            }
            System.exit(0);

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
