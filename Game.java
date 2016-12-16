import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.io.*;

class Game extends JPanel implements ActionListener, MouseListener {
	private JFrame frame;
	private JPanel buttonPanel, gamePanel;
	private Board board;
	private Boolean gameOver;
	private Player currentPlayer;
	private int selectedR, selectedC;
	private MoveSequence mSeq;
	private JLabel message;


	public static void main(String [] args) {
		Game game = new Game();
	}

	public Game() {
		frame = new JFrame();
		buttonPanel = new JPanel();
		gamePanel = new JPanel();
		gamePanel.setSize(new Dimension(200,200));
		gamePanel.setVisible(true);
		frame.setLayout(new BorderLayout());
		frame.add("South", buttonPanel);
		frame.add("Center", gamePanel);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(1001,1001));
		frame.setVisible(true);
		frame.add(this);

		frame.getContentPane().setBackground(new Color(255, 255, 232));
		buttonPanel.setBackground(new Color(222, 250, 232));
		gamePanel.setBackground(new Color(23, 250, 232));

		board = new Board();

		Button button = new Button("NEW GAME");

		button.setVisible(true);
		button.setBackground(Color.black);
		button.addActionListener(this);
		buttonPanel.add(button);

		gameOver = true;

		addMouseListener(this);
		message = new JLabel("HELLO", JLabel.CENTER);
		buttonPanel.add(message);
		startGame();

	}

	public void startGame() {
		if (gameOver == false) {
			message.setText("NEW GAME");
			return;
		}
		board.setup();
		currentPlayer = new Player("RED");
		mSeq = board.getValidMoves(currentPlayer);
		selectedR = -1;
		message.setText("RED: Make your move.");
		gameOver = false;

		repaint();
		return;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		System.out.println("DKFHSFJKHDSF");
		g.setColor(Color.black);
      	g.drawRect(0,0, frame.getContentPane().getSize().width-1, frame.getContentPane().getSize().height-1);
      	g.drawRect(1,1, frame.getSize().width-3, frame.getSize().height-3);


      	for (int row = 0; row < Board.NUM_ROW_COL; row++) {
         for (int col = 0; col < Board.NUM_ROW_COL; col++) {
             if ( row % 2 == col % 2 )
                g.setColor(Color.lightGray);
             else
                g.setColor(Color.gray);
             g.fillRect(2 + col*20, 2 + row*20, 20, 20);

             // Piece piece = board.getPiece(row, col);
             switch (board.getPiece(row,col).getColor()) {
                case "RED":
                   g.setColor(Color.red);
                   g.fillOval(4 + col*20, 4 + row*20, 16, 16);
                   break;
                case "BLACK":
                   g.setColor(Color.black);
                   g.fillOval(4 + col*20, 4 + row*20, 16, 16);
                   break;
                case "RED_KING":
                   g.setColor(Color.red);
                   g.fillOval(4 + col*20, 4 + row*20, 16, 16);
                   g.setColor(Color.white);
                   g.drawString("K", 7 + col*20, 16 + row*20);
                   break;
                case "BLACK_KING":
                   g.setColor(Color.black);
                   g.fillOval(4 + col*20, 4 + row*20, 16, 16);
                   g.setColor(Color.white);
                   g.drawString("K", 7 + col*20, 16 + row*20);
                   break;
             }
         }
      }


	}

	@Override
	public void actionPerformed(ActionEvent e ) {
		System.out.println("action prr");
		gamePanel.repaint();
		// validate();
		// repaint();

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}
	public void mouseReleased(MouseEvent evt) { }
   	public void mouseClicked(MouseEvent evt) { }
   	public void mouseEntered(MouseEvent evt) { }
 	public void mouseExited(MouseEvent evt) { }


}