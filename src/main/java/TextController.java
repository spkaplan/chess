package main.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by brandon on 3/29/2016 at 4:44 PM as part of the chess project.
 */

public class TextController {

    private Model model;

    Map<Character, Integer> charToIntMap = new HashMap<>();

    public TextController() {
        buildCharToIntMap();
    }

    void buildCharToIntMap() {
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
     * Processes the input of the user and tells model how to react/update. Also queues the model to refresh the view.
     * 
     * @param input user input (System.in for now)
     */
    void processInput(String input) {
        input = input.toLowerCase();
        String[] inputArray = input.split(" ");
        String arg1, arg2;
        switch(inputArray[0]) {
            case "move":
                if(inputArray.length != 3) {
                    String message = "The move command requires two grid positions separated by a space (e.g. move a1 a2)";
                    model.setExceptionThrown(new IllegalArgumentException(message));
                    break;
                }
                arg1 = inputArray[1];
                arg2 = inputArray[2];
                try {
                    Position curPos = new Position(8-Character.getNumericValue(arg1.charAt(1)), charToIntMap.get(arg1.charAt(0)));
                    Position newPos = new Position(8-Character.getNumericValue(arg2.charAt(1)), charToIntMap.get(arg2.charAt(0)));
                    model.movePiece(curPos, newPos);
                } catch (InvalidPositionException e) {
                    model.setExceptionThrown(e);
                }
                break;
            case "validmoves":
                if(inputArray.length != 2) {
                    String message = "The validmoves command requires one grid position (e.g. validmoves a1)";
                    model.setExceptionThrown(new IllegalArgumentException(message));
                    break;
                }
                arg1 = inputArray[1];
                try {
                    Position position = new Position(8-Character.getNumericValue(arg1.charAt(1)), charToIntMap.get(arg1.charAt(0)));
                    model.getValidNewPositions(position);
                } catch (InvalidPositionException e) {
                    model.setExceptionThrown(e);
                }
                break;
            default:
                String message = "Unrecognized Command: " + input;
                model.setExceptionThrown(new IllegalArgumentException(message));
                break;
        }
    }

    public void setModel(Model model) {
        this.model = model;
    }

    /**
     * This is the method that loops indefinitely awaiting commands from the user.
     */
    public void run() {
        Scanner reader = new Scanner(System.in);
        
        /*Trigger the view so it is displayed immediately*/
        this.model.notifyObservers();

        while(true) {
            System.out.print(">>");
            String input = reader.nextLine();
            processInput(input);
            this.model.notifyObservers();
            this.model.incrementTurnCount();
            this.model.switchWhosTurn();
        }
    }
}
