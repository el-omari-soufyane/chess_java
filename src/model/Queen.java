package model;

import java.util.ArrayList;

public class Queen extends Piece {

	public Queen(int x, int y, boolean blanche, String icon) {
		super(x, y, blanche, icon);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isPossible(int x_dest, int y_dest, ListPieces blanche, ListPieces noir) {
		
		Piece possiblePiece;
		possiblePiece = blanche.getPieceByXY(x_dest, y_dest);
		if(possiblePiece != null) {
		}
		else possiblePiece = noir.getPieceByXY(x_dest, y_dest);
		
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
				if(isBlocking) return false;
			}
		} else {			
			int nbPasY = Math.abs(y_dest - getY());
			int nbPasX = Math.abs(x_dest - getX());
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
	
	@Override
	public ArrayList<Integer> colorCase(int x_dest, int y_dest, ListPieces blanche, ListPieces noir) {
	     ArrayList arraylist = new ArrayList();
	     Piece possiblePiece;
	     possiblePiece = blanche.getPieceByXY(x_dest, y_dest);
	     
	    for(int i=1;i<8;i++) {
	    	if(possiblePiece.isPossible(getX()+i, getY()-i, blanche, noir)) {
	    		 arraylist.add(getX()+i);   
				 arraylist.add(getY()-i); 
		     }
	    	 if(possiblePiece.isPossible(getX()+i, getY()+i, blanche, noir)) {
	    		 arraylist.add(getX()+i);   
				 arraylist.add(getY()+i); 
		     }
	    	 if(possiblePiece.isPossible(getX()-i, getY()-i, blanche, noir)) {
	    		 arraylist.add(getX()-i);   
				 arraylist.add(getY()-i); 
		     }
	    	 if(possiblePiece.isPossible(getX()-i, getY()+i, blanche, noir)) {
	    		 arraylist.add(getX()-i);   
				 arraylist.add(getY()+i); 
		     }
	    	 if(possiblePiece.isPossible(getX()+i, getY(), blanche, noir)) {
	    		 arraylist.add(getX()+i);   
				 arraylist.add(getY()); 
		     }
	    	 if(possiblePiece.isPossible(getX(), getY()-i, blanche, noir)) {
	    		 arraylist.add(getX());   
				 arraylist.add(getY()-i); 
		     }
	    	 if(possiblePiece.isPossible(getX(), getY()+i, blanche, noir)) {
	    		 arraylist.add(getX());   
				 arraylist.add(getY()+i); 
		     }
	    	 if(possiblePiece.isPossible(getX()-i, getY(), blanche, noir)) {
	    		 arraylist.add(getX()-i);   
				 arraylist.add(getY()); 
		     }
	 
	    }
	    
		 return arraylist; 
	}

}
