/* File: Move.java
   Author: Mathew Puryear & Dulce Palacios
   Assignment: Final Project CS 360
   Description: This class is responsible for holding the attributes of a move.
*/

class Move {
    int fromR, fromC, toR, toC;

    public Move(int fromR, int fromC, int toR, int toC) {
		this.fromR = fromR;
		this.fromC = fromC;
		this.toR = toR;
		this.toC = toC;
    }

    boolean isJump() {
		// If the delta row is > 1 then its a jump
		int rowDelta = fromR - toR;
		return (rowDelta == 2 || rowDelta == -2);
    }
}
