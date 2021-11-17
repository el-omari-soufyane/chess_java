package view;

import javax.swing.JButton;
import javax.swing.SwingWorker;

import model.ListPieces;

public class ApplyMoveWhite extends SwingWorker<Boolean, Void> {
	
	private ListPieces piecesBlanche;
	private JButton[][] pieces;
	private boolean isDone = false;

	public ApplyMoveWhite(ListPieces piecesBlanche, JButton[][] pieces) {
		// TODO Auto-generated constructor stub
		this.piecesBlanche = piecesBlanche;
		this.pieces = pieces;
	}
	
	@Override
	protected Boolean doInBackground() throws Exception {
		// TODO Auto-generated method stub
		for (int i = 0; i < piecesBlanche.size(); i++) {
			int col = piecesBlanche.getPiece(i).getX();
			int row = piecesBlanche.getPiece(i).getY();
			pieces[row][col].setEnabled(false);
		}
		return true;
	}
	
	@Override
	protected void done() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
