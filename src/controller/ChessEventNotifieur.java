package controller;

import javax.swing.event.EventListenerList;

public class ChessEventNotifieur {
	private EventListenerList listenerList = new EventListenerList();

	public void addChessEventListener(ChessEventListener listener) {
		listenerList.add(ChessEventListener.class, listener);
	}

	public void removeChessEventListener(ChessEventListener listener) {
		listenerList.remove(ChessEventListener.class, listener);
	}

	public void diffuserChessEvent(ChessEvent evt) {
		Object[] listeners = listenerList.getListenerList();
		for (int i = 0; i < listeners.length; i = i + 2) {
			if (listeners[i] == ChessEventListener.class) {
				((ChessEventListener) listeners[i + 1]).actionADeclancher(evt);
			}
		}
	}
}