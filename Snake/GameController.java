package Snake;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import baseKit.MObject;
import baseKit.MWidget;
import baseKit.PointD;
import Snake.SnakeObject.direction;
import javafx.scene.input.KeyCode;

public class GameController extends MObject {
	public GameController(MainWindow parent,LevelObject lObject, int n, int m) {
		Parent = parent;
		mainLevel = lObject;	
		objects = new ArrayList<>();
		initializeSnakePosition(m/2, n/2);
		generateFoodObject();
	}
	
	public void initializeSnakePosition(double x, double y)
	{
		SnakeObject snake = new SnakeObject(Parent,1);
		snake.setWidth(mainLevel.BlockSize());
		snake.setPosition(mainLevel.translate(x, y));
		snake.setObjectName("Snake");
		addObject(snake);
	}
	
	public void addObject(MWidget obj)
	{
		objects.add(obj);
	}
	
	private MWidget Object(String objectName)
	{
		for (MWidget obj : objects) {
			if(obj.ObjectName().equals(objectName))
				return obj;
		}
		return null;
	}
	
	private void removeObject(String objectName)
	{
		MWidget obj = Object(objectName);
		if(obj != null)
			objects.remove(obj);
	}
	
	
	public void keyEvent(KeyCode key)
	{
		SnakeObject snake = (SnakeObject) Object("Snake");
		
		if(key == KeyCode.R)
		{
			objects.remove((MObject) snake);
			snake = new SnakeObject(Parent,1);
			initializeSnakePosition(mainLevel.columnCount()/2, mainLevel.rowCount()/2);
			generateFoodObject();
		}
		else if(key.isArrowKey() && 
				!snake.isDead() && 
				(!isOpposite(snake.CurrentDirection(), key) || snake.Lenght() == 0))
		{	
			PointD nPos = snake.Position().copy();
			
			updateCoordinates(nPos, key, snake.Speed());
			snake.setCurrentDirection(DirectionFromKey(key));
			
			CheckAndCorrelateBoundaries(nPos, snake);
			
			// Check for collision
			if(snake.containsCoordinate(nPos))
				snake.Kill();
			
			FoodObject food = (FoodObject) Object("Food");
			if(nPos.Equals(food.Position()))
			{
				snake.moveToCoordinates(nPos, food.GrowAmount());
				generateFoodObject();
			}
			else
				snake.moveToCoordinates(nPos, 0);
		}
	}
	
	/*
	 * Draw section
	 */
	
	public void drawObjects()
	{
		for (MWidget obj : objects)
			obj.draw();
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
	
	private boolean isOpposite(direction dir,KeyCode key)
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
			nPos.setX(mainLevel.translateX((int) mainLevel.columnCount() - 1));
		else if((nPos.Y() + obj.Width()) > mainLevel.LowerBound())
			nPos.setY(mainLevel.translateY(0));
		else if(nPos.Y() < mainLevel.UpperBound())
			nPos.setY(mainLevel.translateY((int) mainLevel.rowCount() - 1));
	}
	
	private void generateFoodObject()
	{
		removeObject("Food");
		Random generator = new Random();
		double x = generator.nextInt(mainLevel.columnCount()),
				y = generator.nextInt(mainLevel.rowCount());
		
		SnakeObject snake = (SnakeObject) Object("Snake");
		
		while(snake.containsCoordinate(mainLevel.translate(new PointD(x, y))))
		{
			x = generator.nextInt(mainLevel.columnCount());
			y = generator.nextInt(mainLevel.rowCount());
		}
		
		FoodObject obj = new FoodObject(Parent,mainLevel.translate(x, y),(double) mainLevel.BlockSize());
		obj.setObjectName("Food");
		addObject(obj);
	}
	
	private List<MWidget> objects;
	private MainWindow Parent;
	private LevelObject mainLevel;
}
