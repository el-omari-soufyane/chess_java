package view;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameOver extends JOptionPane {
	
	private JLabel message = new JLabel();
	private JPanel panel = new JPanel();
	public GameOver() {
		// TODO Auto-generated constructor stub
		message.setText("GAME OVER");
		message.setFont(new Font("Evil Empire", Font.BOLD, 30));
		panel.add(message);
		JOptionPane.showMessageDialog(panel, message);
		setPreferredSize(new Dimension(400, 400));
	}
}
