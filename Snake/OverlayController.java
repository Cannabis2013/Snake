package Snake;

import baseKit.MObject;

public class OverlayController extends MObject {
	public OverlayController(MainWindow parent) {
		super();
		Parent = parent;
	}
	
	public void setPoint(int p)
	{
		gamePoint = p;
	}
	
	public int Point()
	{
		return gamePoint;
	}
	MainWindow Parent;
	private int gamePoint = 0;
}
