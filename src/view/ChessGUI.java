package view;

import java.awt.FlowLayout;

import javax.swing.JFrame;

public class ChessGUI extends JFrame {
	
	private Table tableChess = new Table();
	
	public ChessGUI() {
		// TODO Auto-generated constructor stub
		this.setLayout(new FlowLayout());
		this.add(tableChess);
		tableChess.setSize(tableChess.getPreferredSize());
		this.pack();
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(800, 800);
	}
}
