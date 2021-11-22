package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;


import javax.swing.JButton;

import model.ListPieces;
import model.Piece;

public class ChessController implements ActionListener {

	private ListPieces piecesBlanche;
	private ListPieces piecesNoir;
	private JButton carres[][];
	private Piece activePiece = null;
	private boolean myTurn = true;

	public ChessController(ListPieces piecesBlanche, ListPieces piecesNoir, JButton carres[][]) {
		// TODO Auto-generated constructor stub
		this.piecesBlanche = piecesBlanche;
		this.piecesNoir = piecesNoir;
		this.carres = carres;
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
			int position = piecesBlanche.indexOfPiece(activePiece);
			if(possiblePiece) {
				piecesBlanche.getPiece(position).setXY(clickedCol, clickedLigne, activePiece);
				Piece p = piecesNoir.getPieceByXY(clickedCol, clickedLigne);
				if(p != null) {
					piecesNoir.removePiece(p);
				}
			}
			else piecesBlanche.getPiece(position).setXY(activePiece.getX(), activePiece.getY(), activePiece);
			myTurn = true;
			activePiece = null;
		} else {
			ArrayList<Integer> Tab;
			click.setBackground(new Color(0xf6f669));
			activePiece = piecesBlanche.getPieceByXY(clickedCol, clickedLigne);
			Tab = activePiece.colorCase(clickedCol, clickedLigne, piecesBlanche, piecesNoir);
			int j = 1;
			for(int i = 0; i < Tab.size(); i = i + 2) {
				   try {
					   carres[Tab.get(j)][Tab.get(i)].setBackground(new Color(0xf6f669));
			       }catch(Exception e1) {
					  
			       }
				   j = j + 2;
			}
			myTurn = false;
		}
	}
	
	public void executeOpponent() {
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
				continue;
			}			
			
			Piece active = piecesNoir.getPieceByXY(randX, randY);
			int position = piecesNoir.indexOfPiece(active);
			
			randX_dest = rand.nextInt(8);
			randY_dest = rand.nextInt(7);
			
			if(!active.isPossible(randX_dest, randY_dest, piecesBlanche, piecesNoir)) {
				continue;
			}
			
			legalMove = true;			
			piecesNoir.getPiece(position).setXY(randX_dest, randY_dest, active);	
		
		}
	}
	
	private boolean clickOnWhite(int x, int y) {
		if(piecesBlanche.getPieceByXY(x, y) != null)
			return true;
		return false;
	}
	
	private boolean clickOnNoir(int x, int y) {
		if(piecesNoir.getPieceByXY(x, y) != null)
			return true;
		return false;
	}

}
