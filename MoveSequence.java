/* File: MoveSequence.java
   Author: Mathew Puryear & Dulce Palacios
   Assignment: Final Project CS 360
   Description: This class is responsible for storing an array of valid moves
                that a piece can make on the board.
*/

import java.util.*;

class MoveSequence {
    Move[] mSeq;
    public MoveSequence(Move[] m) {
	mSeq = m;
    }
    public MoveSequence(ArrayList<Move> m) {
	mSeq = new Move[m.size()];
	for(int i =0; i < m.size(); i++)
	    mSeq[i] = m.get(i);
	     
    }

    public Move get (int idx) { return this.mSeq[idx];}
    public int size() { return this.mSeq.length; }

    
}
