class TestAll{
    public static void main(String[] args) {
	System.out.println("Testing Player class");
	PlayerTest.run();
	System.out.println("\nTesting Piece class");
	PieceTest.run();
	System.out.println("\nTesting Move class");
	MoveTest.run();
	System.out.println("\nTesting MoveSequence class");
	MoveSequenceTest.run();
	System.out.println("\nTesting Board class");
	BoardTest.run();
    }
}
