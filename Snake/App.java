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
		
		List<String> parameters = getParameters().getRaw();
		
		int r = 0, c = 0;
		if (parameters.size() == 0) {
			
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
		
		
		Screen scr = Screen.getPrimary();
		Dimension scrDim = new Dimension();
		int blockSize = 0;
		if(scr.getBounds().getWidth() <= 1280 || scr.getBounds().getHeight() <= 800)
		{
			scrDim.setSize(1024, 720);
			blockSize = 15;
		}
		else
		{
			scrDim.setSize(1280, 800);
			blockSize = 20;
		}
		
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
		App a = new App();
		if(args.length <= 0)
		{
			System.out.println("No arguments passed, standard values will be set.");
		}
		
		a.exec(args);
	}
}
