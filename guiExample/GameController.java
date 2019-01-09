package guiExample;

import java.util.ArrayList;
import java.util.List;

import baseKit.MObject;
import baseKit.MWidget;
import baseKit.PointD;
import guiExample.SnakeObject.direction;
import javafx.scene.input.KeyCode;

public class GameController extends MObject {
	public GameController(MainWindow parent, int n, int m, int bSize) {
		Parent = parent;	
		objects = new ArrayList<>();
		mainLevel = new LevelObject(parent,n,m,bSize);
		initializeSnakePosition(m/2, n/2);
	}
	
	public void initializeSnakePosition(double x, double y)
	{
		SnakeObject snake = new SnakeObject(Parent,1);
		snake.setWidth(mainLevel.BlockSize());
		snake.setPosition(mainLevel.translate(0, 0));
		snake.setObjectName("Snake");
		addObject(snake);
	}
	
	public void drawLevel()
	{
		mainLevel.draw();
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
	
	public void keyEvent(KeyCode key)
	{
		SnakeObject snake = (SnakeObject) Object("Snake");
		
		if(key == KeyCode.R)
		{
			objects.remove((MObject) snake);
			snake = new SnakeObject(Parent,1);
			snake.setObjectName("Snake");
			snake.setPosition(Parent.Width()/2, Parent.Height()/2);
			addObject(snake);
		}
		else if(key.isArrowKey())
		{
			double d = snake.Speed();
			
			if(snake.isDead())
				return;
			
			if(isOrtogonal(snake.CurrentDirection(), key) && snake.Lenght() > 0)
				return;
			
			PointD cPos = snake.Position(), nPos = cPos.copy();
			
			updateCoordinates(nPos, key, d);
			snake.setCurrentDirection(DirectionFromKey(key));
			
			CheckAndCorrelateBoundaries(nPos, snake);
			
			if(snake.containsCoordinate(nPos))
				snake.Kill();
			
			snake.moveToCoordinates(nPos, 0);
		}
	}
	
	public void moveEvent()
	{
		
	}
	
	private direction DirectionFromKey(KeyCode key)
	{
		if(key == KeyCode.LEFT)
			return direction.left;
		else if(key == KeyCode.RIGHT)
			return direction.right;
		else if(key == KeyCode.UP)
			return direction.up;
		else if(key == KeyCode.DOWN)
			return direction.down;
		else
			return null;
	}
	
	private void updateCoordinates(PointD nPos, KeyCode key, double d)
	{
		if(key == KeyCode.RIGHT)
			nPos.incrementX(d);
		else if(key == KeyCode.LEFT)
			nPos.decrementX(d);
		else if(key == KeyCode.UP)
			nPos.decrementY(d);
		else if(key == KeyCode.DOWN)
			nPos.incrementY(d);
	}
	
	private boolean isOrtogonal(direction dir,KeyCode key)
	{
		if(dir == direction.left && key == KeyCode.RIGHT)
			return true;
		else if(dir == direction.right && key == KeyCode.LEFT)
			return true;
		else if(dir == direction.up && key == KeyCode.DOWN)
			return true;
		else if(dir == direction.down && key == KeyCode.UP)
			return true;
		else
			return false;
	}
	
	private void CheckAndCorrelateBoundaries(PointD nPos, SnakeObject obj)
	{
		if(nPos.X() > mainLevel.RightBound())
			nPos.setX(mainLevel.translateX(0));
		else if(nPos.X() < mainLevel.LeftBound())
			nPos.setX(mainLevel.translateX((int) mainLevel.width()));
		else if((nPos.Y() + obj.width()) > mainLevel.LowerBound())
			nPos.setY(mainLevel.translateY(0));
		else if(nPos.Y() < mainLevel.UpperBound())
			nPos.setY(mainLevel.translateY((int) mainLevel.height()));
	}
	
	private List<MWidget> objects;
	private MainWindow Parent;
	private LevelObject mainLevel;
}
