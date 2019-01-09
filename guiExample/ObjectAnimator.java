package guiExample;

public class ObjectAnimator extends Worker {

	public ObjectAnimator(GameController g,SnakeObject obj) {
		target = obj;
		gCon = g;
	}
	public ObjectAnimator() {
		
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
			//target.move(d);
			
			gCon.moveEvent();
			
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
	private GameController gCon;
	private boolean isBusy = false;
}
