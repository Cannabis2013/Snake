package Snake;


import java.awt.Dimension;

import baseKit.MWidget;
import baseKit.customEvent;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
	
public class MainWindow extends MWidget{
	public  MainWindow(Dimension size,int gridRows, int gridColumns, int bSize) 
	{
		super();
		setFixedSize(size.getWidth(), size.getHeight());
		setBackgroundColor(Color.DARKBLUE);
		pWorker = new PaintWorker(this);
		gController = new GameController(this,gridRows,gridColumns, bSize);
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
	
	private GameController gController;
	private PaintWorker pWorker;
}
