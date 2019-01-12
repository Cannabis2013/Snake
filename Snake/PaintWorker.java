package Snake;

import baseKit.MWidget;

public class PaintWorker extends Thread {

	public PaintWorker(MWidget parent) {
		Parent = parent;
	}
	
	@Override
	public void run() {
		while(!stopThread)
		{
			Parent.drawEvent();
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
	private MWidget Parent;
}
