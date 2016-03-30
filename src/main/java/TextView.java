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
        System.out.println("The view has called refresh");
    }
}
