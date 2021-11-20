package view;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameOver extends JPanel {
	
	private JLabel message = new JLabel();
	public GameOver() {
		// TODO Auto-generated constructor stub
		message.setText("GAME OVER");
		message.setFont(new Font("Evil Empire", Font.BOLD, 30));
		add(message);
		setPreferredSize(new Dimension(400, 400));
	}
}
