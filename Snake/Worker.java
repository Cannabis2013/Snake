package Snake;

import baseKit.MWidget;

public class Worker extends Thread {
	
	protected static boolean stopAllThreads = false;
	protected boolean stopThread = false;
	protected int pollResolution = 10;
	protected MWidget Parent;
	
	public Worker(MWidget parent) {
		Parent = parent;
	}
	public Worker()
	{}
	
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
}
