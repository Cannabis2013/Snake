package Snake;

import baseKit.View;
import baseKit.PointD;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class FoodObject extends View {

	public FoodObject(View parent, PointD pos, double Width) {
		super(parent);
		setWidth(Width);
		setHeight(Width);
		Pos = new PointD(pos.X(), pos.Y());
	}
	
	public void draw()
	{
		GraphicsContext gC = P.getPainter();
		gC.setFill(Color.RED);
		
		gC.fillRect(Pos.X(), Pos.Y(), Width(), Width());
	}
	
	public PointD Position()
	{
		return Pos;
	}
	
	public int GrowAmount()
	{
		return growAmount;
	}
	
	private int growAmount = 1;
	private PointD Pos;
}
