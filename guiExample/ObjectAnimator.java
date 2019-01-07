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
			double dx = 0, dy = 0;
			if(target.CurrentDirection() == direction.right)
				dx = target.Speed() / PollRate();
			else if(target.CurrentDirection() == direction.left)
				dx = -(target.Speed() / PollRate());
			else if(target.CurrentDirection() == direction.up)
				dy = -(target.Speed() / PollRate());
			else if(target.CurrentDirection() == direction.down)
				dy = target.Speed() / PollRate();
			
			target.move(dx, dy);
			
			try {
				sleep(pollResolution);
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
