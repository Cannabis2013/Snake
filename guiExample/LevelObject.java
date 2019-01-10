package guiExample;

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
	}
	
	public int BlockSize()
	{
		return blockSize;
	}
	
	public double translateX(int x)
	{
		return  Parent().Width()*0.5 - 0.5*columns*blockSize + x*blockSize;
	}
	
	public double translateY(int y)
	{
		return  Parent().Height()*0.5 - 0.5*rows*blockSize + y*blockSize;
	}
	
	public PointD translate(double x, double y)
	{
		double tx = Parent().Width()*0.5 - 0.5*columns*blockSize + x*blockSize,
				ty = Parent().Height()*0.5 - 0.5*rows*blockSize + y*blockSize;
		
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
		return Parent().Width()*0.5 - 0.5*columns*blockSize;
	}
	
	public double RightBound()
	{
		return Parent().Width()*0.5 - 0.5*columns*blockSize + columns*blockSize - blockSize;
	}
	
	public double UpperBound()
	{
		return  Parent().Height()*0.5 - 0.5*rows*blockSize;
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
		return columns - 1;
	}
	
	public double height()
	{
		return rows - 1;
	}
	
	public void draw()
	{
		GraphicsContext gC = P.getPainter();
		
		// Draw background color
		gC.setFill(Color.DARKGREEN);
		gC.fillRect(0, 0, Parent().Width(), Parent().Height());
		
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
}
