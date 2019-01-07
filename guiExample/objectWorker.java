package guiExample;

import baseKit.MHObject;
import baseKit.MHWidget;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class objectWorker extends Worker{
	private KeyCode kCode;
	
	
	public objectWorker(MHWidget parent,MHObject o, KeyEvent kE) {
		super(parent);
		kCode = kE.getCode();
	}
	
	public objectWorker(MHObject o, KeyEvent kE) {
		super(null);
		kCode = kE.getCode();
	}
	
	public void setParent(MHWidget parent)
	{
		Parent = (MainWindow) parent;
	}
	
	public KeyCode getKeyCode()
	{
		return kCode;
	}
	
	@Override
	public void run() {
		
	}
	
	public void Stop()
	{
		stopThread = true;
	}
}
