package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.ChessController;
import model.Piece;

public class KingDied extends JOptionPane {

	private JPanel panel = new JPanel();
	private JLabel message = new JLabel("Le roi est mort !");
	public KingDied(Piece piece, ChessController chessController) {
		// TODO Auto-generated constructors stub
		panel.setLayout(new GridLayout(2, 1));

		JButton kingImg = new JButton();
		if(piece.isBlanche()) {			
			kingImg.setIcon(new ImageIcon(
					new ImageIcon("images/king_white.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
		} else {
			kingImg.setIcon(new ImageIcon(
					new ImageIcon("images/king_black.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH)));
		}
		kingImg.setBackground(new Color(0xeeeed2));
		
		message.setFont(new Font("Evil Empire", Font.BOLD, 30));
		panel.add(message);
		
		panel.add(kingImg);
		panel.setPreferredSize(new Dimension(300, 100));

		JOptionPane.showOptionDialog(null, panel, "Choose", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE,
				null, new Object[] {}, kingImg);
		
	}
}
