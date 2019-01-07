package guiExample;

import java.util.ArrayList;

import baseKit.MHObject;
import baseKit.MHWidget;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class WorkerController {

	public WorkerController(MHWidget parent) {
		objectWorkers = new ArrayList<objectWorker>();
		Parent = parent;
	}
	
	public void addWorker(MHObject obj,KeyEvent event)
	{
		
	}
	
	public void setPollRate(int rate)
	{
		pollRate = rate;
	}
	
	
	public int PollRate()
	{
		return pollRate;
	}
	public void removeWorker(objectWorker worker)
	{
		worker.Stop();
		objectWorkers.remove(worker);
	}
	
	public boolean isKeyPressed(KeyEvent kEvent)
	{
		for(objectWorker objThread : objectWorkers)
		{
			if(objThread.getKeyCode() == kEvent.getCode())
				return true;
		}
		return false;
	}
	
	public void stopObjectWorker(KeyCode kCode)
	{
		objectWorker temp = null;
		for(objectWorker objWorker : objectWorkers)
		{
			if(kCode == objWorker.getKeyCode())
			{
				temp = objWorker;
				objWorker.Stop();
			}
		}
		objectWorkers.remove(temp);
	}
	
	private ArrayList<objectWorker> objectWorkers;
	private MHWidget Parent;
	private int pollRate = 100;
}
