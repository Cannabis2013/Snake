package Snake;

import baseKit.View;

public class PaintWorker extends Thread {

	public PaintWorker(View parent) {
		Parent = parent;
	}
	
	@Override
	public void run() {
		while(!stopThread)
		{
			Parent.draw();
			try {
				sleep(pollResolution);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void setPollRate(int rate)
	{
		if(rate > 1000)
			throw new IllegalArgumentException();
		pollResolution = 1000/rate;
	}
	
	public int PollRate()
	{
		return 1000/pollResolution;
	}
	
	public void Stop()
	{
		stopThread = true;
	}
	
	private boolean stopThread = false;
	private int pollResolution = 10;
	private View Parent;
}
