package main.java;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by brandon on 3/29/2016 at 4:44 PM as part of the chess project.
 */

class TextView implements Observer{
    private Model model;
    private TextController controller;

    TextView() {
        model = null; //must be set using "setModel" in Chess()
    }

    void refresh() {
        System.out.println("The view has called refresh");
    }

    void setModel(Model model) {
        this.model = model;
    }

    void setController(TextController controller) {
        this.controller = controller;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("The view has called refresh");
    }
}