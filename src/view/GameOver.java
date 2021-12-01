package view;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.ChessController;
import workers.InitBlackWorker;
import workers.InitWhiteWorker;

public class GameOver extends JOptionPane {
	
	private JLabel message = new JLabel();
	private JPanel panel = new JPanel();
	public GameOver(ChessController chessController, TimerPanel time) {
		// TODO Auto-generated constructor stub
		message.setText("GAME OVER");
		message.setFont(new Font("Evil Empire", Font.BOLD, 30));
		panel.add(message);
		int result = JOptionPane.showConfirmDialog(null, panel);
		if(result == OK_OPTION) {
			chessController.clearPieces();
			InitBlackWorker blackInitializer = new InitBlackWorker(chessController);
			InitWhiteWorker whiteInitializer = new InitWhiteWorker(chessController);
			blackInitializer.execute();
			whiteInitializer.execute();
			time.resetTimer();
		}
		setPreferredSize(new Dimension(400, 400));
	}
}
