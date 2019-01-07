package baseKit;

import java.awt.Dimension;
import java.awt.Point;

import javafx.stage.Stage;

public class myStage extends Stage {

	public void setSize(int h, int w)
	{
		setWidth((double) w);
		setHeight((double) h);
	}
	public Dimension size()
	{
		int width = (int) getWidth(),
				height = (int) getHeight();

		return new Dimension(width, height);
	}

	public int width()
	{
		return (int) getWidth();
	}

	public int height()
	{
		return (int) getHeight();
	}


	public void setPosition(int x,int y)
	{
		setX(x);
		setY(y);
	}
	public void setPosition(Point pos)
	{
		setX(pos.getX());
		setY(pos.getY());
	}
	public Point position()
	{
		int X = (int) getX();
		int Y = (int) getY();

		return new Point(X,Y);
	}

	public int X()
	{
		return (int) getX();
	}

	public int Y()
	{
		return (int) getY();
	}

}
