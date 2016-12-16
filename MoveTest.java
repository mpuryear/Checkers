/* File: MoveTest.java
   Author: Mathew Puryear & Dulce Palacios
   Assignment: Final Project CS 360
   Description: Test class for Move.
*/

class MoveTest {
    static private int failedTests;

    public static void main(String[] args) {
	MoveTest mt = new MoveTest();
	mt.run();
    }

    static public void run() {
	boolean t = true; int count = 1;
	failedTests = 0;

	t &= test((new Move(0,0,2,2)).isJump() == true, count++);
	t &= test((new Move(0,0,1,1)).isJump() == false, count++);
	t &= test((new Move(0,0,-1,-1)).isJump() == false, count++);
	t &= test((new Move(0,0,-2,-2)).isJump() == true, count++);
		  

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
