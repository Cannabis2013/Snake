package Snake;

import baseKit.MObject;

public class OverlayController extends MObject {
	public OverlayController(MainWindow parent) {
		
	}
	
	public void setPoint(int p)
	{
		gamePoint = p;
	}
	
	public int Point()
	{
		return gamePoint;
	}
	
	public void drawBox()
	{
		
	}
	
	
	
	private int gamePoint = 0;
}
