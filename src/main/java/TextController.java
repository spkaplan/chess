package main.java;

/**
 * Created by brandon on 3/29/2016 at 4:44 PM as part of the chess project.
 */

public class TextController {

    private Model model;

    void userEvent(String event) {
        model.incomingAction(event);
    }

    void setModel(Model model) {
        this.model = model;
    }
}
