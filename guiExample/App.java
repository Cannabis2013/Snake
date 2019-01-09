package guiExample;

import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;


public class App extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		/*
		 * Main thread
		 */
		List<String> parameters = getParameters().getRaw();
		
		String arg1 = parameters.get(0);
		String arg2 = parameters.get(1);
		
		int n = Integer.parseInt(arg1), m = Integer.parseInt(arg2);
		
		MainWindow mW = new MainWindow(n,m,10);
		mW.setGridSize(n, m);
		
		mW.initializeSnakePosition(n/2, m/2);
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
		
		
		int n = Integer.parseInt(args[0]), m = Integer.parseInt(args[1]);
		App a = new App();
		a.exec(args);
	}
}
