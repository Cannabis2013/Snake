package guiExample;

import java.util.ArrayList;
import java.util.List;
import baseKit.PointD;
import guiExample.SnakeObject.direction;
import baseKit.MHObject;
import baseKit.MHWidget;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class SnakeObject extends MHObject {
	public SnakeObject(MHObject parent) {
		super(parent);
		bodyCoordinates = new ArrayList<PointD>();
		headRadius = 40;
		currentDirection = direction.right;
		speed = 200;
		turn = 0;
	}
	
	public void reBirth()
	{
		bodyCoordinates.clear();
	}
	
	// Position section
	
	public void setPosition(double xPos, double yPos)
	{
		double y = (double) Parent().Height() - (yPos + headRadius);
		bodyCoordinates.add(new PointD(xPos, y));
	}
	
	public PointD Position()
	{
		return bodyCoordinates.get(bodyCoordinates.size() -1);
	}
	
	/*
	 * Movement and direction related
	 * - Move
	 * - Direction
	 * - Speed
	 */
	
	public void move(double dx, double dy)
	{
		if(dead)
			return;
		
		PointD cPos = Position(),
				nPos = new PointD(cPos.X() + dx,cPos.Y() + dy);
		
		if(dx != 0)
			turn -= dx;
		else if(dy != 0)
			turn -= dy;
		print(String.format(format, args));
		if(CheckifDead(nPos))
		{
			
			print("Collusion");
			dead = true;
		}
		else
		{
			bodyCoordinates.add(nPos);
			if(lenght < 0)
				bodyCoordinates.remove(0);
			lenght--;
		}
	}
	
	public boolean CheckifDead(PointD pos)
	{
		
		PointD tempPos = new PointD(pos.X(),pos.Y());
		
		if(currentDirection == direction.up)
			tempPos.decrementY(headRadius - 10);
		else if(currentDirection == direction.down)
			tempPos.incrementY(headRadius- 10);
		else if(currentDirection == direction.left)
			tempPos.decrementX(headRadius- 10);
		else
			tempPos.incrementX(headRadius - 10);
		
		for (int i = bodyCoordinates.size() - 2;i >= 0;i--) {
			PointD pointD = bodyCoordinates.get(i);
			if(tempPos.Equals(pointD))
				return true;
		}
		return false;
	}
	
	public void setCurrentDirection(direction dir)
	{
		currentDirection = dir;
	}
	
	public void setCurrentDirection(KeyCode key)
	{
		if(CurrentDirection() == direction.left && key == KeyCode.RIGHT)
			return;
		else if(CurrentDirection() == direction.right && key == KeyCode.LEFT)
			return;
		else if(CurrentDirection() == direction.up && key == KeyCode.DOWN)
			return;
		else if(CurrentDirection() == direction.down && key == KeyCode.UP)
			return;
		else if(turn > 0)
			return;
		
		turn = headRadius;
		
		if(key == KeyCode.LEFT)
			currentDirection = direction.left;
		else if(key == KeyCode.RIGHT)
			currentDirection = direction.right;
		else if(key == KeyCode.UP)
			currentDirection = direction.up;
		else if(key == KeyCode.DOWN)
			currentDirection = direction.down;
		else
			return;
	}
	
	public direction CurrentDirection()
	{
		return currentDirection;
	}
	
	public void setSpeed(double dT)
	{
		speed = dT;
	}
	
	public double Speed()
	{
		return speed;
	}
	
	// Draw section
	
	public void draw()
	{
		if(bodyCoordinates.isEmpty())
			return;
		paintBody();
		paintHead();
	}
	
	private void paintHead()
	{
		MHWidget parent = (MHWidget) P;
		GraphicsContext gC = parent.getPainter();
		gC.setFill(Color.BLUE);
		PointD pos = bodyCoordinates.get(bodyCoordinates.size() -1);
		gC.fillArc(pos.X(), pos.Y(), headRadius, headRadius, 0, 360, ArcType.ROUND);
	}
	
	private void paintBody()
	{
		MHWidget parent = (MHWidget) P;
		GraphicsContext gC = parent.getPainter();
		
		gC.setFill(Color.BLACK);
		
		for (int i = bodyCoordinates.size() - 1; i >= 0; i--) {
			PointD pos = bodyCoordinates.get(i);
			gC.fillRoundRect(pos.X(), pos.Y(), headRadius, headRadius,45,45);
		}
	}
	
	public MHWidget Parent()
	{
		return (MHWidget) P;
	}
	private int lenght = 400;
	public enum direction{up, down, left, right};
	private direction currentDirection;
	private List<PointD> bodyCoordinates;
	private double headRadius, speed;
	private double turn;
	private boolean dead = false;
}
