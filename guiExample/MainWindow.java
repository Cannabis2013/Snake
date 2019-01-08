package guiExample;


import java.awt.Point;

import baseKit.MHWidget;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainWindow extends MHWidget{
	public  MainWindow(int n, int m) 
	{
		super();
		pWorker = new PaintWorker(this);
		gController = new GameController(this);
		dim = new Point(n,m);
	}
	
	public void initializeSnakePosition(double x, double y)
	{
		SnakeObject snake = new SnakeObject(this);
		snake.setPosition(Width()/2, Height()/2);
		snake.setObjectName("Snake");
		gController.addObject(snake);
	}
	
	/*
	 * 
	 */
	
	@Override
	protected void keyPressEvent(KeyEvent event) 
	{
		if(event.getCode() == KeyCode.ESCAPE)
			Platform.exit();
		gController.keyEvent(event.getCode());
	}
	
	public void draw()
	{
		paintClear();
		gController.drawLevel();
		gController.drawObjects();
		paintUpdate();
	}
	
	public void drawEvent() 
	{
		Platform.runLater(new Runnable() {
			public void run() {
				draw();
			}
		});
	}
	
	public void show()
	{
		super.show();
		draw();
		pWorker.start();
	}
	
	public Point gridDimension()
	{
		return dim;
	}
	
	private GameController gController;
	private PaintWorker pWorker;
	private SnakeObject snake;
	private Point dim;
}
