package baseKit;


public class MObject  {
	
	public MObject() {
		id++;
	}
	
	protected void print(String txt)
	{
		System.out.println(txt);
	}
	
	protected void print(String txt, printMode mode)
	{
		if (mode == printMode.lineBreak)
			System.out.println(txt);
		else
			System.out.print(txt);
	}
	
	protected void printCoordinates(double x, double y)
	{
		print(String.format("x: %1$,.10f y: %2$,.10f", x,y));
	}
	
	/*
	 * Meta information related
	 */
	
	public String ClassName()
	{
		print(getClass().toString(),printMode.lineBreak);
		return getClass().toString();
	}
	
	public void setObjectName(String name)
	{
		objectName = name;
	}
	
	public String ObjectName()
	{
		return objectName;
	}
	
	public enum printMode {lineBreak,noLinebreak};
	private String objectName = String.format("Object(%d)", id);
	private static int id = 1;
	
	// Trash methods. Just ignore.

	
}
