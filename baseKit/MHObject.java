package baseKit;

import java.util.ArrayList;
import java.util.List;
import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;

import javafx.scene.Node;

@SuppressWarnings("restriction")
public class MHObject extends Node {
	
	public MHObject() {
		childrens = new ArrayList<>();
		P = null;
		id++;
	}
	
	public MHObject(MHObject parent)
	{
		childrens = new ArrayList<>();
		P = parent;
		id++;
		
		P.addChild(this);
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
	 * Parent related section
	 */
	public boolean HasParent()
	{
		if(P != null)
			return true;
		else
			return false;
	}
	
	public void setParent(MHObject parent)
	{
		P = parent;
	}
	
	public MHObject Parent()
	{
		return P;
	}
	
	public void addChild(MHObject child)
	{
		if(child != null)
			childrens.add(child);
		else
			throw new IllegalArgumentException();
	}
	
	public MHObject ChildAt(int index)
	{
		if(childrens.size() > index && index >= 0)
			return childrens.get(index);
		else
			return null;
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
	private List<MHObject> childrens;
	protected MHObject P;
	
	// Trash methods. Just ignore.

	@Override
	protected boolean impl_computeContains(double arg0, double arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public BaseBounds impl_computeGeomBounds(BaseBounds arg0, BaseTransform arg1) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	protected NGNode impl_createPeer() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object impl_processMXNode(MXNodeAlgorithm arg0, MXNodeAlgorithmContext arg1) {
		// TODO Auto-generated method stub
		return null;
	}
}
