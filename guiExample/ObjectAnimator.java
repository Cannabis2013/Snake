package guiExample;
import baseKit.MHWidget;
import guiExample.SnakeObject.direction;

public class ObjectAnimator extends Worker {

	public ObjectAnimator(MHWidget parent, SnakeObject obj) {
		super(parent);
		
		target = obj;
	}
	public ObjectAnimator(MHWidget parent) {
		super(parent);
		
		target = null;
	}
	
	public void setObject(SnakeObject obj)
	{
		target = obj;
	}
	
	public boolean IsWorking()
	{
		return isBusy;
	}
	
	public void Stop()
	{
		stopThread = true;
	}
	
	@Override
	public void run() {
		isBusy = true;
		while(!stopThread)
		{
			double d = target.Speed(); 
			target.move(d);
			
			try {
				sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		isBusy = false;
	}
	
	@SuppressWarnings("unused")
	private SnakeObject target;
	private boolean isBusy = false;
}
