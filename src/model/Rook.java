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
		
		boolean isBlocking = true;
		int nbPas = y_dest != getY() ? Math.abs(y_dest - getY()) : Math.abs(x_dest - getX());
		for(int i=1; i < nbPas; i++) {
			if(y_dest < getY()) {
				isBlocking = isBlocking(getX(), getY()-i, blanche);
				if(!isBlocking && getY()-i == y_dest) break;
			} else if(y_dest > getY()) {
				isBlocking = isBlocking(getX(), getY()+i, blanche);
				if(!isBlocking && getY()+i == y_dest) break;
			} else if(x_dest > getX()) {
				isBlocking = isBlocking(getX()+i, getY(), blanche);
				if(!isBlocking && getX()+i == x_dest) break;
			} else if(x_dest < getX()) {
				isBlocking = isBlocking(getX()-i, getY(), blanche);
				if(!isBlocking && getX()-i == x_dest) break;
			}
			if(isBlocking) return false;
		}
		return true;
	}
}
