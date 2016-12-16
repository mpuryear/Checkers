class Move {
    int direction; // 1 is up 0 is down?

    int fromR, fromC, toR, toC; // fromRow fromColumn, toRow, toCol

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
