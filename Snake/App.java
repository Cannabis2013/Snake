package Snake;

import java.awt.Dimension;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class App extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		/*
		 * Main thread
		 */
		
		/*
		 * Retrieves the list of arguments passed by command line
		 */
		List<String> parameters = getParameters().getRaw();
		
		/*
		 * Checks if any arguments is passed. Only pair of integers is accepted.
		 * If no arguments passed, standard values will be chosen.
		 */
		
		int r = 0, c = 0;
		if (parameters.size() != 2) {
			r = 30;
			c = 40;
		}
		else
		{
			String rows = parameters.get(0);
			String columns = parameters.get(1);
			
			r = Integer.parseInt(rows);
			c = Integer.parseInt(columns);
		}
		
		/*
		 * Checks if arguments interval is appropiate. Otherwise throw exception.
		 */
		
		if(r < 5 || r > 100 || c < 5 || c > 100)
			throw new IllegalArgumentException();
		
		
		Screen scr = Screen.getPrimary();
		
		MainWindow mW = null;
		
		if(scr.getBounds().getWidth() <= 1280 || scr.getBounds().getHeight() <= 800)
			mW = new MainWindow(new Dimension(1024, 720), r, c);
		else
			mW = new MainWindow(new Dimension(1280, 800), r, c);
		
		mW.show();
		}
	public void exec(String[] args)
	{
		launch(args);
	}
	
	public void exit()
	{
		Platform.exit();
	}
	
	public static void main(String[] args) {
		App a = new App();
		if(args.length <= 0)
		{
			System.out.println("No arguments passed, standard values will be set.");
		}
		a.exec(args);
	}
}
