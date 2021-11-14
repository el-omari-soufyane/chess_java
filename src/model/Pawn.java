package model;

import java.util.Vector;

public class Pawn extends Piece {
	
	private boolean premierDeplace;

	public Pawn(int x, int y, boolean blanche, String icon) {
		super(x, y, blanche, icon);
		premierDeplace = true;
		// TODO Auto-generated constructor stub
	}
	
	public boolean isPremierDeplace() {
		return premierDeplace;
	}

	public void setPremierDeplace(boolean premierDeplace) {
		this.premierDeplace = premierDeplace;
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
			if(!possiblePiece.isBlanche() && y_dest == getY()-1 && x_dest == getX()) return false;
			if(!possiblePiece.isBlanche() && y_dest == getY()-1 && (x_dest == getX()-1 || x_dest == getX()+1)) return true;
		}
		
		if(x_dest != getX() || y_dest > getY()) return false;
		
		if(!isPremierDeplace()) {
			if(y_dest < getY() - 1)
				return false;
		} else {
			if(y_dest < getY() - 2)
				return false;
		}
		
		setPremierDeplace(false);
		return true;
	}

}
