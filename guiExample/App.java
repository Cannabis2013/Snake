package guiExample;

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
		
		Screen mainScreen = Screen.getPrimary();
		Rectangle2D bounds = mainScreen.getBounds();
		
		MainWindow mW = new MainWindow();
		if(bounds.getWidth() <= 1280 && bounds.getHeight() <= 800)
			mW.setFixedSize(bounds.getWidth(), bounds.getHeight());
		else
			mW.setFixedSize(1280, 800);
		
		mW.initializeSnakePosition(20, 30);
		mW.show();
		}
	public void exec()
	{
		launch("");
	}
	
	public void exit()
	{
		Platform.exit();
	}
	
	public static void print(double n)
	{
		System.out.println(n);
	}
	
	public static void main(String[] args) {
		App a = new App();
		a.exec();
	}
}
