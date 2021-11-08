package Board;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import java.net.URL;
import javax.imageio.ImageIO;

public class ChessGUI {

    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JButton[][] chessBoardSquares = new JButton[8][8];
    private Image[][] chessPieceImages = new Image[2][6];
    private JPanel chessBoard;
    private static final String COLS = "ABCDEFGH";
    private final JLabel message = new JLabel(
            " Bienvenue sur JavaChess!");
    public static final int QUEEN = 0, KING = 1,
            ROOK = 2, KNIGHT = 3, BISHOP = 4, PAWN = 5;
    public static final int[] STARTING_ROW = {
        ROOK, KNIGHT, BISHOP, KING, QUEEN, BISHOP, KNIGHT, ROOK
    };
    public static final int BLACK = 0, WHITE = 1;

    JButton b;

    ChessGUI() {
        initializeGui();
    }

    public final void initializeGui() {    	
        createImages();
        JToolBar tools = new JToolBar();
        tools.setFloatable(false);
        tools.setMargin(new Insets(5, 10, 5, 5));
        gui.add(tools, BorderLayout.PAGE_START);
        Action newGameAction = new AbstractAction("Nouvelle partie") {
            @Override
            public void actionPerformed(ActionEvent e) {
                setupNewGame();      
            }
        };
        JButton a = new JButton(newGameAction);
        a.setMargin(new Insets(10, 10, 10, 10));
        a.setFocusPainted(false);
        tools.add(a);
        JButton q = new JButton("Quitter le jeu");
        q.setMargin(new Insets(10, 10, 10, 10));
        q.setFocusPainted(false);
        q.addActionListener(e ->
        {
        	System.exit(0);
        });
        tools.add(q);
        JButton v = new JButton("Sauvgarder la partie");
        v.setMargin(new Insets(10, 10, 10, 10));
        v.setFocusPainted(false);
        v.setEnabled(false);
        tools.add(v);
        
        //message.setBorder(new CompoundBorder(message.getBorder(), new EmptyBorder(0, 180, 0, 0)));
        message.setForeground(new Color(50,111,138));
        message.setFont(new Font("Monospaced", Font.BOLD, 18));
        tools.add(message);
        
        
        chessBoard = new JPanel(new GridLayout(8, 9)) {

			private static final long serialVersionUID = 1L;

			@Override
            public final Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                Dimension prefSize = null;
                Component c = getParent();
                if (c == null) {
                    prefSize = new Dimension(
                            (int)d.getWidth(),(int)d.getHeight());
                } else if (c!=null &&
                        c.getWidth()>d.getWidth() &&
                        c.getHeight()>d.getHeight()) {
                    prefSize = c.getSize();
                } else {
                    prefSize = d;
                }
                int w = (int) prefSize.getWidth();
                int h = (int) prefSize.getHeight();
                int s = (w>h ? h : w);
                return new Dimension(s,s);
                
            }
        };
        Color color = new Color(230,230,230);
        chessBoard.setBackground(color);
        JPanel boardConstrain = new JPanel(new GridBagLayout());
        boardConstrain.setBackground(color);
        boardConstrain.add(chessBoard);
        gui.add(boardConstrain);

        for (int ii = 0; ii < chessBoardSquares.length; ii++) {
            for (int jj = 0; jj < chessBoardSquares[ii].length; jj++) {
                b = new JButton();
                b.setFocusPainted(false);
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if ((jj % 2 == 1 && ii % 2 == 1) || (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(new Color(230,230,230));
                }
                chessBoardSquares[jj][ii] = b;
            }
        }
        
        chessBoard.add(new JLabel(""));
        for (int ii = 0; ii < 8; ii++) {
            chessBoard.add(
                    new JLabel(COLS.substring(ii, ii +1),
                    SwingConstants.CENTER));
        }
        for (int ii = 0; ii < 7; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                switch (jj) {
                    case 0:
                        chessBoard.add(new JLabel("" + (8-(ii + 1)),
                                SwingConstants.CENTER));
                    default:
                        chessBoard.add(chessBoardSquares[jj][ii]);
                }

            }
        }
        
    }

    public final JComponent getGui() {
        return gui;
    }

    
    private final void createImages() {
        try {
            
        	URL url = new URL("http://i.stack.imgur.com/memI0.png");
            BufferedImage bi = ImageIO.read(url);
            for (int ii = 0; ii < 2; ii++) for (int jj = 0; jj < 6; jj++) chessPieceImages[ii][jj] = bi.getSubimage(jj * 64, ii * 64, 64, 64);
        
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    private final void setupNewGame() {
    	
    	message.setText(" Joueur 1 c'est votre tour!");

        for (int ii = 0; ii < STARTING_ROW.length; ii++) chessBoardSquares[ii][0].setIcon(new ImageIcon(chessPieceImages[BLACK][STARTING_ROW[ii]]));
      
        for (int ii = 0; ii < STARTING_ROW.length; ii++) chessBoardSquares[ii][1].setIcon(new ImageIcon(chessPieceImages[BLACK][PAWN]));
        
        for (int ii = 0; ii < STARTING_ROW.length; ii++)chessBoardSquares[ii][5].setIcon(new ImageIcon(chessPieceImages[WHITE][PAWN]));

        
        for (int ii = 0; ii < STARTING_ROW.length; ii++) chessBoardSquares[ii][6].setIcon(new ImageIcon(chessPieceImages[WHITE][STARTING_ROW[ii]]));

    }
    
    public static void main(String[] args) {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                ChessGUI cg = new ChessGUI();
                JFrame f = new JFrame("Jeu d'echecs");
                f.setUndecorated(true);
                f.add(cg.getGui());
                f.setResizable(false);
                f.pack();
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Already there
                f.setExtendedState(JFrame.MAXIMIZED_BOTH);
    
                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}