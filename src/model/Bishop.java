package model;

import java.util.Vector;

public class Bishop extends Piece {

	public Bishop(int x, int y, boolean blanche, String icon) {
		super(x, y, blanche, icon);
	}
	
	@Override
	public boolean isPossible(int x_dest, int y_dest, Vector<Piece> blanche, Vector<Piece> noir) {
		int cpt = 0;
		System.out.println("Dest => x : " + x_dest + " y : " + y_dest + " => OldPiece : " + this);
		Piece possiblePiece = blanche.stream().filter(p -> p.getX() == x_dest && p.getY() == y_dest).findFirst().orElse(null);
		
		if(possiblePiece != null) {
			if(possiblePiece.isBlanche() && this.isBlanche()) return false;
			if(!possiblePiece.isBlanche() && !this.isBlanche()) return false;
		}
		
		for(int i = 1; i < 7; i++) {
			if( (x_dest != getX()+i || y_dest != getY()-i) && (x_dest != getX()-i || y_dest != getY()-i) && (x_dest != getX()+i || y_dest != getY()+i) &&  (x_dest != getX()-i || y_dest != getY()+i ) ) {
				cpt++;
			}
			
		}
				
		if(cpt == 6) {
			return false;
		}else {
			for(int i = 1; i < 7; i++) {

				if(x_dest == getX()+i && ( y_dest == getY()-i || y_dest == getY()+i )) {
					for(int j=1;j<i;j++) {
						if(isBlocking(getX()+j, getY()-j, blanche)) {
							return false;
						}
					}
				
				}

				if(x_dest == getX()-i && y_dest == getY()-i) {
					for(int j=1;j<i;j++) {
						if(isBlocking(getX()-j, getY()-j, blanche) ) {
							return false;
						}
					}
				
				}

				if(x_dest == getX()+i && y_dest == getY()+i) {
					for(int j=1;j<i;j++) {
						if(isBlocking(getX()+j, getY()+j, blanche) ) {
							return false;
						}
					}
				
				}

				if(x_dest == getX()-i && y_dest == getY()+i) {
					for(int j=1;j<i;j++) {
						if(isBlocking(getX()-j, getY()+j, blanche) ) {
							return false;
						}
					}
				
				}
			}
		}
		
		return true;
	}

}
