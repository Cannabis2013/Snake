package guiExample;

import java.util.ArrayList;
import java.util.List;

import baseKit.MObject;
import baseKit.MWidget;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class GameController extends MObject {
	public GameController(MainWindow parent) {
		Parent = parent;	
		objects = new ArrayList<>();
	}
	
	public void drawObjects()
	{
		for (MWidget obj : objects) {
			obj.draw();
		}
	}
	
	public void addObject(MWidget obj)
	{
		objects.add(obj);
	}
	
	public MWidget Object(String objectName)
	{
		for (MWidget obj : objects) {
			if(obj.ObjectName().equals(objectName))
				return obj;
		}
		return null;
	}
	
	public void drawLevel()
	{
		GraphicsContext gC = Parent.getPainter();
		
		// Draw background color
		gC.setFill(Color.DARKGREEN);
		gC.fillRect(0, 0, Parent.Width(), Parent.Height());
		
		
		/*
		 * Draw grid
		 * Set linewidth
		 * Draw grids
		 */
		gC.setFill(Color.BLACK);
		gC.setLineWidth(2);
		for (int i = Parent.BlockSize(); i < Parent.Height(); i += Parent.BlockSize()) {
			gC.strokeLine(0, i, Parent.Width(), i);
		}
		
		for (int i = Parent.BlockSize(); i < Parent.Width(); i += Parent.BlockSize()) {
			gC.strokeLine(i, 0, i, Parent.Height());
		}
	}
	
	public void keyEvent(KeyCode key)
	{
		SnakeObject snake = (SnakeObject) Object("Snake");
		
		if(key == KeyCode.R)
		{
			objects.remove((MObject) snake);
			snake = new SnakeObject(Parent);
			snake.setObjectName("Snake");
			snake.setPosition(Parent.Width()/2, Parent.Height()/2);
			addObject(snake);
		}
		else
		{
			snake.moveInDirection(key);
		}
	}
	
	public void moveEvent()
	{
		
	}
	
	private List<MWidget> objects;
	private MainWindow Parent;
}
