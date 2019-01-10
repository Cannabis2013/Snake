package Snake;

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
		setWidth(1);
		setHeight(1);
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
			bodyCoordinates.add(new PointD(xPos + i*Width(), yPos));
	}
	
	public void setPosition(PointD pos)
	{
		double xPos = pos.X(), yPos = pos.Y();
		for (int i = lenght; i >= 0; i--)
			bodyCoordinates.add(new PointD(xPos + i*Width(), yPos));
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
			return Width();
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
		
		gC.setFill(Color.DARKGRAY);
		
		for (int i = bodyCoordinates.size() - 1; i >= 0; i--) {
			PointD pos = bodyCoordinates.get(i);
			gC.fillRect(pos.X(), pos.Y(), Width(), Width());
		}
	}
	
	private int lenght, grow;
	public enum direction{up, down, left, right};
	private direction currentDirection;
	private List<PointD> bodyCoordinates;
	private double speed;
	private boolean dead = false;
}
