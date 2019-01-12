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
		xPos = 0;
		yPos = 0;
		verticalTopMargin = 0;
		verticalBottomMargin = 0;
	}
	
	public void setDefaultLocation()
	{
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
	
	public double BlockSize()
	{
		return height()/rows;
	}
	
	// Translate grid coordinates to global coordinates
	
	public double translateX(double x)
	{
		return  xPos + x*BlockSize() + borderWidth;
	}
	
	public double translateY(double y)
	{
		return  yPos + verticalTopMargin + y*BlockSize() + borderWidth;
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
	
	public double lastColumn()
	{
		return translateY(0) + BlockSize()*rowCount();
	}
	
	public double lastRow()
	{
		return translateX(0) + width();
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
		return Parent().Height() - 2*borderWidth - verticalTopMargin - verticalBottomMargin;
	}
	
	public void setverticalTopMargin(double vPad)
	{
		verticalTopMargin = vPad;
	}
	
	public double VerticalTopMargin()
	{
		return verticalTopMargin;
	}
	public void setVerticalBottomMargin(double vPad)
	{
		verticalBottomMargin = vPad;
	}
	
	public double VerticalBottomMargin()
	{
		return verticalBottomMargin;
	}
	
	
	/*
	 * Draw section
	 * Re-implements MWidget.draw()
	 */
	
	public void draw()
	{
		GraphicsContext gC = P.getPainter();
		
		// Draw background color
		
		//Draw border
		gC.setFill(Color.BROWN);
		gC.fillRoundRect(translateX(0) - borderWidth, translateY(0) - borderWidth, columns*BlockSize() + borderWidth*2, height() + borderWidth*2,30,30);
		gC.setFill(Color.DARKGREEN);
		gC.fillRect(translateX(0), translateY(0), columns*BlockSize(), height());
		
		for (int i = 0; i <= columns; i++)
			gC.strokeLine(translateX(i), translateY(0), translateX(i), lastColumn());
		for (int i = 0; i <= rows; i++) {
			gC.strokeLine(translateX(0), translateY(i), lastRow(), translateY(i));
		}
	}
	private double verticalTopMargin,verticalBottomMargin;
	private int rows, columns;
	private double xPos, yPos;
	private double borderWidth;
}
