package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;

import model.Piece;

public class ChessController implements ActionListener {

	private Vector<Piece> piecesBlanche;
	private Vector<Piece> piecesNoir;
	private Piece activePiece = null;
	private boolean myTurn = true;
	private int turnCount = 0;

	public ChessController(Vector<Piece> piecesBlanche, Vector<Piece> piecesNoir) {
		// TODO Auto-generated constructor stub
		this.piecesBlanche = piecesBlanche;
		this.piecesNoir = piecesNoir;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		JButton click = (JButton) e.getSource();
		int clickedLigne = click.getY() / click.getSize().height;
		int clickedCol = click.getX() / click.getSize().width;
		
		if(activePiece == null && !clickOnWhite(clickedCol, clickedLigne)) {
			return;
		}
		
		if (!myTurn) {
			boolean possiblePiece = activePiece.isPossible(clickedCol, clickedLigne, piecesBlanche, piecesNoir);
			int position = piecesBlanche.indexOf(activePiece);
			System.out.println("Is Possible : " + possiblePiece);
			if(possiblePiece) {
				piecesBlanche.get(position).setXY(clickedCol, clickedLigne, activePiece);				
			}
			piecesBlanche.get(position).setXY(activePiece.getX(), activePiece.getY(), activePiece);
			myTurn = true;
			activePiece = null;
		} else {
			click.setBackground(new Color(0xf6f669));
			activePiece = piecesBlanche.stream()
					.filter(p -> p.getX() == clickedCol && p.getY() == clickedLigne)
					.findFirst().orElse(null);
			myTurn = false;
		}
		System.out.println(activePiece);
	}
	
	private boolean clickOnWhite(int x, int y) {
		for(Piece p : piecesBlanche) {
			if(p.isBlanche() && p.getX() == x && p.getY() == y) return true;
		}
		return false;
	}

}
