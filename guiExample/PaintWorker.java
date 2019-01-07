package guiExample;

import baseKit.MHWidget;

public class PaintWorker extends Worker {

	public PaintWorker(MHWidget parent) {
		super(parent);
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

}
