package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import controller.ChessController;
import model.ListPieces;
import workers.InitBlackWorker;
import workers.InitWhiteWorker;

public class ChessGUI extends JFrame {
	
	private JToolBar toolbar = new JToolBar();
	private JPanel headerPanel = new JPanel();
	private final JLabel headerMessage = new JLabel("Bienvenue sur i-Chess !");
	
	private Table tableChess;

	private JPanel rightPanel = new JPanel();
	private JPanel timerPanel = new JPanel();
	
	private TimerPanel timerBlanche = new TimerPanel("Blanc");
	private TimerPanel timerNoir = new TimerPanel("Noir");

	private Button start = new Button("Jouer", "icons/add.png", new Color(0x26b1aa));
	private Button saveJeu = new Button("Enregistrer", "icons/save.png", new Color(0xd47b53));
	private Button quit = new Button("Quitter", "icons/close.png", new Color(0xff605c));
	
	private ListPieces piecesBlanche = new ListPieces();
	private ListPieces piecesNoir = new ListPieces();

	private ChessController chessController = new ChessController(piecesBlanche, piecesNoir);

	public ChessGUI() {
		// TODO Auto-generated constructor stub
		TimerThread timerB = new TimerThread(timerBlanche, chessController);
		TimerThread timerN = new TimerThread(timerNoir, chessController);
		tableChess = new Table(chessController, piecesBlanche, piecesNoir, timerB, timerN);
		
		InitWhiteWorker blancheWorker = new InitWhiteWorker(chessController);
		InitBlackWorker noirWorker = new InitBlackWorker(chessController);
		blancheWorker.execute();
		noirWorker.execute();
						
		setLayout(new BorderLayout(15, 10));
		
		toolbar.setLayout(new BorderLayout());
		toolbar.setFloatable(false);
		toolbar.setMargin(new Insets(5, 10, 5, 5));
		start.setFont(new Font("Evil Empire", Font.PLAIN, 20));
		saveJeu.setFont(new Font("Evil Empire", Font.PLAIN, 20));
		quit.setFont(new Font("Evil Empire", Font.PLAIN, 20));
		
		headerMessage.setForeground(new Color(50,111,138));
		headerMessage.setFont(new Font("Evil Empire", Font.PLAIN, 24));
        
        headerPanel.setLayout(new FlowLayout());
        headerPanel.add(start);
        headerPanel.add(saveJeu);
        headerPanel.add(quit);
        
        start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				tableChess.initTable();
				timerB.execute();
			}
		});
        
        toolbar.add(headerMessage, BorderLayout.WEST);
        toolbar.add(headerPanel, BorderLayout.EAST);

		timerPanel.setLayout(new FlowLayout());
		timerPanel.add(timerBlanche);
		timerPanel.add(timerNoir);
		timerPanel.setPreferredSize(new Dimension(200, 200));

		rightPanel.setLayout(new BorderLayout());
		rightPanel.add(timerPanel, BorderLayout.NORTH);

		tableChess.setSize(tableChess.getPreferredSize());

		add(toolbar, BorderLayout.PAGE_START);
		add(rightPanel, BorderLayout.EAST);
		add(tableChess, BorderLayout.CENTER);
		
		
		pack();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(900, 800);
		setResizable(false);
		setLocationRelativeTo(null);
	}
}
