/* File: BoardTest.java
   Author: Mathew Puryear & Dulce Palacios
   Assignment: Final Project CS 360
   Description: This is the test class for Board.
*/
   
import java.util.Random;

class BoardTest extends Board{
    static private int failedTests;
    static private final int TEST_NUMBER = 10;

    public static void main(String[] args) {
	BoardTest bt = new BoardTest();
	bt.run();
    }

    static public void run() {
	boolean t = true; int count = 1;
	failedTests = 0;

	Random rand = new Random();
	
	Board b = new Board();
	Piece[][] pieces = b.getBoard();

	// Test all Setup()
	for(int r = 0; r < Board.NUM_ROW_COL; r++){
	    for(int c = 0; c < Board.NUM_ROW_COL; c++) {
		if( r % 2 == c % 2) {
		    if(r < 3)
			t &= test((pieces[r][c].isBlack()
				   && pieces[r][c].getType().equals("NORMAL")),
				  count++, "isBlack, normal r,c:" + r + "," + c);
		    else if( r > 4)
			t &= test(pieces[r][c].isRed() && pieces[r][c].getType().equals("NORMAL"), count++,
				  "isRed, normal r,c:" + r + "," + c);
		    else
			t &= test(pieces[r][c].isEmpty() && pieces[r][c].getType().equals("EMPTY"), count++,
				  "isEmpty, none r,c:" + r + "," + c);
		}
		
	    }
	}

	// Get a random piece and apply it to the board and check its new position
	for(int i = 0; i < TEST_NUMBER; i++) {
	    int r = rand.nextInt(Board.NUM_ROW_COL);
	    int c = rand.nextInt(Board.NUM_ROW_COL);
	    t &= test(b.getPiece(r,c) == pieces[r][c], count++, "Piece(r,c): " + r + "," + c);
	}
	
	for(int i = 0; i < TEST_NUMBER; i++) {
	    int r = rand.nextInt(Board.NUM_ROW_COL);
	    int c = rand.nextInt(Board.NUM_ROW_COL);
	    if(!b.getPiece(r,c).getType().equals("NONE")) {
		b.setPiece(r,c, new Piece("EMPTY"));
		t &= test(b.getPiece(r,c) == pieces[r][c],
			  count++, "SetEmpty r,c: " + r + "," + c);
	    }
	    else {
		b.setPiece(r,c, new Piece("BLACK"));
		t &= test(b.getPiece(r,c) == pieces[r][c],
			  count++, "SetBlack r,c: " + r + "," + c);
	    }
	}

	// Test get validMoves.
	// Have only 1 piece on the board.
	for(int r = 0; r < Board.NUM_ROW_COL; r++) {
	    for(int c =0; c < Board.NUM_ROW_COL; c++) {
		if(r == 0 && c == 0)
		    b.setPiece(r,c,new Piece("BLACK_KING"));
		else b.setPiece(r,c, new Piece("EMPTY"));
	    }
	}
	// Our board should have exactly 1 move from (0,0) to (1,1)
	MoveSequence mSeq = b.getValidMoves(new Player("BLACK"));
	t &= test(mSeq.size() == 1, count++, "mSeq Size == 1 test");
	Move m = mSeq.get(0);
	t &= test(m.fromR == 0 && m.fromC == 0 && m.toR == 1 && m.toC == 1, count++, "mSeq to 1,1 from 0,0");

	b.setPiece(0,0, new Piece("EMPTY"));
	b.setPiece(1,1, new Piece("BLACK_KING"));
	mSeq = b.getValidMoves(new Player("BLACK"));
	
	// Only piece is at 1,1 and it is a king, therefore 4 valid moves, (0,0), (0,2), (2,0), (2,2)
	t &= test(mSeq.size() == 4, count++, "mSeq == 4, king movement");
	
	
	// Test no red moves
	mSeq = b.getValidMoves(new Player("RED"));
	if(mSeq != null)
	    t &= test(mSeq.size() == 0, count++, "mSqeq size = 0");

	// Fill Entire board with pieces so theres no moves possible
	for(int r = 0; r < Board.NUM_ROW_COL; r++) {
	    for(int c = 0; c < Board.NUM_ROW_COL; c++) {
		b.setPiece(r,c, new Piece("RED"));
	    }
	}

	mSeq = b.getValidMoves(new Player("RED"));
	if(mSeq != null)
	    t &= test(mSeq.size() == 0, count++, " mSeq size == 0, full board");
	


	// Start with our basic board again.
	b = new Board();
	mSeq = b.getValidMoves(new Player("BLACK"));
	t &= test(mSeq.get(0).isJump() == false, count++);
	

	// Empty the board again bar 1 king piece at (1,1)
	// movePiece only takes valid moves, to no need to test invalid moves.
	for(int r = 0; r < Board.NUM_ROW_COL; r++) {
	    for (int c =0; c < Board.NUM_ROW_COL; c++) {
		if(r == 1 && c == 1) {
		    b.setPiece(r,c, new Piece("BLACK_KING"));
		}
		else
		    b.setPiece(r,c, new Piece("EMPTY"));
	    }
	}

	b.movePiece(new Move(1,1,0,0));
	t &= test(b.getPiece(0,0).getColorType().equals("BLACK_KING"), count++, "failed move1");
	t &= test(b.getPiece(1,1).getColorType().equals("NONE_EMPTY"), count++, "failed move1");

	// test a jump from 0,0 to 2,2
	b.movePiece(new Move(0,0,2,2));
	t &= test(b.getPiece(0,0).getColorType().equals("NONE_EMPTY"), count++, "failed jump1");
	t &= test(b.getPiece(1,1).getColorType().equals("NONE_EMPTY"), count++, "failed jump1");
	t &= test(b.getPiece(2,2).getColorType().equals("BLACK_KING"), count++, "failed jump1");
	
	// Test canJump and canMove with a 2 player board. One at (0,0) another at (1,1)
	b.setPiece(2,2, new Piece("EMPTY")); // this was our last moved piece.
	b.setPiece(1,1, new Piece("RED_KING"));
	b.setPiece(0,0, new Piece("BLACK_KING"));

	t &= test(b.canMove(0,0,1,1, new Player("BLACK")) == false, count++, "failed king move onto king");
	t &= test(b.canMove(1,1,0,0, new Player("WHITE")) == false, count++, "failed king to king move");
	t &= test(b.canMove(0,0,-1,-1, new Player("BLACK")) == false, count++, "failed canmove off board");
	t &= test(b.canJump(1,1,0,0,-1,-1, new Player("RED")) == false, count++, "failed canjump off board");
	t &= test(b.canJump(1,1,2,2,3,3, new Player("RED")) == false, count++, "failed canjump no middle man");
	t &= test(b.canJump(0,0,1,1,2,2, new Player("BLACK")) == true, count++, "failed canjump over valid tager");
	
	
	
	

	System.out.println("Failed tests: " + failedTests);
	
    }
    
    static private boolean test(boolean test, int b) {
	if(!test) {
	    failedTests++;
	    System.out.println("Failed test # " + b);
	}
	return test;
    }
    
    static private boolean test(boolean test, int b, String s) {
	if(!test) {
	    failedTests++;
	    System.out.println("Failed test # " + b + " " + s);
	}
	return test;
    }

}
