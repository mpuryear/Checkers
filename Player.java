class Player {
    String color;
    boolean isAI;

    public Player(String color) {
		this.color = color;
		this.isAI = false;
    }

    public Player(String color, boolean isAI) {
    	this.color = color;
    	this.isAI = isAI;
    }

    public String getColor() { return color; }
    public boolean isRed() { return color.equals("RED"); }
    public boolean isBlack() { return color.equals("BLACK"); }
    public boolean isAI() { return this.isAI; }
}