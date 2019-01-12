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
	public GameController(MainWindow parent) {
		Parent = parent;
		level = (LevelObject) Parent.Child("Level");	
		objects = new ArrayList<>();
		initializeSnakePosition(0, 0);
		generateFoodObject();
	}
	
	public void initializeSnakePosition(double x, double y)
	{
		SnakeObject snake = new SnakeObject(Parent,1);
		snake.setWidth(level.BlockSize());
		snake.setPosition(level.translate(x, y));
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
			initializeSnakePosition(level.columnCount()/2, level.rowCount()/2);
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
		if(nPos.X() - 1 > level.RightBound())
			nPos.setX(level.translateX(0));
		else if(nPos.X() < level.LeftBound())
			nPos.setX(level.translateX((int) level.columnCount() - 1));
		else if((nPos.Y() + obj.BlockWidth() - 1) > level.LowerBound())
			nPos.setY(level.translateY(0));
		else if(nPos.Y() + 1 < level.UpperBound())
			nPos.setY(level.lastColumn());
	}
	
	private void generateFoodObject()
	{
		removeObject("Food");
		
		Random generator = new Random();
		double x = generator.nextInt(level.columnCount()),
				y = generator.nextInt(level.rowCount());
		
		SnakeObject snake = (SnakeObject) Object("Snake");
		
		while(snake.containsCoordinate(level.translate(new PointD(x, y))))
		{
			x = generator.nextInt(level.columnCount());
			y = generator.nextInt(level.rowCount());
		}
		
		
		FoodObject obj = new FoodObject(Parent,new PointD(level.translateX(x),level.translateY(y)),level.BlockSize());
		obj.setObjectName("Food");
		addObject(obj);
	}
	
	private List<MWidget> objects;
	private MainWindow Parent;
	private LevelObject level;
}
