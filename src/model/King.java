package model;

import java.util.Vector;

public class King extends Piece {

	public King(int x, int y, boolean blanche, String icon) {
		super(x, y, blanche, icon);
	}
	
	@Override
	public boolean isPossible(int x_dest, int y_dest, ListPieces blanche, ListPieces noir) {
		System.out.println("Dest => x : " + x_dest + " y : " + y_dest + " => OldPiece : " + this);
		Piece possiblePiece = blanche.getPieceByXY(x_dest, y_dest);
		
		if(possiblePiece != null) {
			if(possiblePiece.isBlanche() && this.isBlanche()) return false;
			if(!possiblePiece.isBlanche() && !this.isBlanche()) return false;
		}
		
		if( x_dest > getX()+1 || x_dest < getX()-1) return false;
		if(y_dest > getY()+1 || y_dest < getY()-1 ) return false;
		
		return true;
	}

}
