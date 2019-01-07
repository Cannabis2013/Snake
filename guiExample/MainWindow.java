package guiExample;


import baseKit.MHWidget;
import guiExample.SnakeObject.direction;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class MainWindow extends MHWidget{
	public  MainWindow() 
	{
		super();
		wrkController = new WorkerController(this);
		pWorker = new PaintWorker(this);
		wrkController.setPollRate(30); // Polling rate in Hertz
		animator = new ObjectAnimator(this);
		
		snake = new SnakeObject(this);
		
	}
	
	public void initializeSnakePosition(double x, double y)
	{
		snake.setPosition(x, y);
	}
	
	/*
	 * 
	 */
	
	@Override
	protected void keyPressEvent(KeyEvent event) 
	{
		if(event.getCode() == KeyCode.ESCAPE)
			animator.Stop();
		else if(event.getCode() == KeyCode.ENTER && !animator.IsWorking())
		{
			animator = new ObjectAnimator(this, snake);
			animator.start();
		}
		
		
		snake.setCurrentDirection(event.getCode());
	}
	
	private void draw()
	{
		paintClear();
		snake.draw();
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
	
	private ObjectAnimator animator;
	private WorkerController wrkController;
	private PaintWorker pWorker;
	private SnakeObject snake;
}
