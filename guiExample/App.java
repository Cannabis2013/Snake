package guiExample;

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
		List<String> parameters = getParameters().getRaw();
		
		String rows = parameters.get(0);
		String columns = parameters.get(1);
		
		Screen scr = Screen.getPrimary();
		Dimension scrDim = new Dimension();
		int blockSize = 0;
		if(scr.getBounds().getWidth() < 1280 || scr.getBounds().getHeight() < 800)
		{
			scrDim.setSize(640, 400);
			blockSize = 10;
		}
		else
		{
			scrDim.setSize(1280, 800);
			blockSize = 20;
		}
		
		
		int r = Integer.parseInt(rows), c = Integer.parseInt(columns);
		
		if(r < 5 || r > 100 || c < 5 || c > 100)
			throw new IllegalArgumentException();
		
		MainWindow mW = new MainWindow(scrDim,r,c,blockSize);
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
		if(args.length == 0)
			throw new IllegalArgumentException();
		
		a.exec(args);
	}
}
