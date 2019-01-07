package baseKit;

public class PointD {
	
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
		return (compare.X() == X() && compare.Y() == Y());
	}
	
	public boolean WithinRange(PointD compare, double Range)
	{
		return ((compare.X() + Range == X() ||  compare.X() - Range == X()) &&  
				(compare.Y() + Range == Y()) || compare.Y() - Range == Y());
	}
	
	private double x;
	private double y;
}
