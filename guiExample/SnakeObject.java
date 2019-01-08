package guiExample;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import baseKit.PointD;
import baseKit.MHWidget;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class SnakeObject extends MHWidget {
	public SnakeObject(MHWidget parent) {
		super(parent);
		bodyCoordinates = new ArrayList<PointD>();
		headRadius = 20;
		currentDirection = direction.right;
		speed = 20;
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
	
	public void move(double d)
	{
		double dx = 0, dy = 0;
		
		if(CurrentDirection() == direction.right)
			dx = d;
		else if(CurrentDirection() == direction.left)
			dx = -d;
		else if(CurrentDirection() == direction.up)
			dy = -d;
		else if(CurrentDirection() == direction.down)
			dy = d;
		
		if(dead)
			return;
		
		PointD cPos = Position(),
				nPos = new PointD(cPos.X() + dx,cPos.Y() + dy);
		
		if(dx != 0 && turn > 0)
		{
			if(dx < 0)
				dx *= -1;
			turn -= dx;
		}
		else if(dy != 0 && turn > 0)
		{
			if(dy < 0)
				dy *= -1;
			turn -= dy;
		}
		
		if(nPos.X() > Parent().Width())
		{
			nPos.setX(0);
		}
		else if(nPos.X() < 0)
		{
			nPos.setX(Parent().Width() - headRadius);
		}
		else if(nPos.Y() > Parent().Height())
		{
			nPos.setY(0);
		}
		else if(nPos.Y() < 0)
		{
			nPos.setY(Parent().Height() - headRadius - 1);
		}
		
		print(String.format("Snake x : %1$,.2f Snake y : %2$,.2f", nPos.X(),nPos.Y()));
	
		bodyCoordinates.add(nPos);
		if(lenght < 0)
			bodyCoordinates.remove(0);
		lenght--;
		
		if(CheckifDead(nPos))
		{
			print("Collusion");
			dead = true;
		}
	}
	
	public void setLenght(int l)
	{
		lenght = l;
	}
	
	private boolean CheckifDead(PointD pos)
	{
		
		PointD tempPos = new PointD(pos.X(),pos.Y());
		
		if(currentDirection == direction.up)
			tempPos.decrementY(headRadius);
		else if(currentDirection == direction.down)
			tempPos.incrementY(headRadius);
		else if(currentDirection == direction.left)
			tempPos.decrementX(headRadius);
		else
			tempPos.incrementX(headRadius);
		
		
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
			gC.fillRect(pos.X(), pos.Y(), headRadius, headRadius);
		}
	}
	
	public MHWidget Parent()
	{
		return (MHWidget) P;
	}
	private int lenght = 10;
	public enum direction{up, down, left, right};
	private direction currentDirection;
	private List<PointD> bodyCoordinates;
	private double headRadius, speed;
	private double turn;
	private boolean dead = false;
}
