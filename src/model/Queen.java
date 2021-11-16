package model;

import java.util.Vector;

public class Queen extends Piece {

	public Queen(int x, int y, boolean blanche, String icon) {
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
		
		if(y_dest == getY() || x_dest == getX()) {			
			boolean isBlocking = true;
			int nbPas = y_dest != getY() ? Math.abs(y_dest - getY()) : Math.abs(x_dest - getX());
			
			for(int i=1; i < nbPas; i++) {
				if(y_dest < getY()) {
					isBlocking = isBlocking(getX(), getY()-i, blanche, noir);
					if(!isBlocking && getY()-i == y_dest) break;
				} else if(y_dest > getY()) {
					isBlocking = isBlocking(getX(), getY()+i, blanche, noir);
					if(!isBlocking && getY()+i == y_dest) break;
				} else if(x_dest > getX()) {
					isBlocking = isBlocking(getX()+i, getY(), blanche, noir);
					if(!isBlocking && getX()+i == x_dest) break;
				} else if(x_dest < getX()) {
					isBlocking = isBlocking(getX()-i, getY(), blanche, noir);
					if(!isBlocking && getX()-i == x_dest) break;
				}
				System.out.println("is Blocking : " + isBlocking + " - Like rook");
				if(isBlocking) return false;
			}
		} else {			
			int nbPasY = Math.abs(y_dest - getY());
			int nbPasX = Math.abs(x_dest - getX());
			System.out.println("Pas X : " + nbPasX + " | Pas Y : " + nbPasY);
			if(nbPasX != nbPasY) return false;
			else {
				boolean isBlocking = true;
				for(int i=1; i<nbPasX; i++) {
					if(y_dest < getY() && x_dest < getX()) {
						isBlocking = isBlocking(getX()-i, getY()-i, blanche, noir);
						if(isBlocking) return false;
					} else if(y_dest < getY() && x_dest > getX()) {
						isBlocking = isBlocking(getX()+i, getY()-i, blanche, noir);
						if(isBlocking) return false;
					} else if(y_dest > getY() && x_dest < getX()) {
						isBlocking = isBlocking(getX()-i, getY()+i, blanche, noir);
						if(isBlocking) return false;
					} else if(y_dest > getY() && x_dest > getX()) {
						isBlocking = isBlocking(getX()+i, getY()+i, blanche, noir);
						if(isBlocking) return false;
					}
				}
			}
		}
		return true;
	}

}
