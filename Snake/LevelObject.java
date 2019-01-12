package Snake;

import baseKit.MWidget;
import baseKit.PointD;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LevelObject extends MWidget {
	
	public LevelObject(MainWindow parent) {
		super(parent);
		rows = 0;
		columns = 0;
		borderWidth = 20;
		height = parent.Height();
		xPos = 20;
		yPos = 20;
		verticalPadding = 0;
	}
	
	public void setDefaultLocation()
	{
		height = Parent().Height() - 2*borderWidth - verticalPadding;
		xPos = (int) Parent().Width()*0.5 - 0.5*columns*BlockSize() - borderWidth;
		yPos = Parent().Height()*0.5 - 0.5*rows*BlockSize() - borderWidth;
	}
	
	/*
	 * Border section
	 * 		- Get border width
	 * 		- Set border width
	 */
	
	public double BorderWidth()
	{
		return borderWidth;
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
		return  xPos + x*BlockSize() + borderWidth;
	}
	
	public double translateY(double y)
	{
		double b = BlockSize();
		return  yPos + y*BlockSize() + borderWidth;
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
				 tx = translateX(x),
				ty = translateY(y);
		
		return new PointD(tx, ty);
	}
	
	/*
	 * Boundaries section
	 * Get left/right/upper/lower boundaries
	 */
	
	public double LeftBound()
	{
		return translateX(0);
	}
	
	public double RightBound()
	{
		return translateX(0) + width() - BlockSize();
	}
	
	public double UpperBound()
	{
		return  translateY(0);
	}
	
	public double LowerBound()
	{
		return  translateY(0) + height();
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
	
	/*
	 * Grid section
	 * 		-Get grid dimensions
	 * 		-Set grid dimensions
	 */
	
	public int rowCount()
	{
		return rows;
	}
	
	public int columnCount()
	{
		return columns;
	}
	
	public void setRows(int r)
	{
		rows = r;
	}
	
	public void setColumns(int c)
	{
		columns = c;
	}
	
	public double width()
	{
		return columns*BlockSize();
	}
	
	public double height()
	{
		return Parent().Height() - 2*borderWidth - verticalPadding;
	}
	
	public void setVerticalPadding(double vPad)
	{
		verticalPadding = vPad;
	}
	
	public double VerticalPadding()
	{
		return verticalPadding;
	}
	
	/*
	 * Draw section
	 * Re-implements MWidget.draw()
	 */
	
	public void paint()
	{
		GraphicsContext gC = P.getPainter();
		
		// Draw background color
		
		//Draw border
		gC.setFill(Color.BROWN);
		gC.fillRoundRect(translateX(0) - borderWidth, translateY(0) - borderWidth, columns*BlockSize() + borderWidth*2, height() + borderWidth*2,30,30);
		gC.setFill(Color.DARKGREEN);
		gC.fillRect(translateX(0), translateY(0), columns*BlockSize(), height());
	}
	private double verticalPadding;
	private double height;
	private int rows, columns;
	private double xPos, yPos;
	private double borderWidth;
}
