package guiExample;


import baseKit.MWidget;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MainWindow extends MWidget{
	public  MainWindow(int fixedWidth, int fixedHeight,int gridRows, int gridColumns, int bSize) 
	{
		super();
		setFixedSize(fixedWidth, fixedHeight);
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
	
	/*
	 * Set block size
	 * The sidelenght of the quadratic polygons
	 */
	
	
	
	private GameController gController;
	private PaintWorker pWorker;
}
