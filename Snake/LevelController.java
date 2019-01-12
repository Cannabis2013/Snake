package Snake;

import baseKit.MObject;
import baseKit.PointD;

public class LevelController extends MObject {
	public LevelController() {
		super();
	}
	
	public double MapXToRelative(double x)
	{
		return level.translateX(x);
	}
	
	public double MapYToRelative(double y)
	{
		return level.translateY(y);
	}
	
	public PointD MapToRelative(PointD coords)
	{
		return level.translate(coords);
	}
	
	public void drawLevel()
	{
		level.draw();
	}
	
	private LevelObject level;
}
