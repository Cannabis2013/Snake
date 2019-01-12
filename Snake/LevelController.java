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
		level.setverticalTopMargin(50);
		level.setY(0);
		level.setX(parent.Width()*0.5 - level.width()*0.5 - level.BorderWidth());
		
	}
	
	public double BorderWidth()
	{
		return level.BorderWidth();
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
	
	public double Height()
	{
		return level.height() + level.translateY(0);
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
	
	public double BlockSize()
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
