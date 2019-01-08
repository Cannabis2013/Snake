package baseKit;

import java.util.ArrayList;
import java.util.List;


public class MHObject  {
	
	public MHObject() {
		
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
