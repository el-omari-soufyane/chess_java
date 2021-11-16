package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.ChessController;
import controller.ChessEvent;
import controller.ChessEventListener;
import model.Bishop;
import model.King;
import model.Knight;
import model.Pawn;
import model.Piece;
import model.Queen;
import model.Rook;

public class Table extends JPanel implements ChessEventListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7642237496065653046L;
	private final static int LIGNES = 7;
	private final static int COLONNES = 8;
	private final static int WIDTH = 600;
	private final static int HEIGHT = 600;

	private JButton carres[][] = new JButton[LIGNES][COLONNES];
	private Vector<Piece> piecesBlanche = new Vector<Piece>();
	private Vector<Piece> piecesNoir = new Vector<Piece>();

	private ChessController chessController = new ChessController(piecesBlanche, piecesNoir);

	public Table() {
		// TODO Auto-generated constructor stub
		setLayout(new GridLayout(LIGNES, COLONNES));
		initTable();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
	}

	private void initTable() {

		piecesBlanche.add(new Rook(0, LIGNES - 1, true, "rook_white.png"));
		piecesBlanche.add(new Knight(1, LIGNES - 1, true, "knight_white.png"));
		piecesBlanche.add(new Bishop(2, LIGNES - 1, true, "bishop_white.png"));
		piecesBlanche.add(new King(3, LIGNES - 1, true, "king_white.png"));
		piecesBlanche.add(new Queen(4, LIGNES - 1, true, "queen_white.png"));
		piecesBlanche.add(new Bishop(5, LIGNES - 1, true, "bishop_white.png"));
		piecesBlanche.add(new Knight(6, LIGNES - 1, true, "knight_white.png"));
		piecesBlanche.add(new Rook(7, LIGNES - 1, true, "rook_white.png"));

		for (int i = 0; i < COLONNES; i++) {
			piecesBlanche.add(new Pawn(i, LIGNES - 2, true, "pawn_white.png"));
		}

		piecesNoir.add(new Rook(0, 0, false, "rook_black.png"));
		piecesNoir.add(new Knight(1, 0, false, "knight_black.png"));
		piecesNoir.add(new Bishop(2, 0, false, "bishop_black.png"));
		piecesNoir.add(new King(3, 0, false, "king_black.png"));
		piecesNoir.add(new Piece(4, 0, false, "queen_black.png"));
		piecesNoir.add(new Queen(5, 0, false, "bishop_black.png"));
		piecesNoir.add(new Knight(6, 0, false, "knight_black.png"));
		piecesNoir.add(new Rook(7, 0, false, "rook_black.png"));

		for (int i = 0; i < COLONNES; i++) {
			piecesNoir.add(new Piece(i, 1, false, "pawn_black.png"));
		}

		for (int i = 0; i < LIGNES; i++) {
			for (int j = 0; j < COLONNES; j++) {
				carres[i][j] = new JButton();
				if ((i + j) % 2 != 0) {
					carres[i][j].setBackground(new Color(0x769656));
				} else {
					carres[i][j].setBackground(new Color(0xeeeed2));
				}
				add(carres[i][j]);
				carres[i][j].addActionListener(chessController);
			}
		}

		for (int i = 0; i < piecesBlanche.size(); i++) {
			int col = piecesBlanche.get(i).getX();
			int row = piecesBlanche.get(i).getY();
			Image icon = new ImageIcon("images/" + piecesBlanche.get(i).getIcon()).getImage().getScaledInstance(60, 60,
					Image.SCALE_DEFAULT);
			ImageIcon piece = new ImageIcon(icon);
			carres[row][col].setIcon(piece);
			piecesBlanche.get(i).addChessEventListener(this);
		}

		for (int i = 0; i < piecesNoir.size(); i++) {
			int col = piecesNoir.get(i).getX();
			int row = piecesNoir.get(i).getY();
			Image icon = new ImageIcon("images/" + piecesNoir.get(i).getIcon()).getImage().getScaledInstance(60, 60,
					Image.SCALE_DEFAULT);
			ImageIcon piece = new ImageIcon(icon);
			carres[row][col].setIcon(piece);
			piecesNoir.get(i).addChessEventListener(this);
		}
	}

	@Override
	public void actionADeclancher(ChessEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getSource() instanceof Piece) {
			
			Vector<Object> donne = (Vector<Object>) evt.getDonnee();
			
			int oldX = Integer.parseInt((String) donne.get(0));
			int oldY = Integer.parseInt((String) donne.get(1));
			Piece newPiece = (Piece) donne.get(2);
			
			Image icon;
			
			if(newPiece.isBlanche()) {
				int index = piecesBlanche.indexOf(newPiece);				
				icon = new ImageIcon("images/" + piecesBlanche.get(index).getIcon()).getImage().getScaledInstance(60, 60,
						Image.SCALE_DEFAULT);
			} else {
				int index = piecesNoir.indexOf(newPiece);				
				icon = new ImageIcon("images/" + piecesNoir.get(index).getIcon()).getImage().getScaledInstance(60, 60,
						Image.SCALE_DEFAULT);
			}
			
			ImageIcon deplaceIcon = new ImageIcon(icon);						
			carres[oldY][oldX].setIcon(null);
			carres[newPiece.getY()][newPiece.getX()].setIcon(deplaceIcon);
			
			if((oldY + oldX) % 2 != 0) {
				carres[oldY][oldX].setBackground(new Color(0x769656));
			} else {
				carres[oldY][oldX].setBackground(new Color(0xeeeed2));
			}
		}
	}
}
