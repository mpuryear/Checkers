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
    
}