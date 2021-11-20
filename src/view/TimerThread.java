package view;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

public class TimerThread extends SwingWorker<Integer, Void> {

	private TimerPanel time;

	public TimerThread(TimerPanel time) {
		// TODO Auto-generated constructor stub
		this.time = time;
	}

	@Override
	protected Integer doInBackground() throws Exception {
		// TODO Auto-generated method stub
		time.switchTour();
		for (int i = 0; i < 50 && !isCancelled(); i++) {
			final int niveau = i;
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					time.setTimer();
				}
			});
			System.out.println("Timer : "+ i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
		}
		return null;
	}
	
	public TimerPanel getTimerPanel() {
		return this.time;
	}
	
	@Override
	protected void done() {
		// TODO Auto-generated method stub
		if(isCancelled()) {			
			time.resetTimer();
			time.switchTour();
		} else {
			time.timerEnded();			
		}
	}
}
