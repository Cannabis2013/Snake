package Snake;

import baseKit.MObject;
import baseKit.PointD;

public class LevelController extends MObject {
	public LevelController(MainWindow parent, int rows, int columns) {
		super();
		
		level = new LevelObject(parent);
		setRowCount(rows);
		setColumnCount(columns);
		level.setBorderWidth(20);
		
		level.setDefaultLocation();
	}
	
	/*
	 * Translation section
	 * Map internal grid coordinates to coordinates relative to the MainWindow widget.
	 */
	
	public double MapXToRelative(double x)
	{
		return level.translateX(x);
	}
	
	public double MapYToRelative(double y)
	{
		return level.translateY(y);
	}
	
	public PointD MapToRelative(double x, double y)
	{
		return level.translate(x, y);
	}
	
	public PointD MapToRelative(PointD coords)
	{
		return level.translate(coords);
	}
	
	/*
	 * Boundaries section
	 * Get left/right/upper/lower boundaries
	 */
	
	public double LeftBound()
	{
		return level.LeftBound();
	}
	
	public double RightBound()
	{
		return level.RightBound();
	}
	
	public double UpperBound()
	{
		return  level.UpperBound();
	}
	
	public double LowerBound()
	{
		return  level.LowerBound();
	}
	
	
	/*
	 * Set grid properties
	 * 		- Get quadratic side lenght
	 * 		- Get dimensions (Rowcount, ColumnCount)
	 * 		- Set dimensions (Rowcount, ColumnCount)
	 */
	
	public int BlockSize()
	{
		return level.BlockSize();
	}
	
	public int Rows()
	{
		return level.rowCount();
	}
	
	public int Columns()
	{
		return level.columnCount();
	}
	
	public void setRowCount(int r)
	{
		level.setRows(r);
	}
	
	public void setColumnCount(int c)
	{
		level.setColumns(c);
	}
	
	/*
	 * Calls LevelObject draw() method.
	 */
	
	public void drawLevel()
	{
		level.draw();
	}
	
	private LevelObject level;
}
