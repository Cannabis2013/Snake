package Snake;

import baseKit.MWidget;
import baseKit.PointD;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class LevelObject extends MWidget {
	
	public LevelObject(MainWindow parent, int Rows, int Columns, int BlockSize) {
		super(parent);
		rows = Rows;
		columns = Columns;
		blockSize = BlockSize;
		borderWidth = 20;
	}
	
	public int BlockSize()
	{
		return blockSize;
	}
	
	public double translateX(double x)
	{
		return  Parent().Width()*0.5 - 0.5*columns*blockSize + x*blockSize;
	}
	
	public double translateY(double y)
	{
		return  Parent().Height()*0.5 - 0.5*rows*blockSize + y*blockSize;
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
				 tx = Parent().Width()*0.5 - 0.5*columns*blockSize + x*blockSize,
				ty = Parent().Height()*0.5 - 0.5*rows*blockSize + y*blockSize;
		
		return new PointD(tx, ty);
	}
	
	public double LeftBound()
	{
		return translateX(0);
	}
	
	public double RightBound()
	{
		return Parent().Width()*0.5 - 0.5*columns*blockSize + columns*blockSize - blockSize;
	}
	
	public double UpperBound()
	{
		return  translateY(0);
	}
	
	public double LowerBound()
	{
		return  Parent().Height()*0.5 - 0.5*rows*blockSize + rows*blockSize;
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
		return columns*blockSize;
	}
	
	public double height()
	{
		return rows*blockSize;
	}
	
	public void draw()
	{
		GraphicsContext gC = P.getPainter();
		
		// Draw background color
		
		//Draw border
		gC.setFill(Color.BROWN);
		gC.fillRoundRect(translateX(0) - borderWidth, translateY(0) - borderWidth, columns*blockSize + borderWidth*2, height() + borderWidth*2,30,30);
		gC.setFill(Color.DARKGREEN);
		gC.fillRect(translateX(0), translateY(0), columns*blockSize, height());
		
		/*
		 * Draw grid
		 * Set linewidth
		 * Draw grid
		 */
		
		gC.setFill(Color.BLACK);
		gC.setLineWidth(2);
		
		// Draw rows
		for (int i = 0; i <= rows; i++) {
			gC.strokeLine(translateX(0), i*blockSize + translateY(0), translateX(0) + blockSize*columns, i*blockSize + translateY(0));
		}
		
		// Draw columns
		for (int i = 0; i <= columns; i++) {
			gC.strokeLine(i*blockSize + translateX(0), translateY(0), i*blockSize + translateX(0), blockSize*rows + translateY(0));
		}
	}
	private int rows, columns, blockSize;
	private double borderWidth;
}
