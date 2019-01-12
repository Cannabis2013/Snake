package baseKit;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PointD extends MObject{
	
	public PointD(double X, double Y) {
		x = X;
		y = Y;
	}
	
	public PointD() {
		x = 0;
		y = 0;
	}
	
	public double X()
	{
		return x;
	}
	public double Y()
	{
		return y;
	}
	
	public void setX(double X)
	{
		x = X;
	}
	
	public void setY(double Y)
	{
		y = Y;
	}
	
	public void incrementX(double dx)
	{
		x += dx;
	}
	
	public void incrementY(double dy)
	{
		y += dy;
	}
	
	public void decrementX(double dx)
	{
		x -= dx;
	}
	
	public void decrementY(double dy)
	{
		y -= dy;
	}
	
	public boolean Equals(PointD compare)
	{
		return (round(compare.X(),2) == round(X(),2) && round(compare.Y(),2) == round(Y(),2));
	}
	
	public boolean WithinRange(PointD compare, double Range)
	{
		return ((compare.X() + Range == X() ||  compare.X() - Range == X()) &&  
				(compare.Y() + Range == Y()) || compare.Y() - Range == Y());
	}
	
	public PointD copy()
	{
		return new PointD(x, y);
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, RoundingMode.HALF_UP);
	    return bd.doubleValue();
	}
	
	public void printCoordinates()
	{
		print(String.format("x = %1$,.2f y = %2$,.2f", x,y));
	}
	
	private double x;
	private double y;
}
