package MainKit;

import java.util.ArrayList;
import java.util.List;
import BaseKit.PointD;
import BaseKit.View;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SnakeObject extends View {
	public SnakeObject(View parent, int l) {
		super(parent);
		bodyCoordinates = new ArrayList<PointD>();
		currentDirection = direction.left;
		lenght = l;
		width = 1;
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
			bodyCoordinates.add(new PointD(xPos + i*width, yPos));
	}
	
	public void setPosition(PointD pos)
	{
		double xPos = pos.X(), yPos = pos.Y();
		for (int i = lenght; i >= 0; i--)
			bodyCoordinates.add(new PointD(xPos + i*width, yPos));
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
	
	public double BlockWidth()
	{
		return width;
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
		{
			grow--;
			lenght++;
		}
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
	public void setWidth(double w)
	{
		width = w;
	}
	
	public double Speed()
	{
		if (speed == -1)
			return width;
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
		PointD pos = bodyCoordinates.get(bodyCoordinates.size() -1);
		painter.setFill(Color.BLUE);
		painter.fillRoundRect(pos.X(), pos.Y(), width, width,45, 45);
		painter.setFill(Color.BLACK);
		if(CurrentDirection() == direction.left)
			painter.fillRect(pos.X() + width/2, pos.Y(), width/2, width);
		else if(CurrentDirection() == direction.right)
			painter.fillRect(pos.X(), pos.Y(), width/2, width);
		if(CurrentDirection() == direction.up)
			painter.fillRect(pos.X(), pos.Y() + width/2, width, width/2);
		else if(CurrentDirection() == direction.down)
			painter.fillRect(pos.X(), pos.Y(), width, width/2);
		
		for (int i = bodyCoordinates.size() - 2; i >= 0; i--) {
			pos = bodyCoordinates.get(i);
			painter.fillRect(pos.X(), pos.Y(), width, width);
		}
		
	}
	
	private int lenght, grow;
	public enum direction{up, down, left, right};
	private direction currentDirection;
	private List<PointD> bodyCoordinates;
	private double speed,width;
	private boolean dead = false;
}
