package model;

import java.util.Vector;

import controller.ChessEvent;
import controller.ChessEventListener;
import controller.ChessEventNotifieur;

public class Piece {
	private int x;
	private int y;
	private boolean blanche;
	private String icon;
	private ChessEventNotifieur notifieur = new ChessEventNotifieur();
	
	public Piece(int x, int y, boolean blanche, String icon) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.blanche = blanche;
		this.icon = icon;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Piece getXY() {
		return this;
	}
	
	public void setXY(int x_dest, int y_dest, Piece p) {
		String oldX = "" + p.getX();
		String oldY = "" + p.getY();
		
		Vector<Object> piece = new Vector<Object>(3);
		piece.add(oldX); piece.add(oldY);
		
		this.x = x_dest;
		this.y = y_dest;
		
		piece.add(getXY());
		notifieur.diffuserChessEvent(new ChessEvent(this, piece));
	}

	public boolean isBlanche() {
		return blanche;
	}

	public void setBlanche(boolean blanche) {
		this.blanche = blanche;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public void addChessEventListener(ChessEventListener listener) {
		notifieur.addChessEventListener(listener);
	}
	
	public boolean isPossible(int x_dest, int y_dest, Vector<Piece> blanche, Vector<Piece> noir) {
		return false;
	}
	
	public boolean isBlocking(int x, int y, Vector<Piece> blanche, Vector<Piece> noir) {
		for(Piece p : blanche) {
			if(p.getX() == x && p.getY() == y) return true;
		}
		for(Piece p : noir) {
			if(p.getX() == x && p.getY() == y) return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Piece [x=" + x + ", y=" + y + ", blanche=" + blanche + "]";
	}
	
}
