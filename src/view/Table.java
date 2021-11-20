package view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import controller.ChessController;
import controller.ChessEvent;
import controller.ChessEventListener;
import model.Bishop;
import model.King;
import model.Knight;
import model.ListPieces;
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
	// private Vector<Piece> piecesBlanche = new Vector<Piece>();
	// private Vector<Piece> piecesNoir = new Vector<Piece>();

	private ListPieces piecesBlanche = new ListPieces();
	private ListPieces piecesNoir = new ListPieces();

	private ChessController chessController = new ChessController(piecesBlanche, piecesNoir);

	private TimerThread timerBlanche;
	private TimerThread timerNoir;

	public Table(TimerThread timerBlanche, TimerThread timerNoir) {
		this.timerBlanche = timerBlanche;
		this.timerNoir = timerNoir;
		// TODO Auto-generated constructor stub
		setLayout(new GridLayout(LIGNES, COLONNES));
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
				carres[i][j].setFocusPainted(false);
			}
		}
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setMaximumSize(new Dimension(WIDTH, HEIGHT));
	}

	@Override
	public void noirListener(ChessEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getSource() instanceof Piece) {
			Vector<Object> donne = (Vector<Object>) evt.getDonnee();
			int oldX = Integer.parseInt((String) donne.get(0));
			int oldY = Integer.parseInt((String) donne.get(1));
			Piece newPiece = (Piece) donne.get(2);

			Image icon;
			int index = piecesNoir.indexOfPiece(newPiece);
			icon = new ImageIcon("images/" + piecesNoir.getPiece(index).getIcon()).getImage().getScaledInstance(60, 60,
					Image.SCALE_SMOOTH);

			ImageIcon deplaceIcon = new ImageIcon(icon);
			carres[oldY][oldX].setIcon(null);
			carres[newPiece.getY()][newPiece.getX()].setIcon(deplaceIcon);

			if (oldX != newPiece.getX() || oldY != newPiece.getY()) {
				timerNoir.cancel(true);
				timerBlanche = new TimerThread(timerBlanche.getTimerPanel());
				timerBlanche.execute();
			}
			System.out.println("Tableau Noir : " + piecesNoir);
		}
	}

	@Override
	public void blancheListener(ChessEvent evt) {
		// TODO Auto-generated method stub
		if (evt.getSource() instanceof Piece) {
			Vector<Object> donne = (Vector<Object>) evt.getDonnee();
			int oldX = Integer.parseInt((String) donne.get(0));
			int oldY = Integer.parseInt((String) donne.get(1));
			Piece newPiece = (Piece) donne.get(2);

			if (oldX != newPiece.getX() || oldY != newPiece.getY()) {
				BlackWorker blackWorker = new BlackWorker(chessController, timerBlanche);
				WhiteWorker whiteWorker = new WhiteWorker(oldX, oldY, newPiece, carres, piecesBlanche, blackWorker);
				whiteWorker.execute();
				timerBlanche.cancel(true);
				timerNoir = new TimerThread(timerNoir.getTimerPanel());
				timerNoir.execute();
			}
			if ((oldY + oldX) % 2 != 0) {
				carres[oldY][oldX].setBackground(new Color(0x769656));
			} else {
				carres[oldY][oldX].setBackground(new Color(0xeeeed2));
			}
			System.out.println("Tableau : " + piecesBlanche);
		}
	}

	public void initTable() {
		initBlanche();
		initNoir();
		initPieces();
	}

	private void initBlanche() {
		piecesBlanche.addPiece(new Rook(0, LIGNES - 1, true, "rook_white.png"));
		piecesBlanche.addPiece(new Knight(1, LIGNES - 1, true, "knight_white.png"));
		piecesBlanche.addPiece(new Bishop(2, LIGNES - 1, true, "bishop_white.png"));
		piecesBlanche.addPiece(new King(3, LIGNES - 1, true, "king_white.png"));
		piecesBlanche.addPiece(new Queen(4, LIGNES - 1, true, "queen_white.png"));
		piecesBlanche.addPiece(new Bishop(5, LIGNES - 1, true, "bishop_white.png"));
		piecesBlanche.addPiece(new Knight(6, LIGNES - 1, true, "knight_white.png"));
		piecesBlanche.addPiece(new Rook(7, LIGNES - 1, true, "rook_white.png"));

		for (int i = 0; i < COLONNES; i++) {
			piecesBlanche.addPiece(new Pawn(i, LIGNES - 2, true, "pawn_white.png"));
		}
	}

	private void initNoir() {
		piecesNoir.addPiece(new Rook(0, 0, false, "rook_black.png"));
		piecesNoir.addPiece(new Knight(1, 0, false, "knight_black.png"));
		piecesNoir.addPiece(new Bishop(2, 0, false, "bishop_black.png"));
		piecesNoir.addPiece(new King(3, 0, false, "king_black.png"));
		piecesNoir.addPiece(new Piece(4, 0, false, "queen_black.png"));
		piecesNoir.addPiece(new Queen(5, 0, false, "bishop_black.png"));
		piecesNoir.addPiece(new Knight(6, 0, false, "knight_black.png"));
		piecesNoir.addPiece(new Rook(7, 0, false, "rook_black.png"));

		for (int i = 0; i < COLONNES; i++) {
			piecesNoir.addPiece(new Pawn(i, 1, false, "pawn_black.png"));
		}
	}

	private void initPieces() {
		for (int i = 0; i < piecesBlanche.size(); i++) {
			int col = piecesBlanche.getPiece(i).getX();
			int row = piecesBlanche.getPiece(i).getY();
			Image icon = new ImageIcon("images/" + piecesBlanche.getPiece(i).getIcon()).getImage().getScaledInstance(60,
					60, Image.SCALE_SMOOTH);
			ImageIcon piece = new ImageIcon(icon);
			carres[row][col].setIcon(piece);
			piecesBlanche.getPiece(i).addChessEventListener(this);
		}

		for (int i = 0; i < piecesNoir.size(); i++) {
			int col = piecesNoir.getPiece(i).getX();
			int row = piecesNoir.getPiece(i).getY();
			Image icon = new ImageIcon("images/" + piecesNoir.getPiece(i).getIcon()).getImage().getScaledInstance(60,
					60, Image.SCALE_SMOOTH);
			ImageIcon piece = new ImageIcon(icon);
			carres[row][col].setIcon(piece);
			piecesNoir.getPiece(i).addChessEventListener(this);
		}
	}
}
