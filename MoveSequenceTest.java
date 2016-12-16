/* File: MoveSequenceTest.java
   Author: Mathew Puryear & Dulce Palacios
   Assignment: Final Project CS 360
   Description: Test class for MoveSequence.java
*/

import java.util.*;

class MoveSequenceTest {
    static private int failedTests;

    public static void main(String[] args) {
	MoveSequenceTest mst = new MoveSequenceTest();
	mst.run();
    }

    static public void run() {
	boolean t = true; int count = 1;
	failedTests = 0;


	Move[] m = new Move[4];
	m[0] = new Move(0,0,2,2);
	m[1] = new Move(0,0,1,1);
	m[2] = new Move(0,0,-1,-1);
	m[3] = new Move(0,0,-2,-2);

	ArrayList<Move> m2 = new ArrayList<Move>();
	m2.add(m[0]);
	m2.add(m[1]);
	m2.add(m[2]);
	m2.add(m[3]);
	
	
	MoveSequence mSeq = new MoveSequence(m);
	MoveSequence mSeq2 = new MoveSequence(m2);
	
	t &= test(mSeq.get(0) == m[0], count++);
	t &= test(mSeq.get(1) == m[1], count++);
	t &= test(mSeq.get(2) == m[2], count++);
	t &= test(mSeq.get(3) == m[3], count++);
	t &= test(mSeq.size() == 4, count++);

	t &= test(mSeq2.get(0) == m2.get(0), count++);
	t &= test(mSeq2.get(1) == m2.get(1), count++);
	t &= test(mSeq2.get(2) == m2.get(2), count++);
	t &= test(mSeq2.get(3) == m2.get(3), count++);
	t &= test(mSeq2.size() == 4, count++);
	

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
