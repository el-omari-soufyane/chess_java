package model;

import java.util.Vector;

public class Knight extends Piece {

	public Knight(int x, int y, boolean blanche, String icon) {
		super(x, y, blanche, icon);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean isPossible(int x_dest, int y_dest, Vector<Piece> blanche, Vector<Piece> noir) {
		// TODO Auto-generated method stub
		return true;
	}

}
