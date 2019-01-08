package guiExample;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import baseKit.MHObject;
import baseKit.MHWidget;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class GameController extends MHObject {
	public GameController(MHWidget parent) {
		Parent = parent;
		animator = new ObjectAnimator();	
		objects = new ArrayList<>();
	}
	
	public void drawObjects()
	{
		for (MHWidget obj : objects) {
			obj.draw();
		}
	}
	
	public void addObject(MHWidget obj)
	{
		objects.add(obj);
	}
	
	public MHWidget Object(String objectName)
	{
		for (MHWidget obj : objects) {
			if(obj.ObjectName().equals(objectName))
				return obj;
		}
		return null;
	}
	
	public void drawLevel()
	{
		MainWindow p = (MainWindow) Parent;
		Point pDim = p.gridDimension();
		GraphicsContext gC = Parent.getPainter();
		gC.setFill(Color.DARKGREEN);
		
		gC.fillRect(0, 0, Parent.Width(), Parent.Height());
		
		gC.setFill(Color.BLACK);
		gC.setLineWidth(2);
		for (int i = 20; i < Parent.Height(); i += 20) {
			gC.strokeLine(0, i, Parent.Width(), i);
		}
		
		for (int i = 20; i < Parent.Width(); i += 20) {
			gC.strokeLine(i, 0, i, Parent.Height());
		}
	}
	
	public void keyEvent(KeyCode key)
	{
		SnakeObject snake = (SnakeObject) Object("Snake");
		if(key == KeyCode.Q)
			animator.Stop();
		else if(key == KeyCode.ENTER && !animator.IsWorking())
		{
			animator = new ObjectAnimator(this,snake);
			animator.start();
		}
		else if(key == KeyCode.R)
		{
			animator.Stop();
			objects.remove((MHObject) snake);
			snake = new SnakeObject(Parent);
			snake.setObjectName("Snake");
			snake.setPosition(Parent.Width()/2, Parent.Height()/2);
			addObject(snake);
			
		}
		else
			snake.setCurrentDirection(key);
	}
	
	public void moveEvent()
	{
		
	}
	
	private ObjectAnimator animator;
	private List<MHWidget> objects;
	private MHWidget Parent;
	
}
