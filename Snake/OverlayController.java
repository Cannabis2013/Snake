package Snake;

import baseKit.MObject;

public class OverlayController extends MObject {
	public OverlayController(MainWindow parent) {
		super();
		Parent = parent;
	}
	
	public void setPoint(int p)
	{
		point = p;
	}
	
	public int Point()
	{
		return point;
	}
	MainWindow Parent;
	private int point = 0;
}
