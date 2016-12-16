/* File: Player.java
   Author: Mathew Puryear & Dulce Palacios
   Assignment: Final Project CS 360
   Description: This class is responsible for storing the attributes of a player.
*/
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
    public void setAI(boolean b ) { this.isAI = b; }
}