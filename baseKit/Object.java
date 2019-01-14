package baseKit;


public class Object  {
	
	public Object() {
		id++;
	}
	
	/*
	 * Meta information related
	 */
	
	public String ClassName()
	{
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
