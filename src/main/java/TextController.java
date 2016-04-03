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
        charToIntMap.put('a', 1);
        charToIntMap.put('b', 2);
        charToIntMap.put('c', 3);
        charToIntMap.put('d', 4);
        charToIntMap.put('e', 5);
        charToIntMap.put('f', 6);
        charToIntMap.put('g', 7);
        charToIntMap.put('h', 8);
    }

    void processInput(String input) {
        input = input.toLowerCase();
        String[] inputArray = input.split(" ");
        String arg1, arg2;
        switch(inputArray[0]) {
            case "move":
                arg1 = inputArray[1];
                arg2 = inputArray[2];
                try {
                    Position curPos = new Position(charToIntMap.get(arg1.charAt(0)), Character.getNumericValue(arg1.charAt(1)));
                    Position newPos = new Position(charToIntMap.get(arg2.charAt(0)), Character.getNumericValue(arg2.charAt(1)));
                    model.movePiece(curPos, newPos);
                } catch (IndexOutsideOfGridException e) {
                    System.out.println("That grid position does not exist, try again");
                }
                break;
            case "validmoves":
                arg1 = inputArray[1];
                try {
                    Position position = new Position(charToIntMap.get(arg1.charAt(0)), Character.getNumericValue(arg1.charAt(1)));
                    model.getValidNewPositions(position);
                } catch (IndexOutsideOfGridException e) {
                    System.out.println("That grid position does not exist, try again");
                }
            default:
                System.out.println("Unrecognized command: " + input);
                break;
        }

    }

    void setModel(Model model) {
        this.model = model;
    }

    public void run() {
        Scanner reader = new Scanner(System.in);

        while(true) {
            System.out.println("Awaiting user input");
            String input = reader.nextLine();
            System.out.println("The user typed: " + input);
            processInput(input);
        }
    }
}
