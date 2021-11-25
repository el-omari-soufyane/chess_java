package view;

import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import controller.ChessController;

public class TimerThread extends SwingWorker<Integer, Void> {

	private TimerPanel time;
	private ChessController chessController;

	public TimerThread(TimerPanel time, ChessController chessController) {
		// TODO Auto-generated constructor stub
		this.time = time;
		this.chessController = chessController;
	}

	@Override
	protected Integer doInBackground() throws Exception {
		// TODO Auto-generated method stub
		time.switchTour();
		for (int i = 0; i < 50 && !isCancelled(); i++) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					time.setTimer();
				}
			});
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
