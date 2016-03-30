package main.java;

/**
 * Created by brandon on 3/29/2016.
 */
public class TextController {

    private Model textModel;
    private TextView textView;

    public TextController() {
        textModel = new Model(); //TODO: Is text controller created a model or getting an existing one at instantiation?
        textView = new TextView(); // And same here

    }

    void userEvent(String event) {
        System.out.println(event);
    }
}
