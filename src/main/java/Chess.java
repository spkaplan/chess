package main.java;

/**
 * Created by brandon on 3/30/2016 at 4:44 PM as part of the chess project.
 */
public class Chess
{
    public static void main(String[] args)
    {
        //Instantiate the Model, View, and Controller//

        Model model = new Model();
        TextView view = new TextView();
        TextController controller = new TextController();

        controller.setModel(model);
        controller.setView(view);


        model.addObserver(view);

        controller.run();
    }
}
