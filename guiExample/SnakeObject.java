package guiExample;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import baseKit.PointD;
import baseKit.MWidget;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

public class SnakeObject extends MWidget {
	public SnakeObject(MWidget owner,Level parent, int l) {
		P = parent;
		Owner = owner;
		bodyCoordinates = new ArrayList<PointD>();
		currentDirection = direction.left;
		lenght = l;
		SnakeWidth = parent().BlockSize();
		speed = SnakeWidth;
	}
	
	public boolean isDead()
	{
		return dead;
	}
	
	public void Kill()
	{
		dead = true;
	}
	
	// Position section
	
	public void setPosition(double xPos, double yPos)
	{
		for (int i = lenght; i >= 0; i--)
			bodyCoordinates.add(new PointD(xPos + i*SnakeWidth, yPos));
	}
	
	public PointD Position()
	{
		return bodyCoordinates.get(bodyCoordinates.size() -1);
	}
	
	/*
	 * Set body properties like lenght and width
	 */
	public int Lenght()
	{
		return lenght;
	}
	
	public void setLenght(int l)
	{
		lenght = l;
	}
	
	public void setWidth(int w)
	{
		SnakeWidth = w;
	}
	
	public double width()
	{
		return SnakeWidth;
	}
	
	
	/*
	 * Movement and direction related
	 * - Move
	 * - Direction
	 * - Speed
	 */
	
	public void moveToCoordinates(PointD pos, int g)
	{
		grow += g;
		bodyCoordinates.add(pos);
		if(grow <= 0)
			bodyCoordinates.remove(0);
		else
			grow--;
	}
	
	public void setCurrentDirection(direction dir)
	{
		currentDirection = dir;
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
	
	public boolean CheckifDead(PointD pos)
	{
		PointD tempPos = new PointD(pos.X(),pos.Y());
		for (int i = bodyCoordinates.size() - 2;i >= 0;i--) {
			PointD pointD = bodyCoordinates.get(i);
			if(tempPos.Equals(pointD))
				return true;
		}
		return false;
	}
	
	// Draw section
	
	public void draw()
	{
		if(bodyCoordinates.isEmpty())
			return;
		paintBody();
	}
	
	private void paintBody()
	{
		MWidget parent = Owner;
		GraphicsContext gC = parent.getPainter();
		
		gC.setFill(Color.BLACK);
		
		for (int i = bodyCoordinates.size() - 1; i >= 0; i--) {
			PointD pos = bodyCoordinates.get(i);
			gC.fillRect(pos.X(), pos.Y(), SnakeWidth, SnakeWidth);
		}
	}
	
	public boolean isOrtogonal(KeyCode key)
	{
		if(CurrentDirection() == direction.left && key == KeyCode.RIGHT)
			return true;
		else if(CurrentDirection() == direction.right && key == KeyCode.LEFT)
			return true;
		else if(CurrentDirection() == direction.up && key == KeyCode.DOWN)
			return true;
		else if(CurrentDirection() == direction.down && key == KeyCode.UP)
			return true;
		else
			return false;
	}
	
	public Level parent()
	{
		return (Level) P;
	}
	
	private int lenght, grow;
	public enum direction{up, down, left, right};
	private direction currentDirection;
	private List<PointD> bodyCoordinates;
	private double SnakeWidth, speed;
	private boolean dead = false;
	private MWidget Owner;
}
