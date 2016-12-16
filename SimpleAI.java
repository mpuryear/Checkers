/* File: SimpleAI.java
   Author: Mathew Puryear & Dulce Palacios
   Assignment: Final Project CS 360
   Description: Simple AI.
*/
   

import java.util.Random;

class SimpleAI extends Player {
	public SimpleAI(String s) {
		super(s);
	}

	public Move getNextMove(MoveSequence m) {
		Random rand = new Random();
		int r = rand.nextInt(m.size());
		return m.get(r); 
	}
}