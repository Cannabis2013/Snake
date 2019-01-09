package guiExample;

import baseKit.MWidget;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Level extends MWidget {
	
	public Level(MainWindow parent, int Rows, int Columns, int BlockSize) {
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
		GraphicsContext gC = Parent().getPainter();
		
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
	
	public MainWindow Parent()
	{
		return (MainWindow) P;
	}
	private int rows, columns, blockSize;
}
