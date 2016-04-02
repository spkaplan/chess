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

		TextController controller = new TextController();
		controller.setModel(model);

		TextView view = new TextView();
		view.setModel(model);
		view.setController(controller);

		model.addObserver(view);
	}
}
