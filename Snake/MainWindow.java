package Snake;


import java.awt.Dimension;

import baseKit.MWidget;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
	
public class MainWindow extends MWidget{
	public  MainWindow(Dimension size,int gridRows, int gridColumns) 
	{
		super();
		setFixedSize(size.getWidth(), size.getHeight());
		setBackgroundColor(Color.DARKBLUE);
		lController = new LevelController(this, gridRows, gridColumns);
		
		
		pWorker = new PaintWorker(this);
		gController = new GameController(this,lController);
	}
	
	@Override
	protected void keyPressEvent(KeyEvent event) 
	{
		if(event.getCode() == KeyCode.ESCAPE)
		{
			pWorker.Stop();
			Platform.exit();
		}
		gController.keyEvent(event.getCode());
	}
	
	@Override
	protected void mouseMoveEvent(MouseEvent event) {
		print(String.format("x: %1$,.2f y: %2$,.2f", event.getX(),event.getY()));
	}
	
	public void draw()
	{
		paintClear();
		lController.drawLevel();;
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
	
	private GameController gController;
	private LevelController lController;
	private PaintWorker pWorker;
}
