package model;

public class Pawn extends Piece {

	public Pawn(int x, int y, boolean blanche, String icon) {
		super(x, y, blanche, icon);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isPossible(int x_dest, int y_dest, ListPieces blanche, ListPieces noir) {
		// TODO Auto-generated method stub
		System.out.println("Dest => x : " + x_dest + " y : " + y_dest + " => OldPiece : " + this);
		Piece possiblePiece = blanche.getPieceByXY(x_dest, y_dest);
		possiblePiece = noir.getPieceByXY(x_dest, y_dest);
		
		if (possiblePiece != null) {
			if (possiblePiece.isBlanche() && this.isBlanche())
				return false;
			if (!possiblePiece.isBlanche() && !this.isBlanche())
				return false;

			if (this.isBlanche() && !possiblePiece.isBlanche() && y_dest == getY() - 1 && x_dest == getX())
				return false;
			if (this.isBlanche() && !possiblePiece.isBlanche() && y_dest == getY() - 1
					&& (x_dest == getX() - 1 || x_dest == getX() + 1))
				return true;

			if (!this.isBlanche() && possiblePiece.isBlanche() && y_dest == getY() + 1 && x_dest == getX())
				return false;
			if (!this.isBlanche() && possiblePiece.isBlanche() && y_dest == getY() + 1
					&& (x_dest == getX() - 1 || x_dest == getX() + 1))
				return true;

		}

		if (this.isBlanche()) {
			if (x_dest != getX() || y_dest > getY() || y_dest < getY() - 1)
				return false;
		} else {
			if (x_dest != getX() || y_dest < getY() || y_dest > getY() + 1)
				return false;
		}

		return true;
	}

}
