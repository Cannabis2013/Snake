package Snake;

import baseKit.MWidget;
import baseKit.PointD;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LevelObject extends MWidget {
	
	public LevelObject(MainWindow parent, int Rows, int Columns) {
		super(parent);
		rows = Rows;
		columns = Columns;
		borderWidth = 20;
		height = Parent().Height() - 2*borderWidth - 50;
		xPos = (int) Parent().Width()*0.5 - 0.5*columns*BlockSize();
		yPos = Parent().Height()*0.5 - 0.5*rows*BlockSize();
	}
	
	public void setBorderWidth(double w)
	{
		borderWidth = w;
	}
	
	public int BlockSize()
	{
		return (int)height/rows;
	}
	
	// Translate grid coordinates to global coordinates
	
	public double translateX(double x)
	{
		return  xPos + x*BlockSize();
	}
	
	public double translateY(double y)
	{
		return  yPos + y*BlockSize();
	}
	
	public PointD translate(double x, double y)
	{
		double tx = translateX(x),
				ty = translateY(y);
		
		return new PointD(tx, ty);
	}
	
	public PointD translate(PointD coords)
	{
		double x = coords.X(), y = coords.Y(),
				 tx = Parent().Width()*0.5 - 0.5*columns*BlockSize() + x*BlockSize(),
				ty = Parent().Height()*0.5 - 0.5*rows*BlockSize() + y*BlockSize();
		
		return new PointD(tx, ty);
	}
	
	/*
	 * Boundaries section
	 * Get boundaries
	 */
	
	public double LeftBound()
	{
		return translateX(0);
	}
	
	public double RightBound()
	{
		return Parent().Width()*0.5 - 0.5*columns*BlockSize() + columns*BlockSize() - BlockSize();
	}
	
	public double UpperBound()
	{
		return  translateY(0);
	}
	
	/*
	 * Position section
	 * 		- Get position
	 * 		- Set position
	 */
	
	public double x()
	{
		return xPos;
	}
	
	public double y()
	{
		return yPos;
	}
	
	public void setX(double x)
	{
		xPos = x;
	}
	
	public void setY(double y)
	{
		yPos = y;
	}
	
	public double LowerBound()
	{
		return  Parent().Height()*0.5 - 0.5*rows*BlockSize() + rows*BlockSize();
	}
	
	public int rowCount()
	{
		return rows;
	}
	
	public int columnCount()
	{
		return columns;
	}
	
	public double width()
	{
		return columns*BlockSize();
	}
	
	public double height()
	{
		return rows*BlockSize();
	}
	
	public void draw()
	{
		GraphicsContext gC = P.getPainter();
		
		// Draw background color
		
		//Draw border
		gC.setFill(Color.BROWN);
		gC.fillRoundRect(translateX(0) - borderWidth, translateY(0) - borderWidth, columns*BlockSize() + borderWidth*2, height() + borderWidth*2,30,30);
		gC.setFill(Color.DARKGREEN);
		gC.fillRect(translateX(0), translateY(0), columns*BlockSize(), height());
		
		/*
		 * Draw grid
		 * Set linewidth
		 * Draw grid
		 */
		
		gC.setFill(Color.BLACK);
		gC.setLineWidth(2);

	}
	private double height;
	private int rows, columns;
	private double xPos, yPos;
	private double borderWidth;
}
