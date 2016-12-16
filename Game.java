/* File: Game.java
   Author: Mathew Puryear & Dulce Palacios
   Assignment: Final Project CS 360
   Description: This class is responsible for managing the rules
   				of the game and the rest of the classes.
*/
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.io.*;

class Game extends JPanel implements ActionListener, MouseListener {
	static final public int HEIGHT_WIDTH = 640;
	private JFrame frame;
	private JPanel buttonPanel, gamePanel;
	private Board board;
	private Boolean gameOver;
	private Player currentPlayer, redPlayer, blackPlayer;
	private int selectedR, selectedC;
	private MoveSequence mSeq;
	private JLabel message;


	public static void main(String [] args) {
		Game game = new Game();
	}

	public Game() {
		gameOver = true;
		board = new Board();

		buttonPanel = new JPanel();

		message = new JLabel("HELLO", JLabel.CENTER);
		message.setFont(new Font("TimesRoman", Font.BOLD, 20));
		message.setForeground(Color.white);

		Button b = new Button("NEW GAME");
		b.setVisible(true);
		b.addActionListener(this);
		buttonPanel.add(b);
		buttonPanel.add(message);
		buttonPanel.setBackground(new Color(66, 29, 15));

		gamePanel = new JPanel();
		gamePanel.setBackground(Color.red);

		frame = new JFrame();
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(HEIGHT_WIDTH, HEIGHT_WIDTH + 65));
		frame.setVisible(true);
		frame.setResizable(false);

		frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
		frame.add(this);
		addMouseListener(this);
		startGame();

	}

	public void startGame() {
		if (gameOver == false) {
			message.setText(""); // POP UP 
			int result = JOptionPane.showConfirmDialog(null, "Restart?", "Game Over", JOptionPane.YES_NO_OPTION);
			if (result == JOptionPane.NO_OPTION)
				return;
		}
		board.setup();

		if (JOptionPane.NO_OPTION == JOptionPane.showConfirmDialog(null, "Should the red player be AI?", 
			"Prompt", JOptionPane.YES_NO_OPTION)) {
			redPlayer = new Player("RED");
			redPlayer.setAI(false);
		} else {
			redPlayer = new SimpleAI("RED");
			redPlayer.setAI(true);
		}

		if (JOptionPane.NO_OPTION == JOptionPane.showConfirmDialog(null, "Should the black player be AI?", 
			"Prompt", JOptionPane.YES_NO_OPTION)) {
			blackPlayer = new Player("BLACK");
			blackPlayer.setAI(false);
		} else {
			blackPlayer = new SimpleAI("BLACK");
			blackPlayer.setAI(true);
		}

		currentPlayer = redPlayer;
		mSeq = board.getValidMoves(currentPlayer);
		selectedR = -1;
		message.setText("RED: Make your move.");
		gameOver = false;

		if (currentPlayer instanceof SimpleAI) {
			SimpleAITurn();
		}

		repaint();
		return;
	}

	@Override
	protected void paintComponent(Graphics g) {
		// super.paintComponent(g);
		g.setColor(Color.black);
      	g.drawRect(0,0, frame.getContentPane().getSize().width-1, frame.getContentPane().getSize().height-1);
      	g.drawRect(1,1, frame.getSize().width-3, frame.getSize().height-3);


      	for (int row = 0; row < Board.NUM_ROW_COL; row++) {
        	for (int col = 0; col < Board.NUM_ROW_COL; col++) {
            	if ( row % 2 == col % 2 )
                	g.setColor(Color.lightGray);
             	else
                	g.setColor(Color.darkGray);
             	g.fillRect(2 + col*80, 2 + row*80, 80, 80);

	             // Piece piece = board.getPiece(row, col);
	             switch (board.getPiece(row,col).getColorType()) {
	                case "RED_NORMAL":
	                   g.setColor(Color.red);
	                   g.fillOval(4 + col*80, 4 + row*80, 76, 76);
	                   break;
	                case "BLACK_NORMAL":
	                   g.setColor(Color.black);
	                   g.fillOval(4 + col*80, 4 + row*80, 76, 76);
	                   break;
	                case "RED_KING":
	                   g.setColor(Color.red);
	                   g.fillOval(4 + col*80, 4 + row*80, 76, 76);
	                   g.setColor(Color.white);
	                   Font f = g.getFont();
	                   g.setFont(new Font("TimesRoman", Font.BOLD, 40));
	                   g.drawString("K", 27 + col*80, 55 + row*80);
	                   g.setFont(f);
	                   break;
	                case "BLACK_KING":
	                   g.setColor(Color.black);
	                   g.fillOval(4 + col*80, 4 + row*80, 76, 76);
	                   g.setColor(Color.white);
	                   Font f2 = g.getFont();
	                   g.setFont(new Font("TimesRoman", Font.BOLD, 40));
	                   g.drawString("K", 27 + col*80, 55 + row*80);
	                   g.setFont(f2);
	                   break;
	             }
         	}
      	}
      	if(!gameOver) {
      		Graphics2D g2 = (Graphics2D) g;
            g2.setColor(Color.blue);
            g2.setStroke(new BasicStroke(3));
            // Loop over all valid moves                                                                                 
       	    for(int i = 0; i < mSeq.size(); i++) {
                g2.drawRect(2 + mSeq.get(i).fromC * 80, 2 + mSeq.get(i).fromR*80, 80, 80);
            }

            if(selectedR >= 0) {
                g2.setColor(Color.white);
				g2.drawRect(2+ selectedC * 80, 2 + selectedR * 80, 78, 78);
                g2.drawRect(3+ selectedC * 80, 3 + selectedR * 80, 77, 77);
				g2.setColor(Color.green);
            for(int i = 0; i < mSeq.size(); i++) {
                if(mSeq.get(i).fromC == selectedC && mSeq.get(i).fromR == selectedR)
                    g2.drawRect(2 + mSeq.get(i).toC * 80, 2 + mSeq.get(i).toR * 80, 78, 78);
                }
            }
        }
	}

	@Override
	public void actionPerformed(ActionEvent e ) {
		startGame();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(gameOver)
			message.setText("Game Over.");
		else {
			int col = (e.getX() - 2) / 80;
			int row = (e.getY() - 2) / 80;
			if(col >= 0 && col < Board.NUM_ROW_COL 
				&& row >= 0 && row < Board.NUM_ROW_COL) {
				clickSquare(row,col);
		
			}
		}
	}
	public void mouseReleased(MouseEvent evt) { }
   	public void mouseClicked(MouseEvent evt) { }
   	public void mouseEntered(MouseEvent evt) { }
 	public void mouseExited(MouseEvent evt) { }

 	public void clickSquare(int row, int col) {
 		for (int i =0; i < mSeq.size(); i++) {
 			if (mSeq.get(i).fromR == row && mSeq.get(i).fromC == col) {
 				selectedR = row;
 				selectedC = col;
 				if (currentPlayer.isRed())
 					message.setText("RED's turn.");
				else 
 					message.setText("BLACK: Make your move.");
 				repaint();
 				return;
 			}
 		}

 		if (selectedR < 0 ) {
 			message.setText("Click the piece you want to move.");
 			return;
 		}

 		for (int i = 0; i < mSeq.size(); i++) {
 			if (mSeq.get(i).fromR == selectedR && mSeq.get(i).fromC == selectedC
 				&& mSeq.get(i).toR == row && mSeq.get(i).toC == col) {
 				makeMove(mSeq.get(i));
 			}
 		}
 		message.setText("Click the square you want to move to.");
 	}

 	public void makeMove(Move m) {
 		board.movePiece(m);
 		if(m.isJump()) {
 			mSeq = board.getValidJumps(m.toR, m.toC, currentPlayer);
 			if(mSeq != null) {
 				if(currentPlayer.isRed())
 					message.setText("Red must jump");
 				else 
 					message.setText("Black must jump");
 				selectedR = m.toR;
 				selectedC = m.toC;

 				if (currentPlayer instanceof SimpleAI) {
 					SimpleAITurn();
 				}
 				repaint();
 				return;
 			}
 		}

 		if(currentPlayer.isRed()) {
 			currentPlayer = blackPlayer;
 			mSeq = board.getValidMoves(currentPlayer);
 			if(mSeq == null)
 				gameOver("RED WINS");
 			else if (mSeq.get(0).isJump())
 				message.setText("Black, you must jump");
 			else
 				message.setText("Black, make a move.");
 		}
 		else {
 			currentPlayer = redPlayer;
 			mSeq = board.getValidMoves(currentPlayer);
 			if(mSeq == null)
 				gameOver("BLACK WINS");
 			else if ( mSeq.get(0).isJump())
 				message.setText("Red, make a jump");
 			else 
 				message.setText("Red, make a move");

 		}
 		selectedR = -1;
 		if (currentPlayer instanceof SimpleAI) {
 			SimpleAITurn();
 		}
 		repaint();
 	}

 	public void SimpleAITurn() {
 		Move ai_move = ((SimpleAI) currentPlayer).getNextMove(mSeq);
 		mousePressed(new MouseEvent(this, 0, 0, 0, (ai_move.fromC * 80) + 2, (ai_move.fromR * 80) + 2, 1, false));
 		mousePressed(new MouseEvent(this, 0, 0, 0, (ai_move.toC * 80) + 2, (ai_move.toR * 80) + 2, 1, false));
 	}


 	public void gameOver(String s) {
 		message.setText(s);
 		gameOver = true;
 		int result = JOptionPane.showConfirmDialog(null, "New Game?", "Game Over", JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.NO_OPTION)
			System.exit(0);
		startGame();
 	}
}