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
		
		String rows = parameters.get(0);
		String columns = parameters.get(1);
		
		int r = Integer.parseInt(rows), c = Integer.parseInt(columns);
		
		MainWindow mW = new MainWindow(1280,800,r,c,20);
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
