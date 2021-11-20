package model;

import java.util.Vector;

public class ListPieces {

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
	
	public Vector<Piece> getPieces() {
		return this.pieces;
	}
	
	public Piece getPiece(int index) {
		return this.pieces.get(index);
	}
	
	public Piece getPieceByXY(int x, int y) {
		return pieces.stream().filter(p -> p.getX() == x && p.getY() == y).findFirst().orElse(null);
	}
	
	public int indexOfPiece(Piece piece) {
		return pieces.indexOf(piece);
	}
}
