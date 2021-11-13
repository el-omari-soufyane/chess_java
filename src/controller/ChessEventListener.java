package controller;

import java.util.EventListener;

public interface ChessEventListener extends EventListener {
	public void actionADeclancher(ChessEvent evt);
}