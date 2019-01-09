package guiExample;

import baseKit.MWidget;
import baseKit.PointD;

public class FoodObject extends MWidget {

	public FoodObject(MainWindow parent) {
		super(parent);
		position = new PointD();
	}
	
	public void draw()
	{
		
	}
	
	private PointD position;
}
