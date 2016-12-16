/* File: Piece.java
   Author: Mathew Puryear & Dulce Palacios
   Assignment: Final Project CS 360
   Description: This class is responsible for holding attributes for a checker piece.
*/

class Piece {
    String color; // RED or BLACK
    String type; // NORMAL or KING

    public Piece(String p) {
	if(p.equals("RED")) {
	    color = "RED";
	    type = "NORMAL";
	}
	else if(p.equals("BLACK")) {
	    color = "BLACK";
	    type = "NORMAL";
	}
	else if(p.equals("RED_KING")) {
	    color = "RED";
	    type = "KING";
	}
	else if(p.equals("BLACK_KING")) {
	    color = "BLACK";
	    type = "KING";
	}
	else if(p.equals("EMPTY")) {
	    color = "NONE";
	    type = "EMPTY";
	}
    }
    
    public boolean isPlayer(Player p) {	return color.equals(p.getColor()); }
    public boolean isRed() { return color.equals("RED"); }
    public boolean isBlack() { return color.equals("BLACK"); }
    public boolean isEmpty() { return type.equals("EMPTY"); }
    public boolean isKing() { return type.equals("KING"); }
    public String getType() { return type; }

    public String getColor() { return this.color;}
    public String getColorType() { return new String(color + "_" + type); }
   
}
