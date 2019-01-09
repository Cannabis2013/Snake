package guiExample;

import java.util.ArrayList;
import java.util.List;
import baseKit.PointD;
import baseKit.MWidget;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SnakeObject extends MWidget {
	public SnakeObject(MWidget parent, int l) {
		super(parent);
		bodyCoordinates = new ArrayList<PointD>();
		currentDirection = direction.left;
		lenght = l;
		SnakeWidth = 1;
		speed = -1;
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
	
	public void setPosition(PointD pos)
	{
		double xPos = pos.X(), yPos = pos.Y();
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
		if (speed == -1)
			return SnakeWidth;
		else
			return speed;
	}
	
	public boolean containsCoordinate(PointD pos)
	{
		PointD tempPos = pos.copy();
		for (int i = bodyCoordinates.size() - 1;i >= 0;i--) {
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
		GraphicsContext gC = P.getPainter();
		
		gC.setFill(Color.BLACK);
		
		for (int i = bodyCoordinates.size() - 1; i >= 0; i--) {
			PointD pos = bodyCoordinates.get(i);
			gC.fillRect(pos.X(), pos.Y(), SnakeWidth, SnakeWidth);
		}
	}
	
	private int lenght, grow;
	public enum direction{up, down, left, right};
	private direction currentDirection;
	private List<PointD> bodyCoordinates;
	private double SnakeWidth, speed;
	private boolean dead = false;
}
