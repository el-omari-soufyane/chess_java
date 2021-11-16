package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;

import javax.swing.JButton;

import model.Piece;

public class ChessController implements ActionListener {

	private Vector<Piece> piecesBlanche;
	private Vector<Piece> piecesNoir;
	private Piece activePiece = null;
	private boolean myTurn = true;

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
				for(Piece p : piecesNoir) {
					if(p.getX() == clickedCol && p.getY() == clickedLigne) {
						piecesNoir.removeElement(p);
						break;
					}
				}
			}
			else piecesBlanche.get(position).setXY(activePiece.getX(), activePiece.getY(), activePiece);
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
	
	public void executeOpponent() {
		System.out.println("Execute Opponent");
		Random rand = new Random();
		int randX = -1;
		int randY = -1;
		int randX_dest = -1;
		int randY_dest = -1;
		
		boolean legalMove = false;
		
		while(!legalMove) {
			randX = rand.nextInt(8);
			randY = rand.nextInt(7);
			if(!clickOnNoir(randX, randY)) {
				System.out.println("Random Piece : X = " + randX + " | Y = " + randY);
				continue;
			}			
			Piece active = null;
			for(Piece p : piecesNoir) {
				if(p.getX() == randX && p.getY() == randY) active = p;
			}
			int position = piecesNoir.indexOf(active);
			randX_dest = rand.nextInt(8);
			randY_dest = rand.nextInt(7);
			if(!active.isPossible(randX_dest, randY_dest, piecesBlanche, piecesNoir)) {
				System.out.println(active + "Random Destination : X = " + randX_dest + " | Y = " + randY_dest);
				continue;
			}
			legalMove = true;			
			piecesNoir.get(position).setXY(randX_dest, randY_dest, active);	
		}
	}
	
	private boolean clickOnWhite(int x, int y) {
		for(Piece p : piecesBlanche) {
			if(p.isBlanche() && p.getX() == x && p.getY() == y) return true;
		}
		return false;
	}
	
	private boolean clickOnNoir(int x, int y) {
		for(Piece p : piecesNoir) {
			if(!p.isBlanche() && p.getX() == x && p.getY() == y) return true;
		}
		return false;
	}

}
