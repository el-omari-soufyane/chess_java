package model;

import java.util.Vector;

public class Knight extends Piece {

	public Knight(int x, int y, boolean blanche, String icon) {
		super(x, y, blanche, icon);
	}
	
	@Override
	public boolean isPossible(int x_dest, int y_dest, Vector<Piece> blanche, Vector<Piece> noir) {
		int x, y, x1, x2, x3, x4, y1, y2, y3, y4;
		System.out.println("Dest => x : " + x_dest + " y : " + y_dest + " => OldPiece : " + this);
		Piece possiblePiece = blanche.stream().filter(p -> p.getX() == x_dest && p.getY() == y_dest).findFirst().orElse(null);
		
		if(possiblePiece != null) {
			if(possiblePiece.isBlanche() && this.isBlanche()) return false;
			if(!possiblePiece.isBlanche() && !this.isBlanche()) return false;
		}
		x = x_dest; y = y_dest;
		x1 = getX() + 1; x2 = getX() + 2 ; x3 = getX() - 1; x4 = getX() - 2;
		y1 = getY() + 1 ; y2 = getY() + 2 ; y3 = getY() - 1; y4 = getY() - 2;
		
		if((x != x1 || y != y4) && (x != x2 || y != y3 ) && (x != x2 || y != y1) && (x != x1 || y != y2) && 
				(x != x3 || y != y4) && (x != x4 || y != y3 ) && (x != x4 || y != y1) && (x != x3 || y != y2)) {
				return false;
		}
		
		return true;
	}

}
