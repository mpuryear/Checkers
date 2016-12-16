class PieceTest {
    static private int failedTests;

    public static void main(String[] args) {
	PieceTest pt = new PieceTest();
	pt.run();
    }


    static public void run() {
	boolean t = true; int count = 1;
	failedTests = 0;


	// isPlayer(Player p) testing
	t &= test((new Piece("RED")).isPlayer(new Player("RED")) == true, count++);
	t &= test((new Piece("BLACK")).isPlayer(new Player("RED")) == false, count++);
	t &= test((new Piece("BLACK")).isPlayer(new Player("BLACK")) == true, count++);
	t &= test((new Piece("RED")).isPlayer(new Player("BLACK")) == false, count++);

	// isRed() testing
	t &= test((new Piece("RED")).isRed() == true, count++);
	t &= test((new Piece("RED_KING")).isRed() == true, count++);
	t &= test((new Piece("BLACK")).isRed() == false, count++);
	t &= test((new Piece("BLACK_KING")).isRed() == false, count++);
	t &= test((new Piece("EMPTY")).isRed() == false, count++);

	// isBlack() testing
	t &= test((new Piece("RED")).isBlack() == false, count++);
	t &= test((new Piece("RED_KING")).isBlack() == false, count++);
	t &= test((new Piece("BLACK")).isBlack() == true, count++);
	t &= test((new Piece("BLACK_KING")).isBlack() == true, count++);
	t &= test((new Piece("EMPTY")).isBlack() == false, count++);

	// isEmpty()
	t &= test((new Piece("RED")).isEmpty() == false, count++);
	t &= test((new Piece("RED_KING")).isEmpty() == false, count++);
	t &= test((new Piece("BLACK")).isEmpty() == false, count++);
	t &= test((new Piece("BLACK_KING")).isEmpty() == false, count++);
	t &= test((new Piece("EMPTY")).isEmpty() == true, count++);

	// isKing
	t &= test((new Piece("RED")).isKing() == false, count++);
	t &= test((new Piece("RED_KING")).isKing() == true, count++);
	t &= test((new Piece("BLACK")).isKing() == false, count++);
	t &= test((new Piece("BLACK_KING")).isKing() == true, count++);
	t &= test((new Piece("EMPTY")).isKing() == false, count++);

	// getPiece
	t &= test((new Piece("RED")).getType().equals("NORMAL"), count++);
	t &= test((new Piece("RED_KING")).getType().equals("KING"), count++);
	t &= test((new Piece("BLACK")).getType().equals("NORMAL"), count++);
	t &= test((new Piece("BLACK_KING")).getType().equals("KING"), count++);
	t &= test((new Piece("EMPTY")).getType().equals("EMPTY"), count++);

	// getColor
	t &= test((new Piece("RED")).getColor().equals("RED"), count++);
	t &= test((new Piece("RED_KING")).getColor().equals("RED"), count++);
	t &= test((new Piece("BLACK")).getColor().equals("BLACK"), count++);
	t &= test((new Piece("BLACK_KING")).getColor().equals("BLACK"), count++);
	t &= test((new Piece("EMPTY")).getColor().equals("NONE"), count++);

	// getColorType
	t &= test((new Piece("RED")).getColorType().equals("RED_NORMAL"), count++);
	t &= test((new Piece("RED_KING")).getColorType().equals("RED_KING"), count++);
	t &= test((new Piece("BLACK")).getColorType().equals("BLACK_NORMAL"), count++);
	t &= test((new Piece("BLACK_KING")).getColorType().equals("BLACK_KING"), count++);
	t &= test((new Piece("EMPTY")).getColorType().equals("NONE_EMPTY"), count++);




	
	System.out.println("Failed tests: " + failedTests);
	
    }
    
    static private boolean test(boolean test, int b) {
	if(!test) {
	    failedTests++;
	    System.out.println("Failed test # " + b);
	}
	return test;
    }
}
