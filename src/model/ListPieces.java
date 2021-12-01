package model;

import java.io.Serializable;
import java.util.Vector;

public class ListPieces implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Vector<Piece> pieces = new Vector<Piece>();
	private int size = 0;
	
	public int size() {
		return this.size;
	}
	public void addPiece(Piece piece) {
		pieces.add(piece);
		size = pieces.size();
	}
	
	public void removePiece(Piece piece) {
		pieces.removeElement(piece);
		size = pieces.size();
	}
	
	public void removeAllPiece() {
		pieces.removeAll(pieces);
	}
	
	public Vector<Piece> getPieces() {
		return this.pieces;
	}
	
	public Piece getPiece(int index) {
		return this.pieces.get(index);
	}
	
	public Piece getPieceByXY(int x, int y) {
		return pieces.stream().filter(p -> p.getX() == x && p.getY() == y).findFirst().orElse(null);
	}
	
	public void setPieceType(Piece p, String type) {
		if(type == "bishop") {
			int index = indexOfPiece(p);
			pieces.setElementAt(new Bishop(p.getX(), p.getY(), false, "bishop_white.png"), index);
		}
	}
	
	public int indexOfPiece(Piece piece) {
		return pieces.indexOf(piece);
	}
	@Override
	public String toString() {
		for(Piece p : pieces) {
			System.out.println(p);
		}
		return "Listes";
	}
	
}
