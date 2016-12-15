class Player {
    String color;

    public Player(String color) {
	this.color = color;
    }

    public String getColor() { return color; }
    public boolean isRed() { return color.equals("RED"); }
    public boolean isBlack() { return color.equals("BLACK"); }
}
