package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ChessGUI extends JFrame {
	
	private JToolBar toolbar = new JToolBar();
	private JPanel headerPanel = new JPanel();
	private final JLabel headerMessage = new JLabel("Bienvenue sur i-Chess !");
	
	private Button start = new Button("Nouvelle partie", "icons/add.png", new Color(0x26b1aa));
	private Button upload = new Button("Continuer une partie", "/icons/upload-file.png", new Color(0xd67c54));
	private Button quit = new Button("Quitter", "icons/close.png", new Color(0xff605c));
	
	private Table tableChess = new Table();

	private JPanel rightPanel = new JPanel();
	private JPanel timerPanel = new JPanel();
	private JPanel timerBlanchePanel = new JPanel();
	private JPanel timerNoirPanel = new JPanel();
	private JLabel timerBlanche = new JLabel("00:00");
	private JLabel timerNoir = new JLabel("00:00");

	private Button saveJeu = new Button("Enregistrer", "icons/save.png", new Color(0xff605c));

	public ChessGUI() {
		// TODO Auto-generated constructor stub
		setLayout(new BorderLayout(15, 25));
		
		toolbar.setLayout(new BorderLayout());
		toolbar.setFloatable(false);
		toolbar.setMargin(new Insets(5, 10, 5, 5));
		
		headerMessage.setForeground(new Color(50,111,138));
		headerMessage.setFont(new Font("Evil Empire", Font.PLAIN, 24));
        
        start.setMargin(new Insets(10, 10, 10, 10));
        start.setFocusPainted(false);
        start.setFont(new Font("Glue Gun", Font.PLAIN, 16));
        
        upload.setMargin(new Insets(10, 10, 10, 10));
        upload.setFocusPainted(false);
        upload.setFont(new Font("Glue Gun", Font.PLAIN, 16));
        
        quit.setMargin(new Insets(10, 10, 10, 10));
        quit.setFocusPainted(false);
        quit.setFont(new Font("Glue Gun", Font.PLAIN, 16));
        
        headerPanel.setLayout(new FlowLayout());
        headerPanel.add(start); headerPanel.add(upload); headerPanel.add(quit);
        
        toolbar.add(headerMessage, BorderLayout.WEST);
        toolbar.add(headerPanel, BorderLayout.EAST);

		timerBlanche.setFont(timerBlanche.getFont().deriveFont(32f));
		timerNoir.setFont(timerNoir.getFont().deriveFont(32f));
		timerNoirPanel.add(timerNoir);
		timerNoirPanel.setBorder(BorderFactory.createTitledBorder("Noir"));
		timerBlanchePanel.add(timerBlanche);
		timerBlanchePanel.setBorder(BorderFactory.createTitledBorder("Blanc"));
		timerBlanchePanel.setPreferredSize(new Dimension(200, 70));
		timerNoirPanel.setPreferredSize(new Dimension(200, 70));

		timerPanel.setLayout(new FlowLayout());
		timerPanel.add(timerNoirPanel);
		timerPanel.add(timerBlanchePanel);
		timerPanel.setPreferredSize(new Dimension(200, 200));

		rightPanel.setLayout(new BorderLayout());
		rightPanel.add(timerPanel, BorderLayout.NORTH);
		rightPanel.add(saveJeu, BorderLayout.SOUTH);

		tableChess.setSize(tableChess.getPreferredSize());

		add(toolbar, BorderLayout.PAGE_START);
		add(rightPanel, BorderLayout.EAST);
		add(tableChess, BorderLayout.CENTER);

		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(900, 800);
	}
}
