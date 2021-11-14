package model;

import java.util.Vector;

public class Rook extends Piece {

	public Rook(int x, int y, boolean blanche, String icon) {
		super(x, y, blanche, icon);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isPossible(int x_dest, int y_dest, Vector<Piece> blanche, Vector<Piece> noir) {
		// TODO Auto-generated method stub
		System.out.println("Dest => x : " + x_dest + " y : " + y_dest + " => OldPiece : " + this);
		Piece possiblePiece = blanche.stream().filter(p -> p.getX() == x_dest && p.getY() == y_dest).findFirst().orElse(null);
		possiblePiece = noir.stream().filter(p -> p.getX() == x_dest && p.getY() == y_dest).findFirst().orElse(possiblePiece);
		
		if(possiblePiece != null) {
			if(possiblePiece.isBlanche() && this.isBlanche()) return false;
			if(!possiblePiece.isBlanche() && !this.isBlanche()) return false;
		}
	
		if(y_dest != getY() && x_dest != getX()) return false;
		
		if(y_dest < getY()) {
			boolean isBlocking;
			for(int i=1; i <= getY(); i++) {
				isBlocking = isBlocking(getX(), getY()-i, blanche);
				if(!isBlocking && getY()-i == y_dest) break;
				if(isBlocking) return false;
			}
		}
		if(y_dest > getY()) {
			boolean isBlocking;
			for(int i=1; i <= getY(); i++) {
				isBlocking = isBlocking(getX(), getY()+i, blanche);
				if(!isBlocking && getY()+i == y_dest) break;
				if(isBlocking) return false;
			}
		}
		if(x_dest > getX()) {
			boolean isBlocking;
			for(int i=1; i <= getX(); i++) {
				isBlocking = isBlocking(getX()+i, getY(), blanche);
				if(!isBlocking && getX()+i == x_dest) break;
				if(isBlocking) return false;
			}
		}
		if(x_dest < getX()) {
			boolean isBlocking;
			for(int i=1; i <= getX(); i++) {
				isBlocking = isBlocking(getX()-i, getY(), blanche);
				if(!isBlocking && getX()-i == x_dest) break;
				if(isBlocking) return false;
			}
		}
		
		/*boolean est = isBlocking(x_dest+1, y_dest, blanche);
		boolean ouest = isBlocking(x_dest-1, y_dest, blanche);
		boolean sud = isBlocking(x_dest, y_dest+1, blanche);
		boolean nord = isBlocking(x_dest, y_dest-1, blanche);
		if(est || ouest || sud || !nord) return false;*/
		
		return true;
	}
	
	private boolean isBlocking(int x, int y, Vector<Piece> blanche) {
		for(Piece p : blanche) {
			if(p.getX() == x && p.getY() == y) return true;
		}
		return false;
	}

}
